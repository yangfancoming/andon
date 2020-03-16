package com.sim.andon.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Properties;

public class Mail {
	private static Logger logger = LoggerFactory.getLogger(Mail.class);
	private static final String DEFAULT_ENCODING = "utf-8";
	private String host;

	private String username;

	private String password;

	/**
	 * 初始化邮件类，设置HOST,USERNAME,PASSWORD
	 * 
	 * @param host
	 *            SMTP
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	public Mail(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
		System.out.println("host："+ host + "###length:"+ host.length() );
		System.out.println("username："+ username + "###length:"+ username.length() );
		System.out.println("password："+ password + "###length:"+ password.length() );
	}

	public void sendMail(String to, String from, String subject,
			String message, List<File> files) throws MessagingException {
		// logger.debug("sendMail:{},{},{},{},{},{}",to,from,subject,message,file.getName());
		SendMailParam mailparam = new SendMailParam(to, from, subject, message, true);
		
		sendMail(mailparam, files);
	}

	public void sendMail(SendMailParam mailparam, List<File> files)
			throws MessagingException {
		// logger.debug("sendMail:{},{},{},{},{},{},{}",to,from,subject,message,file.getName(),auth);
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定mail server
		senderImpl.setHost(this.host);
		logger.debug("设置HOST：{}", host);
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// multipart模式 为true时发送附件 可以设置html格式
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true, DEFAULT_ENCODING);

		// 设置收件人，寄件人
		messageHelper.setTo(mailparam.getTo());
		logger.debug("设置收件人：{}", mailparam.getTo());
		messageHelper.setFrom(mailparam.getFrom());
		logger.debug("设置寄件人：{}", mailparam.getFrom());
		messageHelper.setSubject(mailparam.getSubject());
		logger.debug("设置主题：{}", mailparam.getSubject());
		// true 表示启动HTML格式的邮件
		messageHelper.setText(mailparam.getMessage(), true);
		logger.debug("启动HTML格式的邮件");
		if (files != null) {
			for (File f : files) {
				// 插入附件
				messageHelper.addAttachment(f.getName(), f);
				logger.debug("插入附件", f);
			}
		}
		// 账号密码
		senderImpl.setUsername(this.username);
		logger.debug("账号:{}", username);
		senderImpl.setPassword(this.password);
		logger.debug("密码:{}", password);

		// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		if (mailparam.getAuth()) {
			logger.debug("让服务器进行认证,认证用户名和密码是否正确");
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", mailparam.getAuth());
			logger.debug("mail.smtp.auth:{}", mailparam.getAuth());
			prop.put("mail.smtp.timeout", "25000");
			logger.debug("mail.smtp.timeout:{}", "25000");
			// gmail 必须
			prop.put("mail.smtp.starttls.enable",
					"smtp.gmail.com".equals(host.toLowerCase()));
			logger.debug("mail.smtp.starttls.enable:{}",
					"smtp.gmail.com".equals(host.toLowerCase()));
			senderImpl.setJavaMailProperties(prop);
		}

		// 发送邮件
		senderImpl.send(mailMessage);
		logger.info("发送邮件 成功");
	}

	public static void main(String... agrs) {

//		try {
//			Mail mail = new Mail(ConstantUtils.SERVER_MAIL_HOST, ConstantUtils.SERVER_MAIL_USERNAME, ConstantUtils.SERVER_MAIL_PASSWORD);
//			String title = "今天报警状况";
//			String mailMsg = "<html>"+"<body><div style='text-align:center;'>"
//					+ "<h2>您好：沙雕 这是今天的报警情况信息</h2><h3>今天发生故障次数：10次</h3><table  width='600' cellpadding='0' cellspacing='0' border='1' style='margin:0 auto;'>"
//					+"<tr><th>异常分类</th><th>故障时间</th><th>维修时间</th></tr>"
//					+"<tr><td>异常1</td><td>9:00</td><td>9:00</td></tr>"
//					+"<tr><td>异常2</td><td>10:00</td><td>10:00</td></tr>"
//					+"<tr><td>异常3</td><td>11:00</td><td>10:00</td></tr>"
//					+ "</table>"
//					+ "<h3>机修信息</h3>"
//					+"<table  width='600' cellpadding='0' cellspacing='0' border='1' style='margin:0 auto;'><tr><th>机修人</th><th>抢单次数</th></tr>"
//					+"<tr><td>沙雕1</td><td>2</td></tr>"
//					+"<tr><td>沙嗲2</td><td>4</td></tr>"
//					+ "</table>"
//					+"</div></body>"+"</html>";
//
//			mail.sendMail("952049433@qq.com", ConstantUtils.SERVER_MAIL_USERNAME, title, mailMsg, null);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

	}
}
