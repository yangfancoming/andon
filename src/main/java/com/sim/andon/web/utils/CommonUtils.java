package com.sim.andon.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**  
 *   
 * 类名称：CommonUtils  
 * 类描述：  -== 常用工具类 ==-
 * 创建人：yuanqi.jing  
 * 创建时间：2013-8-26 下午10:51:25  
 * 修改备注：  
 * @version  1.0 
 *   
 */
public class CommonUtils {
	
	/**
	 * 将字符串用MD5加密
	 * 
	 * @param str
	 * @return String
	 */
	public static String md5(String source) {
		Log logger=LogFactory.getLog(CommonUtils.class);
		StringBuffer sb = new StringBuffer(32);
			
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));
				
			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			logger.error("Can not encode the string '" + source + "' to MD5!", e);
			return null;
		}
		return sb.toString();
	}
	/**
	 * 将用户名和密码用MD5加密
	 * 
	 * @param str
	 * @return String
	 */
	public static String md5(String username, String password) {
		// 加密后欲存入数据库的密码
		return CommonUtils.md5(CommonUtils.md5(username) + CommonUtils.md5(password));
	}
	
	/**
	 * 产生12位用户的初始密码
	 * @return
	 */
	public static String randomPassword() {
		String uuidStr = UUID.randomUUID().toString();
		return uuidStr.substring(uuidStr.lastIndexOf("-") + 1);
	}
	
	/**
	 * 字符串是否为空
	 * 
	 * @param val
	 * @return	boolean true:为空,false:不为空
	 */
	public static boolean isEmpty(String val) {
		return null == val || "".equals(val.trim());
	}
	
//	/**
//	 * 报表模板本地路径
//	 * 
//	 * @return String excell报表模板存放路径
//	 */
//	public static String getExcellReportModelPath() {
//		return ServletActionContext.getServletContext().getRealPath(
//				SysConstants.MODEL_FILE_PATH);
//	}
	
	/**
	 * 取得上传文件的扩展名
	 * 
	 * @return String fileName上传文件的名字
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	/**
	 * 获得给定数字的符号(正数返回1.0, 负数返回-1.0, 0返回0.0)
	 * 
	 * @param value 数值
	 * @return
	 */
	public static double signum(double value) {
		if(value>0) {return 1.0;}
		else if(value<0) {return -1.0;}
		else {return 0;}
	}
	
	/**
	 * 对给定的数字进行四舍五入
	 * 
	 * @param value   数值
	 * @param scale   小数点后位数
	 * @return
	 */
	public static double roundHE(double value, int scale) {
		double s = signum(value);
		double c = Math.pow(10, scale);
		value = Math.abs(value) * c;
		if (value % 2 < 1) {
			return s*(Math.ceil(Math.max(value - 0.5d, 0))/c);
		}
		else {
			return s*(Math.floor(value + 0.5)/c);
		}
	}
	
	/**
	 * 
	 * @Title：bigDecimaldiv
	 * @Description: （相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入 
	 * @author cui.li
	 * @date 2015年3月3日 下午1:30:19
	 * @param v1被除数
	 * @param v2 除数 
	 * @param scale表示表示需要精确到小数点以后几位
	 * @return
	 */
	public   static   String   bigDecimaldiv(String v1,String v2,int scale){          
        BigDecimal   b1   =   new   BigDecimal(v1);
        BigDecimal   b2   =   new   BigDecimal(v2);  
        return   b1.divide(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();   
    }  
	
	/**
	 * main方法 
	 * @param args
	 */
	/*public static void main(String[] args) {
		// 用户名
		String username  = "admin";
		
		// 密码
		String password = "123456";
		
		System.out.println("用户名：" + username);
		System.out.println("密     码：" + password);
		System.out.println("md5密码：" + CommonUtils.md5(username, password));
		System.out.println("md5密码：" + CommonUtils.md5(password));
		
	}*/
	
	/** 
	 * @Title：generateSn
	 * @Description: 生成序列号，例如 160523101024_1f2adda9c68d
	 * @author yuanqi.jing 
	 * @date 2017年3月28日 上午10:16:55
	 * @return
	 */
	public static String generateSn() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		String fullUuid = UUID.randomUUID().toString();
		String uuid = fullUuid.substring(24, 36);
		return dateStr + "_" + uuid;
	}
	
	/**
	 * @Description: List转Map，Key为frontStr加上字段联合主键
	 * @param list
	 * @param frontStr
	 * @param classe
	 * @param field
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @return: Map<String,Object>
	 * @Author: fang.wang
	 * @Date: 2019年7月24日
	 */
	public static <T> Map<String, T> listToMap(List<T> list,String frontStr, String... field) 
			throws NoSuchFieldException, SecurityException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException{
		Map<String, T> map = new HashMap();
		if(list != null && list.size() > 0) {
 			for(int i = 0; i < list.size(); i++) {
				String key = "";
				T t = list.get(i);
				Class tClass = t.getClass();
				if (field != null && field.length > 0) {
					for (String fd : field) {
						fd = toUpperCaseFirstOne(fd);
						Method method = null;
						try {
							method = tClass.getDeclaredMethod("get"+fd);
						} catch (NoSuchMethodException e) {
							Class superClass = tClass.getSuperclass();
							try {
								method = superClass.getDeclaredMethod("get"+fd);
							} catch (NoSuchMethodException e1) {
								e1.printStackTrace();
							}
						}
						key += (String) method.invoke(t);
					}
					map.put(frontStr + key, t);
				} else {
					return map;
				}
			}
		}
		return map;
	}

	/**
	 * @Description: 首字母大写
	 * @param str
	 * @return
	 * @return: String
	 * @Author: fang.wang
	 * @Date: 2019年7月24日
	 */
	private static String toUpperCaseFirstOne(String str) {
        char[] charArr=str.toCharArray();
        charArr[0]-=32;
        return String.valueOf(charArr);
    }
}
