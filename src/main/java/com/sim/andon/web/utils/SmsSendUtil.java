package com.sim.andon.web.utils;

import com.sim.andon.web.service.HttpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * 短信发送类
 * @author mindong.wang
 * @Date 2014年6月6日17:28:22
 */
@Component
public class SmsSendUtil {
	
	
	@Autowired
	private HttpClientService httpClientService;
	
	private static Logger logger = LoggerFactory.getLogger(SmsSendUtil.class);
	private static String  url = "http://sh.ipyy.com:8888/sms.aspx";// new platform "http://sh2.ipyy.com/sms.aspx";
	private String parameter ;
	private static String action = "send";
	private static String userId = "1987";
	private static String account = "jkcs95";//new platform  jkwl017
	private static String passWord = "jkcs95";
	private static String label = "短信提醒";
	
	/**
	 * @Param
	 * mobile 电话号码
	 * content 短信内容
	 * sendTime 发送时间 为空表示立即发送  定时发送格式  2010-10-24 09:08:10
	 * 
	 */
	public  String sendMessage(List<String> mobile, String content) {
		initParam(mobile,content);
		String urlNameString = url + "?" + parameter;
		String result = httpClientService.doGet(urlNameString, "UTF-8");
        return result;
    }

	/**
	 * 初始化请求参数
	 * @param mobile
	 * @param content
	 * @param sendTime 为空表示立即发送，定时发送格式2010-10-24 09:08:10
	 */
	private void initParam(List<String> mobiles, String content) {
		try {
			content = java.net.URLEncoder.encode(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("字符编码转换错误！"+e.getMessage());
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		StringBuffer param = new StringBuffer();
		param.append("action=").append(action).append("&userid=").append(userId).append("&account=").append(account).append("&password=")
			.append(passWord).append("&mobile=");
		if(mobiles.size()>0&&!"".equals(content)&&!content.equals("")){
			for(String mobile : mobiles){
				param.append(mobile).append(",");
			}
			param.deleteCharAt(param.lastIndexOf(","));
		}
		param.append("&content=").append(content).append("【").append(label).append("】")
			.append("&sendTime=");//.append("&extno=");
		
		parameter = param.toString();
		logger.info("发送参数="+parameter);
	}
}
