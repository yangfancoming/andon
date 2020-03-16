package com.sim.andon.web.core.jdbc;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.sim.andon.web.core.jdbc.annotation.Key;
import com.sim.andon.web.core.jdbc.annotation.NotRecord;
import com.sim.andon.web.core.jdbc.annotation.Table;
import com.sim.andon.web.core.jdbc.common.Page;
import com.sim.andon.web.core.jdbc.common.PageModel;
import com.sim.andon.web.utils.ReflectUtil;
import com.sim.andon.web.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;

public class BaseRepository<T, PK extends Serializable> {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	// @Resource
	// 此处可以注入多个数据源的JdbcTemplate
	@Resource(name = "primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
	@Resource(name = "secondaryJdbcTemplate")
	protected JdbcTemplate secondaryJdbcTemplate;

	private Class<T> entityClass;
	private String tableName;
	private NamedParameterJdbcTemplate npjt;

	private List<String> keysList = new ArrayList<String>();
	private List<String> notRecords = new ArrayList<String>();
	private List<BeanField> commonField = new ArrayList<BeanField>();
	/* 表前缀 */
	private final static String TABLE_FIX = "T_";

	/**
	 * Creates a new instance of BaseRepository. 初始化时进行实体解析，分析出表名 字段名称 主键等
	 */
	@SuppressWarnings("unchecked")
	protected BaseRepository() {
		this.entityClass = null;

		log.debug("[Repository 初始化开始:{}]", this.getClass());

		@SuppressWarnings("rawtypes")
		Class<? extends BaseRepository> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
		log.debug("[Repository 初始化：entityClass->{}]", this.entityClass);

		Field[] fiels = this.entityClass.getDeclaredFields();// 获得反射对象集合
		boolean t = this.entityClass.isAnnotationPresent(Table.class);// 获得类是否有注解
		// 注解table
		if (t) {
			tableName = this.entityClass.getAnnotation(Table.class).name();
		} else {
			// 类名转换table
			tableName = TABLE_FIX + StringUtil.classToTableName(entityClass.getSimpleName());
		}
		// 转换小写
		tableName = tableName.toLowerCase();
		log.debug("[Repository  tableName->{}]", tableName);

		setTableName(tableName);

		for (Field fl : fiels) {
			fl.setAccessible(true);// 开启支私有变量的访问权限
			if (fl.isAnnotationPresent(Key.class)) {// 判断是否存在主键
				keysList.add(fl.getName());
				commonField.add(new BeanField(fl.getName(), StringUtil.camelToUnderline(fl.getName()).toUpperCase()));
			} else if (fl.isAnnotationPresent(NotRecord.class)) {
				notRecords.add(fl.getName());
			} else {
				// 非静态变量为表中列,大写。。
				if (!Modifier.isStatic(fl.getModifiers())) {
					commonField
							.add(new BeanField(fl.getName(), StringUtil.camelToUnderline(fl.getName()).toUpperCase()));
				}

			}
		}

		if (log.isDebugEnabled()) {
			Gson gson = new Gson();
			log.debug("[Repository  keysList->{}]", gson.toJson(keysList));
			log.debug("[Repository  notRecords->{}]", gson.toJson(notRecords));
			log.debug("[Repository  commonField->{}]", gson.toJson(commonField));
			log.debug("[Repository  初始化结束:{}]", this.getClass());
		}

	}

	/**
	 * getQueryStr:获得列名. <br/>
	 *
	 * @author he.sun
	 * @param field
	 * @return
	 * @since JDK 1.7
	 */
	private String getQueryStr(String... field) {
		if (field == null || field.length == 0) {
			return tableName + ".*";
		} else {
			String temp = "";
			for (String fd : field) {
				temp += fd + ",";
			}
			return temp.substring(0, temp.length() - 1);
		}
	}

	// 用id查询很少用到
	/**
	 * findOne:根据ID获得实体 <br/>
	 *
	 * @author he.sun
	 * @param id
	 *            ID
	 * @param field
	 *            需要的查询的列名
	 * @return
	 * @since JDK 1.7
	 */
	public T findOne(PK id, String... field) {

		String sql = "select " + getQueryStr(field) + " from " + tableName + " where id=? limit 0,1";
		try {
			log.debug("[findOne sql ->{}]", sql);

			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(entityClass), id);
		} catch (EmptyResultDataAccessException e) {
			log.error("[findOne is error]", e);

		}
		return null;
	}

	/**
	 * findOne:根据condition获得一个实体<br/>
	 *
	 * @author he.sun
	 * @param condition
	 *            where 后的条件
	 * @param field
	 *            需要的查询的列名
	 * @return
	 */
	// public T findOne(String condition, String... field) {
	// Map<String, ?> paramMap = Maps.newHashMap();
	// return this.findOne(condition, paramMap, field);
	// }

	/**
	 * findOneByCondition:根据条件获得一个实体 <br/>
	 * 
	 * @author he.sun
	 * @param condition
	 *            条件。使用命名占位符如：id=:id
	 * @param paramMap
	 *            参数。如 map.put("id",111L);
	 * @param field
	 *            需要的查询的列名
	 * @return T 实体对象
	 * @since JDK 1.7
	 */
	public T findOne(String condition, Map<String, ?> paramMap, String... field) {
		if (Strings.isNullOrEmpty(condition)) {
			condition = "1 = 1";
		}
		String sql = "select " + getQueryStr(field) + "  from " + tableName + " where  " + condition
				+ " limit 0,1 ";
		try {
			log.debug("[findOne sql ->{}]", sql);

			return this.getNamedParameterJdbcTemplate().queryForObject(sql, paramMap,
					new BeanPropertyRowMapper<T>(entityClass));
		} catch (EmptyResultDataAccessException e) {
			// 无返回值
			// log.info(sql + ", no result");
		} catch (Exception e) {
			log.error("[findOne is error]", e);
		}
		return null;
	}

	/**
	 * findOneByCondition:根据条件获得一个实体 . <br/>
	 * 
	 * @author he.sun
	 * @param condition
	 *            条件。使用?占位符如：id=?
	 * @param params
	 *            参数对象[]，需要保证[]中参数的顺序
	 * @param field
	 *            需要的查询的列名
	 * @return T 实体对象
	 * @since JDK 1.7
	 */
	public T findOne(String condition, Object[] params, String... field) {
		if (condition == null) {
			condition = " 1= 1";
		}
		String sql = "select " + getQueryStr(field) + "  from " + tableName + "  where  " + condition
				+ "  and rownum = 1";
		try {
			log.debug("[findOne sql ->{}]", sql);

			return this.jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<T>(entityClass));
		} catch (EmptyResultDataAccessException e) {
			// 无返回值
			// log.info(sql + ", no result");
		} catch (Exception e) {
			log.error("[findOne is error]", e);
		}
		return null;
	}

	/**
	 * findOneByPropertyName:根据条件获得一个实体 . <br/>
	 *
	 * @author he.sun
	 * @param propertyName
	 *            表中列名，如：USER_ID
	 * @param value
	 *            列名的值
	 * @return T 实体对象
	 * @since JDK 1.7
	 */
	public T findOneByCloumName(String propertyName, Object value, String... field) {
		String sql = "select " + getQueryStr(field) + "  from " + tableName + "  where " + tableName + " ."
				+ propertyName + " = ?   and rownum = 1";
		try {
			log.debug("[findOneByCloumName sql ->{}]", sql);

			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(entityClass), value);
		} catch (EmptyResultDataAccessException e) {
			// 无返回值
			// log.info(sql + ", no result");
		} catch (Exception e) {
			log.error("[findOneByCloumName is error]", e);
		}
		return null;
	}

	/**
	 * findAll:获得全部实体列表. <br/>
	 *
	 * @author he.sun
	 * @return
	 */
	public List<T> findAll() {
		return findAll(null);
	}

	/**
	 * findAll:获得全部实体列表 <br/>
	 *
	 * @author he.sun
	 * @param order
	 *            排序方式 :order by XXX desc XXX
	 * @param field
	 *            获取列名
	 * @return
	 * @since JDK 1.7
	 */
	public List<T> findAll(String order, String... field) {
		if (order == null) {
			order = "";
		}
		String sql = "select " + getQueryStr(field) + "  from " + tableName + " " + order;
		try {
			log.debug("[findAll sql ->{}]", sql);
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(entityClass));
		} catch (Exception e) {
			log.error("[findAll is error]", e);
		}
		return null;
	}

	/**
	 * findAllByDBLinkName:获得全部实体列表 <br/>
	 *
	 * @author he.sun
	 * @param order
	 *            排序方式 :order by XXX desc XXX
	 * @param field
	 *            获取列名
	 * @return
	 * @since JDK 1.7
	 */
	public List<T> findAllByDBLinkName(String order, String DBLinkName, String... field) {
		if (order == null) {
			order = "";
		}
		String sql = "select " + getQueryStr(field) + "  from " + tableName + "@" + DBLinkName + " " + order;
		try {
			log.debug("[findAll sql ->{}]", sql);
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(entityClass));
		} catch (Exception e) {
			log.error("[findAll is error]", e);
		}
		return null;
	}

	/**
	 * findAll:获得全部实体 <br/>
	 *
	 * @author he.sun
	 * @param condition
	 *            条件。使用命名占位符如：id=:id
	 * @param paramMap
	 *            参数。如 map.put("id",111L);
	 * @param order
	 *            排序方式 :order by XXX desc XXX
	 * @param field
	 *            获取列名
	 * @return
	 * @since JDK 1.7
	 */
	public List<T> find(String condition, Map<String, ?> paramMap, String order, String... field) {
		if (Strings.isNullOrEmpty(condition)) {
			condition = " 1=1 ";
		}
		String sql = "select " + getQueryStr(field) + "  from " + tableName + " where " + condition + " " + order;
		try {
			log.debug("[find sql ->{}]", sql);
			return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper(entityClass));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[find is error]", e);
		}
		return null;
	}

	/**
	 * findAll:获得全部实体 <br/>
	 *
	 * @author he.sun
	 * @param condition
	 *            条件。使用?占位符如：id=?
	 * @param params
	 *            参数对象[]，需要保证[]中参数的顺序
	 * @param order
	 *            排序方式 :order by XXX desc XXX
	 * @param field
	 *            获取列名
	 * @return
	 * @since JDK 1.7
	 */
	public List<T> find(String condition, Object[] params, String order, String... field) {
		if (condition == null) {
			condition = " 1=1 ";
		}
		String sql = "select " + getQueryStr(field) + "  from " + tableName + " where " + condition + " " + order;
		try {
			log.debug("[find sql ->{}]", sql);

			return this.jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(entityClass));
		} catch (Exception e) {
			log.error("[find is error]", e);
		}
		return null;
	}

	/**
	 * findTotalCountByCondition:获得行数 <br/>
	 *
	 * @author he.sun
	 * @param condition
	 *            条件。使用命名占位符如：id=:id
	 * @param paramMap
	 *            参数。如 map.put("id",111L);
	 * @return
	 * @since JDK 1.7
	 */
	@SuppressWarnings("deprecation")
	public Long findCount(String condition, Map<String, Object> paramMap) {
		if (condition == null) {
			condition = " 1=1 ";
		}
		String sql = "select count(1) from " + tableName + " where " + condition;
		log.debug("[findCount sql ->{}]", sql);
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		return getNamedParameterJdbcTemplate().queryForObject(sql, paramMap, Long.class);
		// return 0l;
	}

	/**
	 * isExist:该查询条件的实体是否存在 <br/>
	 *
	 * @author he.sun
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 * @return
	 * @since JDK 1.7
	 */
	public boolean isExist(String propertyName, Object value) {
		T object = findOneByCloumName(propertyName, value);
		log.debug("[isExist  object->{}]", object);

		return (object != null);
	}

	/**
	 * save:添加实体,返回主键. <br/>
	 *
	 * @author he.sun
	 * @param entity
	 *            实体
	 * @return
	 * @since JDK 1.7
	 */
	public void save(T entity) {
		saveAndReturnKey(entity, keysList.toArray(new String[] {}));
	}

	public String saveAndReturnId(T entity) {
		return saveAndReturnKey(entity, keysList.toArray(new String[] {}));
	}

	/**
	 * saveOrUpdate:根据ID自动判断更新或者新增. <br/>
	 *
	 * @author he.sun
	 * @param entity
	 * @since JDK 1.7
	 */
	public T saveOrUpdate(T entity) {
		try {
			Method method = entity.getClass().getMethod("getId");
			String id = (String) (method.invoke(entity));
			if (StringUtil.checkNotNull(id)) {
				update(entity);
			} else {
				save(entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return entity;
	}

	/**
	 * update:根据实体更新实体 <br/>
	 *
	 * @author he.sun
	 * @param entity
	 *            实体
	 * @return 更新的记录数
	 * @since JDK 1.7
	 */
	public int update(T entity) {

		String sql = "update " + tableName + " set ";
		for (BeanField beanField : commonField) {
			if (ReflectUtil.invokeGet(entity, beanField.getFieldName()) != null) {
				sql += beanField.getCloumName() + "=:" + beanField.getFieldName() + " , ";
			}
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += " where id=:id";

		log.debug("[update  sql->{}]", sql);

		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));

	}

	/**
	 * update:通过条件更新. <br/>
	 *
	 * @author he.sun
	 * @param condition
	 *            条件。使用命名占位符如：id=:id
	 * @param paramMap
	 *            参数。如 map.put("id",111L);
	 * @return
	 * @since JDK 1.7
	 */
	public int update(String condition, Map<String, ?> paramMap) {
		String sql = "update " + tableName + " set " + condition;
		log.debug("[update  sql->{}]", sql);
		return getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	/**
	 * delete:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author he.sun
	 * @param condition
	 *            条件。使用命名占位符如：id=:id
	 * @param paramMap
	 *            参数。如 map.put("id",111L);
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	public int delete(String condition, Map<String, ?> paramMap) {
		String sql = "delete from " + tableName + " where " + condition;
		log.debug("[delete  sql->{}]", sql);
		return getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	/**
	 * 根据主键进行物理删除
	 * 
	 * @return 影响行数
	 */
	public int delete(PK id) {
		String sql = "delete from " + tableName + " where id = :id";
		log.debug("[delete  sql->{}]", sql);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	/**
	 * 根据主键进行批量物理删除
	 */
	public void delete(PK[] ids) {
		for (PK id : ids) {
			log.debug("[delete  PK->{}]", id);
			delete(id);
		}
	}

	public Page<T> getListByPage(int start, int length, String condition, Map<String, Object> paramMap,
			String orderAndAsc, String... field) {
		Page<T> page = new Page<T>();
		if (Strings.isNullOrEmpty(condition)) {
			condition = " 1=1 ";
		}
		if (paramMap == null) {
			paramMap = Maps.newHashMap();
		}
		if (orderAndAsc == null) {
			orderAndAsc = "";
		}
		/*String sql = "select tmp.* from (select ROWNUM rn, A .* FROM ( SELECT " + getQueryStr(field) + " from "
				+ tableName + " where " + condition + " " + orderAndAsc + "	) A) tmp where tmp.rn > " + start
				+ " and tmp.rn <=" + (start + length);*/
		String sql = "select tmp.* from ( SELECT " + getQueryStr(field) + " from "
				+ tableName + " where " + condition + " " + orderAndAsc + "	) tmp limit " + start + "," + length;
		log.debug("[getListByPage  sql->{}]", sql);
		// page.setRows(getNamedParameterJdbcTemplate().queryForList(sql,
		// paramMap, entityClass));
		page.setData(getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper(entityClass)));

		log.debug("[page  Rows->{}]", page.getData());

		long allCount = this.findCount(condition, paramMap);
		log.debug("[page  allCount->{}]", allCount);

		page.setTotal(allCount);
		page.setiTotalDisplayRecords(allCount);
		page.setiTotalRecords(allCount);
		if (log.isDebugEnabled()) {
			Gson gson = new Gson();
			log.debug("[page  page->{}]", gson.toJson(page));

		}

		return page;
	}

	/**
	 * getListByPage:分页查询<br/>
	 *
	 * @author he.sun
	 * @param start
	 *            从第几条记录开始
	 * @param pageSize
	 *            每页几条数据
	 * @param condition
	 *            查询条件
	 * @param paramMap
	 *            参数值
	 * @param orderAndAsc
	 *            排序
	 * @param field
	 *            列名 可不可。不写为全部
	 * @return
	 * @since JDK 1.7
	 */
	public PageModel<T> getListByPage2(int start, int pageSize, String condition, Map<String, Object> paramMap,
                                       String orderAndAsc, String... field) {
		PageModel<T> page = new PageModel<T>();
		if (Strings.isNullOrEmpty(condition)) {
			condition = " 1=1 ";
		}
		if (paramMap == null) {
			paramMap = Maps.newHashMap();
		}
		if (orderAndAsc == null) {
			orderAndAsc = "";
		}
		String sql = "select tmp.* from (select ROWNUM rn, A .* FROM ( SELECT " + getQueryStr(field) + " from "
				+ tableName + " where " + condition + " " + orderAndAsc + "	) A) tmp where tmp.rn > " + start
				+ " and tmp.rn <=" + (start + pageSize);
		log.debug("[getListByPage  sql->{}]", sql);

		// page.setRows(getNamedParameterJdbcTemplate().queryForList(sql,
		// paramMap, entityClass));
		page.setRows(getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper(entityClass)));

		log.debug("[page  Rows->{}]", page.getRows());

		long allCount = this.findCount(condition, paramMap);
		log.debug("[page  allCount->{}]", allCount);

		if (allCount % pageSize == 0) {
			page.setPageCount(allCount / pageSize);
		} else {
			page.setPageCount((allCount / pageSize) + 1);
		}
		page.setTotal(allCount);
		page.setPageNumber(start);
		page.setPageSize(pageSize);

		if (log.isDebugEnabled()) {
			Gson gson = new Gson();
			log.debug("[page  page->{}]", gson.toJson(page));

		}

		return page;
	}

	/**
	 * 添加实体,返回主键
	 * 
	 * @param objForSave
	 * @param keyColumns
	 * @return
	 */
	private String saveAndReturnKey(Object objForSave, String... keyColumns) {
		String sql = getInsertSql();
		log.debug("[saveAndReturnKey  sql->{}]", sql);

		String id = UUID.randomUUID().toString();
		try {
			Method method = objForSave.getClass().getMethod("setId", String.class);
			method.invoke(objForSave, id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(objForSave);
		this.getNamedParameterJdbcTemplate().update(sql, paramSource);
		return id;
	}
	// @SuppressWarnings("unchecked")
	// private String saveAndReturnId(Object objForSave, String... keyColumns) {
	// String sql = getInsertSql();
	// log.debug("[saveAndReturnKey sql->{}]", sql);
	//
	// KeyHolder keyHolder = new GeneratedKeyHolder();
	// SqlParameterSource paramSource = new BeanPropertySqlParameterSource(
	// objForSave);
	// this.getNamedParameterJdbcTemplate().update(sql, paramSource,
	// keyHolder, keyColumns);
	//
	// String id = keyHolder.getKey().toString();
	// log.debug("[saveAndReturnKey id->{}]", id);
	//
	// try {
	// Method method = objForSave.getClass()
	// .getMethod("setId", String.class);
	// method.invoke(objForSave, id);
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// return id;
	// }

	/**
	 * 生成插入的sql语句
	 * 
	 * @param model
	 * @param tableName
	 * @return
	 */
	private String getInsertSql() {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into " + tableName + " (");
		for (int i = 0; i < commonField.size(); i++) {
			String param = commonField.get(i).getCloumName();
			if("STATUS".equals(param) || 
			   "SCHEMA".equals(param) ||
			   "TYPE".equals(param)
			   ) {
				param = "`" + param + "`";
			}
			sb.append(param + ", ");
		}
		sb.deleteCharAt(sb.length() - 2);
		sb.append(") values (");
		for (int i = 0; i < commonField.size(); i++) {
			sb.append(":" + commonField.get(i).getFieldName() + ", ");
		}
		sb.deleteCharAt(sb.length() - 2);
		sb.append(")");
		return sb.toString();
	}

	@Async
	public int updateSqlAsync(String sql) {

		return jdbcTemplate.update(sql);
	}

	public String getTableName() {
		return this.tableName;
	}

	private void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate).withTableName(tableName);
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		if (npjt == null) {
			npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		}
		return npjt;
	}

	private class BeanField {
		/* JAVA属性名称 */
		private String fieldName;
		/* 表 中列名 */
		private String cloumName;

		public BeanField(String fieldName, String cloumName) {
			this.fieldName = fieldName;
			this.cloumName = cloumName;
		}

		public String getFieldName() {
			return fieldName;
		}

		public String getCloumName() {
			return cloumName;
		}

	}
}
