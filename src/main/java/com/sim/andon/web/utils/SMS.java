package com.sim.andon.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMS {
	private static final Logger logger = LoggerFactory.getLogger(SMS.class);
	
	/**
	 * 发送短信
	 * @param mobiles
	 *            手机号码，多个号码使用","分割
	 * @param msg
	 *            短信内容
	 */
	public static void sentSMS(String mobiles, String msg) {
		String url = "http://222.73.117.158/msg/";// 应用地址
		String account = "meidezhileng";// 账号
		String pswd = "Tch123456";// 密码
		// String mobile = "13918293138";// 手机号码，多个号码使用","分割
		// String msg = "亲爱的用户，您的验证码是"+code.getCode()+"，5分钟内有效[云拍科技]。";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String product = null;// 产品ID
		String extno = null;// 扩展码

		try {
			logger.info("向" + mobiles + "发送短信:" + msg);
			String returnString = HttpSender.batchSend(url, account, pswd,
					mobiles, msg, needstatus, product, extno);
			logger.info("短信发送结果:" + returnString);
			// code.setSendCount(code.getSendCount()+1);
			// code.setSendTime(curTime);
			// SessionManager.CODE_TEMP.put(session.getId(),code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
