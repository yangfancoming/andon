/**
 * Project Name:guoren-xintianyou-web
 * File Name:FilterConfig.java
 * Package Name:guoren.xintianyou.web.core.config
 * Date:2015年2月5日下午3:26:49
 * Copyright (c) 2015, www.sim.com All Rights Reserved.
 *
 */

package com.sim.andon.web.core.config;

import com.sim.andon.web.filter.LoginFilter;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:FilterConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年2月5日 下午3:26:49 <br/>
 * 
 * @author he.sun
 * @version
 * @since JDK 1.7
 * @see
 */
@Configuration
public class FilterConfig {
    
    @Bean(name = "LoginFilter")
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new LoginFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }
	
    @Bean(name = "sitemeshFilter")
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new ConfigurableSiteMeshFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
}
