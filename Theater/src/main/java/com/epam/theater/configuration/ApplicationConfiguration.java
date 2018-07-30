package com.epam.theater.configuration;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;

import static com.epam.theater.controller.Constants.PROPERTY_NAME_DB_URL;
import static com.epam.theater.controller.Constants.PROPERTY_NAME_DB_DRIVER;
import static com.epam.theater.controller.Constants.PROPERTY_NAME_DB_PASSWORD;
import static com.epam.theater.controller.Constants.PROPERTY_NAME_DB_USERNAME;

@ContextConfiguration
@ComponentScan("com.epam.theater")
@EnableAspectJAutoProxy
@PropertySource("classpath:database.properties")
public class ApplicationConfiguration {

	@Autowired
	Environment environment;

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(PROPERTY_NAME_DB_URL));
		driverManagerDataSource.setUsername(environment.getProperty(PROPERTY_NAME_DB_USERNAME));
		driverManagerDataSource.setPassword(environment.getProperty(PROPERTY_NAME_DB_PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(PROPERTY_NAME_DB_DRIVER));
		return driverManagerDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}

	@Bean
	public org.slf4j.Logger logger() {
		return LoggerFactory.getLogger("application");
	}

}
