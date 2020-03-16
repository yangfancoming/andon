package com.sim.andon.web.utils;

public class Common {

	public static final String LOGIN_USER_IN_SESSION_MSG = "CURRENT_USER";
	public static final String USER_LOGIN_PWD_DEF = "123456";

	// public static final String UPLOAD_FILE_DISK_PATH = "F:/img";
	// public static final String UPLOAD_FILE_ACCESS_PATH =
	// "http://localhost:8080/img";
	// public static final String UPLOAD_FILE_DISK_PATH = "/andon/img";
	// public static final String UPLOAD_FILE_ACCESS_PATH =
	// "http://localhost:8095/img";

	public static final String UPLOAD_PATH = "E:/upload/";
	public static final String UPLOAD_ACCESS_PATH = "http://localhost:8095/upload/";

	public static final int KEY_LENGTH = 6;
	public static final String KEY_DATE_FORMAT = "yyyyMMdd";
	public static final String KEY_INVENTEL_HEAD = "IN";

	public static final String PERSON_POSITION_CODE_BAOYANGRENYUAN = "保养";
	//public static final String PERSON_POSITION_CODE_WEIXIURENYUAN = "修";
	public static final String PERSON_POSITION_CODE_WEIXIURENYUAN = PropertyUtil.PropertiesInfo("person.worker");
	public static final String PERSON_POSITION_CODE_SHENGCHANRENYUAN = "生产人员";
	public static final String PERSON_POSITION_CODE_GONGCHENGSHI = "工程师";
	public static final String PERSON_POSITION_CODE_KEJINGLI = "科经理";
	public static final String PERSON_POSITION_CODE_BUMENJINGLI = "部门经理";
	//public static final String PERSON_POSITION_CODE_XIANZHANG = "线长";
	public static final String PERSON_POSITION_CODE_XIANZHANG = PropertyUtil.PropertiesInfo("person.leader");
	public static final String PERSON_POSITION_CODE_CAOZUORENYUAN = "操作";
	
	public static final String SMT_TYPE_FUJI = "FUJI";// 富士贴片机
	public static final String SMT_TYPE_PANASONIC = "Panasonic";// 松下贴片机
	
	public static final String[] PERSON_DEPTS = {"MOE1","MFE1","MOF1"};
}
