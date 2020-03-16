package com.sim.andon.web.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Constants
 * @Description: 常量类
 * @author yuanqi.jing
 * @date 2016年10月18日 下午12:01:26
 * 
 */
public class Constants {

	/**
	 * @Fields LOGIN_TOKEN_KEY_IN_REDIS : pad 登录令牌在缓存中的key的前缀
	 */
	public static final String LOGIN_TOKEN_KEY_PREFIX = "loginTokenKey_";

	/**
	 * @Fields LOGIN_USER_KEY_PREFIX : pad 登录用户在缓存中的key的前缀
	 */
	public static final String LOGIN_USER_KEY_PREFIX = "loginUserKey_";

	/**
	 * @Fields LOGIN_TIMEOUT : pad 登录令牌超时时间，单位：毫秒
	 */
	public static long LOGIN_TIMEOUT = 24 * 60 * 60 * 1000;

	/*
	 * // 分层审核登录 开始
	 *//**
		 * @Fields AUDIT_LOGIN_TOKEN_KEY_PREFIX : pad 分层审核，登录令牌在缓存中的key的前缀
		 */
	/*
	 * public static final String AUDIT_LOGIN_TOKEN_KEY_PREFIX =
	 * "auditLoginTokenKey_";
	 * 
	 *//**
		 * @Fields AUDIT_LOGIN_USER_KEY_PREFIX : pad 分层审核，登录用户在缓存中的key的前缀
		 */
	/*
	 * public static final String AUDIT_LOGIN_USER_KEY_PREFIX =
	 * "auditLoginUserKey_";
	 * 
	 *//**
		 * @Fields AUDIT_LOGIN_TIMEOUT : pad 分层审核，登录令牌超时时间，单位：毫秒
		 *//*
		 * public static long AUDIT_LOGIN_TIMEOUT = 24 * 60 * 60 * 1000; //
		 * 分层审核登录 结束
		 */
	/**
	 * 设备类别编码：AOI
	 */
	public static final String EQUPMENT_CATEGORY_CODE_AOI = "AOI";
	public static final String EQUPMENT_CATEGORY_CODE_AOI_STATISTICS = "VI";

	/**
	 * 设备类别编码：SPI
	 */
	public static final String EQUPMENT_CATEGORY_CODE_SPI = "SPI";
	public static final String EQUPMENT_CATEGORY_CODE_SPI_STATISTICS = "SPI";

	/**
	 * 阈值type
	 */
	public static final String THRESHOLD_TYPE_AOI = "aoi";
	public static final String THRESHOLD_TYPE_SPI = "spi";
	public static final String THRESHOLD_TYPE_TIMEOUT = "timeout";
	public static final String THRESHOLD_TYPE_OPL = "opl";
	public static final String THRESHOLD_TYPE_LEADERTIMEOUT = "leaderTimeout";
	public static final String THRESHOLD_TYPE_ERRORTODAY = "errorToday";
	public static final String THRESHOLD_TYPE_ERRORMAIL = "errorMail";
	public static final String THRESHOLD_TYPE_INVENTORYTODAY = "inventoryToday";
	/**
	 * 故障流程
	 */
	public static final String ERRORFLOW_FAQIGUZHANG = "FQGZ";// 发起故障
	public static final String ERRORFLOW_XIANZHANGQUEREN = "XZQR";// 线长确认
	public static final String ERRORFLOW_WEIXIURENYINGDA = "WXRYD";// 维修人抢单
	public static final String ERRORFLOW_WEIXIURENQIANDAO = "WXRQD";// 维修人签到
	public static final String ERRORFLOW_WENTIQUEREN = "WTQR";// 问题确认
	public static final String ERRORFLOW_LINGQUBEIJIAN = "LQBJ";// 领取备件
	public static final String ERRORFLOW_QINGQIUZHIYUAN = "QQZY";// 请求支援
	public static final String ERRORFLOW_GONGCHENGSHICHULI = "GCSCL";// 工程师处理
	public static final String ERRORFLOW_SHIFANGJIXIU = "GQ";// 释放机修==挂起
	public static final String ERRORFLOW_WENTIJIEJUE = "WTJJ";// 问题解决
	public static final String ERRORFLOW_SHENGCHANFANGXING = "SCFX";// 生产放行

	/**
	 * 异常分类
	 */
	public static final String SHEBEIGUZHANG = "设备故障";
	public static final String RENYUANDUNAQUE = "人员短缺";
	public static final String WULIAODUANQUE = "物料短缺";
	public static final String ZHILIANGWENTI = "质量问题";
	public static final String QITAWENT = "其他问题";

	public List<String> getConstantsList(String condition) {
		List<String> list = new ArrayList<String>();
		Field[] fields = Constants.class.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			if (StringUtils.contains(name, condition)) {
				try {
					Object str = field.get(Constants.class);
					list.add(String.valueOf(str));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
