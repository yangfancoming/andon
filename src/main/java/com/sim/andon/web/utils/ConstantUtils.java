package com.sim.andon.web.utils;

/**
 * 
 * @ClassName: ConstantUtils
 * @Description: 常量工具类
 * @author yuanqi.jing
 * @date 2015年2月4日 下午3:23:34
 *
 */
public class ConstantUtils {

	public static final String SERVER_PUSH_URL = PropertyUtil.PropertiesInfo("server.push");

	public static final String SERVER_SUPPORT_URL = PropertyUtil.PropertiesInfo("server.support");

	public static final String SERVER_GETONLINE_URL = PropertyUtil.PropertiesInfo("server.getOnline");
	
	public static final String SERVER_LEADERPUSH_URL = PropertyUtil.PropertiesInfo("server.leaderPush");

	public static final String SERVER_FINISH_URL = PropertyUtil.PropertiesInfo("server.finish");
	
	public static final String SERVER_WORKING_URL = PropertyUtil.PropertiesInfo("server.working");
	
	public static final String SERVER_MAIL_HOST = PropertyUtil.PropertiesInfo("email.smtp");// 邮箱服务器

	public static final String SERVER_MAIL_USERNAME = PropertyUtil.PropertiesInfo("email.from");// 发出邮件的邮箱账号

	public static final String SERVER_MAIL_PASSWORD = PropertyUtil.PropertiesInfo("email.pass");// 邮箱密码
	
	public static final String PERSON_LEADER = PropertyUtil.PropertiesInfo("person.leader");// 线长
	public static final String PERSON_WORKER = PropertyUtil.PropertiesInfo("person.worker");// 线长

	public static final String APP_USER_SESSIONID = "app_sessionid_";

	public static final String USER_IN_SESSION = "web_logon_user";

	public static final String APP_FLAG = "app";

	public static final String WEB_FLAG = "web";

	public static final String WEIXIN_TOKEN = "weixin_token";

	public static final String WEIXIN_TICKET = "weixin_ticket";

	public final static String LOCAL_IMAGE_SAVE_PATH = "D:/Andon/"; // 注释

	public static final String PREFIX_MACHINE = "andon:";

	public final static String IMAGE_URL = "http://localhost:8080/image/"; // 注释
	// ==========================================
	/**
	 * 附件类型
	 */
	public static final int ATTACHMENT_TYPE_TENDER_PDCA = 1;// PDCA

	public static final int ATTACHMENT_TYPE_TENDER_PSS = 2;// PSS

	// 上传附件默认编码
	public static final String MULTIPAR_DEFAULT_ENCODING = "UTF-8";
	public static final int MULTIPAR_MAX_IN_MEMORY_SIZE = 10240;
	public static final long MULTIPAR_MAX_UPLOAD_SIZE = 1024 * 1024 * 30;
	public static final String MULTIPAR_UPLOAD_TEMP_DIR = "D:/temp";

	public static final String PROCESS_PDCA = "pdca";// PDCA

	public static final String PROCESS_PSS = "pss";// PSS

	/**
	 * 附件后缀-可预览的文件后缀
	 */
	public static final String FILE_PREVIEW = ".xls,.xlsx,.doc,.docx,.pdf";// 可预览

	/**
	 * 附件-获取下载进度监听时存入session中的key
	 */
	public static final String PROGRESS_LISTENER_KEY = "upload_progress";
	
	/**
	 *web访问地址 
	 */
	public static final String WEB_INDEX = PropertyUtil.PropertiesInfo("web.index");
}
