package com.sim.andon.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LicenseUtil {

	private static Properties props;

	public static Map<String, Object> isExpired(String path) throws Exception {
		props = new Properties();
		props.load(new FileInputStream(new File(path + "license.properties")));
		String propsSignature = props.getProperty("signature");
		path = path + props.getProperty("name");
		FileInputStream in = new FileInputStream(path);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate certificate = cf.generateCertificate(in);
		X509Certificate x509Certificate = (X509Certificate) certificate;
		Date today = new Date();
		Date notBefore = x509Certificate.getNotBefore();
		Date notAfter = x509Certificate.getNotAfter();
		Map<String, Object> map = new HashMap();
		long days=(notAfter.getTime()-today.getTime())/(1000*3600*24);
		map.put("days", days);
		String signature = new BigInteger(x509Certificate.getSignature()).toString(16);
		if (notBefore.getTime() <= today.getTime() && notAfter.getTime() >= today.getTime()
				&& signature.equals(propsSignature)) {
			map.put("flag", true);
			//return true;
		} else {
			map.put("flag", false);
			//return false;
		}
		return map;
	}

}
