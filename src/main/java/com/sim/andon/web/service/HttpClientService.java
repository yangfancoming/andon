package com.sim.andon.web.service;


import org.apache.http.HttpEntity;


/**
 * Http服务接口
 * @packageName com.dong.web.service
 * @createClass class HttpClientService.java
 * @description 
 * @author Zhuo.Zeng
 * @mail Zhuo.Zeng@sim.com
 * @createTime 2014年6月4日  下午2:55:41
 * @version 1.0
 */
public interface HttpClientService {
	
	/**
	 * Http Get方式访问
	 * @description 
	 * @Author Zhuo.Zeng
	 * @createDate 2014年6月6日  上午10:07:29
	 * 
	 * @param url  请求URL
	 * @param charset 请求返回字符编码GBK,UTF-8 等默认为UTF-8
	 * @return
	 */
	
	/**
	 * @Title: doGet
	 * @Description: Http Get方式访问
	 * @Author Zhuo.Zeng
	 * @createDate 2014年6月6日  上午10:07:29
	 * @param @param url  请求URL
	 * @param @param charset 请求返回字符编码GBK,UTF-8 等默认为UTF-8
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String doGet(String url, String charset);

	/**
	 * @Title: doPost
	 * @Description: Http Post方式访问
	 * @Author Zhuo.Zeng
	 * @createDate 2014年6月6日  上午10:06:04
	 * @param @param url 请求URL
	 * @param @param httpEntity 请求参数
	 * @param @param charset 请求返回字符编码 GBK,UTF-8 等默认为UTF-8
	 * @param @param contentType 请求头类型
	 * @param @param contentValue 请求头值
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String doPost(String url, HttpEntity httpEntity, String charset, String contentType, String contentValue) ;
	
}
