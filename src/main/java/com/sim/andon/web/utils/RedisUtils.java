package com.sim.andon.web.utils;

import com.alibaba.fastjson.JSONObject;
import com.sim.andon.web.entity.Persons;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**  
*   
* 类名称：RedisUtils  
* 类描述：  -== RedisUtils工具类 ==-
* 创建人：yuanqi.jing  
* 创建时间：2015-3-16 下午17:51:11  
* 修改备注：  
* @version  1.0 
*   
*/ 
@Component
public class RedisUtils {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	/** 
	 * @Title：setString
	 * @Description: 向redis中添加String 
	 * @author yuanqi.jing 
	 * @date 2015年3月16日 下午5:04:28
	 * @param key 键
	 * @param value 值
	 * @param timeOut 时效（毫秒）
	 */
	public void setString(String key, String value, long timeOut) {
		stringRedisTemplate.opsForValue().set(key, value);
		stringRedisTemplate.expire(key, timeOut, TimeUnit.MILLISECONDS);
	}
	
	/** 
	 * @Title：setString
	 * @Description: 向redis中添加String,永不超时 
	 * @author yuanqi.jing 
	 * @date 2015年3月16日 下午5:04:28
	 * @param key 键
	 * @param value 值
	 */
	public void setString(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}
	
	/** 
	 * @Title：getString
	 * @Description: 从redis中获得String 
	 * @author yuanqi.jing 
	 * @date 2015年3月16日 下午5:08:52
	 * @return
	 */
	public String getString(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
	
	/** 
	 * @Title：putObjectToRedis
	 * @Description: 将对象放入redis 
	 * @author yuanqi.jing 
	 * @date 2016年7月22日 下午4:28:03
	 * @param key
	 * @param value
	 * @param clazz
	 * @param timeout  org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
	 */
	public void putObjectToRedis(String key, final Object value, Class clazz, long timeout) {
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<T>(clazz));
		
//		redisTemplate.setKeySerializer(RedisConfig.StringSerializer.INSTANCE);
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<T>(clazz));
		
		redisTemplate.afterPropertiesSet();
		ValueOperations<Serializable, Object> ops = redisTemplate.opsForValue();
//		ops.set(key, value);
		ops.set(key, value, timeout, TimeUnit.MILLISECONDS);
	}
	
	/** 
	 * @Title：putListToRedis
	 * @Description: 将对象放入redis 
	 * @author yuanqi.jing 
	 * @date 2016年7月22日 下午5:09:22
	 * @param key
	 * @param value
	 */
	public void putListToRedis(String key, final List value) {
		String json=JSONObject.toJSONString(value);
//		String json = new Gson().toJson(value);
//		this.setString(key, json);
		this.setString(key, json, 50000);
	}
	
	public List getListFromRedis(String key, Class clazz) {
	  Object jsonInRedis = this.getString(key);
	  List list = null;
	  Object listInRedis = null;
	  if(jsonInRedis != null){
		  listInRedis= JSONObject.parseArray(jsonInRedis.toString(), clazz);
//		  listInRedis= new Gson().fromJson(jsonInRedis.toString(), new TypeToken<List>(){}.getType());
	  }
	  if (listInRedis instanceof List) {
		  list = (List) listInRedis;
	  }
	  return list;
	}
	
	/** 
	 * @Title：getObjectFromRedis
	 * @Description: 从redis获取对象 
	 * @author yuanqi.jing 
	 * @date 2016年7月22日 下午4:28:17
	 * @param key
	 * @return
	 */
	public Object getObjectFromRedis(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	/** 
	 * @Title：flushDb
	 * @Description: 清空redis所有数据
	 * @author yuanqi.jing 
	 * @date 2016年7月22日 下午3:52:01
	 */
	public void flushDb() {
		redisTemplate.getConnectionFactory().getConnection().flushDb();
	}
	
	/** 
	 * @Title：putLoginUserInRedis
	 * @Description: 将登录用户放入redis缓存 
	 * @author yuanqi.jing 
	 * @date 2016年10月19日 下午1:43:38
	 * @param p
	 * @param token
	 * @return
	 */
	public boolean putLoginUserInRedis(String token, Persons p) {
		boolean flag = false;
		
		try {
			this.putObjectToRedis(Constants.LOGIN_USER_KEY_PREFIX + token, p, Persons.class, Constants.LOGIN_TIMEOUT);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	};
	
	/** 
	 * @Title：getLoginUserInRedis
	 * @Description: 从redis缓存中获取登录用户信息 
	 * @author yuanqi.jing 
	 * @date 2016年10月19日 下午2:03:07
	 * @param token
	 * @return
	 */
	public Persons getLoginUserInRedis(String token) {
		Persons p = (Persons)this.getObjectFromRedis(Constants.LOGIN_USER_KEY_PREFIX + token);
		return p;
	}
	
	/** 
	 * @Title：putAuditLoginUserInRedis
	 * @Description: 将分层审核登录用户放入redis缓存 
	 * @author yuanqi.jing 
	 * @date 2017年3月2日 下午3:32:38
	 * @param p
	 * @param token
	 * @return
	 */
//	public boolean putAuditLoginUserInRedis(String token, Persons p) {
//		boolean flag = false;
//		
//		try {
//			this.putObjectToRedis(Constants.AUDIT_LOGIN_USER_KEY_PREFIX + token, p, Persons.class, Constants.AUDIT_LOGIN_TIMEOUT);
//			flag = true;
//		} catch (Exception e) {
//			flag = false;
//		}
//		return flag;
//	};
	
	/** 
	 * @Title：getAuditLoginUserInRedis
	 * @Description: 从redis缓存中获取分层审核登录用户信息 
	 * @author yuanqi.jing 
	 * @date 2017年3月2日 下午3:36:07
	 * @param token
	 * @return
	 */
//	public Persons getAuditLoginUserInRedis(String token) {
//		Persons p = (Persons)this.getObjectFromRedis(Constants.AUDIT_LOGIN_USER_KEY_PREFIX + token);
//		return p;
//	}
	
	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}
	
	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}


}
