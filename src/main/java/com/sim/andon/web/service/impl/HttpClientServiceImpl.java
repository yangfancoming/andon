package com.sim.andon.web.service.impl;

import com.sim.andon.web.service.HttpClientService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;


@Service
public class HttpClientServiceImpl implements HttpClientService {

	private Logger log = LoggerFactory.getLogger(HttpClientServiceImpl.class);
	
	@Override
	public String doGet(String url,String charset){
		String data = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			if(StringUtils.isEmpty(charset)){
				charset = "UTF-8";
			}
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
	        try {
	        	response.setHeader("Content-Type", "application/x-www-form-urlencoded");
	        	log.info("HTTP Get 请求状态:"+  response.getStatusLine());
	        	HttpEntity httpEntity = response.getEntity();
	            data = EntityUtils.toString(httpEntity,Charset.forName(charset));
//	            log.info(" HTTP Get 请求成功 ! 服务器返回:" + data);
	        } finally {
	            response.close();
	        }
		}catch(Exception e){
			log.error("HTTP Get请求异常:"+e.getMessage());
		} finally {
            try {
				httpclient.close();
			} catch (IOException e) {
				log.error("HTTP Get Httpclient close异常:"+e.getMessage());
			}
        }
		return data;
	}

	public String doPost(String url,HttpEntity httpEntity,String charset,String contentType,String contentValue){
		String data = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	if(StringUtils.isEmpty(charset)){
				charset = "UTF-8";
			}
        	log.info("HttpClientService Post URL:" + url);
        	HttpPost httpPost = new HttpPost(url);
        	httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
            	if(StringUtils.isNotEmpty(contentType) && StringUtils.isNotEmpty(contentValue)){
            		((AbstractHttpMessage) response).setHeader(contentType, contentValue);
            	}
            	log.info("HTTP Post 请求状态:"+  response.getStatusLine());
                HttpEntity entity = response.getEntity();
                data = EntityUtils.toString(entity,Charset.forName(charset));
                log.info("请求返回结果:" + data);
	            log.info("HTTP Post 请求成功.");
            } finally {
                response.close();
            }
        }catch(Exception e){
			log.error("HTTP Post请求异常:"+e.getMessage());
        } finally {
            try {
				httpclient.close();
			} catch (IOException e) {
				log.error("HTTP Post Httpclient close异常:"+e.getMessage());
			}
        }
		return data;
	}


}
