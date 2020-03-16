package com.sim.andon.web.core.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * ClassName: DatabaseMultiConfig <br/>
 * 多数据源设置 @Primary 表示默认 <br/>
 * date: 2015年2月4日 上午9:51:11 <br/>
 *
 * @author he.sun
 * @version
 * @since JDK 1.7
 */
@Configuration
public class DatabaseMultiConfig {
//	@Bean(name = "dbsdb1")
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource primaryDataSource() {
//		return DataSourceBuilder.create().build();
//	}

//	@Bean(name = "jdbcDb1")
//	@Primary
//	public JdbcTemplate jdbcTemplate1(DataSource dsUsers) {
//		return new JdbcTemplate(primaryDataSource());
//	}

	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "secondaryDataSource")
	@Qualifier("secondaryDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
