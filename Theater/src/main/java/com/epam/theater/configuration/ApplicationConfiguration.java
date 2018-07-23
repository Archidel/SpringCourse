package com.epam.theater.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;

import com.epam.theater.bean.DataBase;

@ContextConfiguration
@ComponentScan("com.epam.theater")
@EnableAspectJAutoProxy
public class ApplicationConfiguration {

	@Bean
	public DataBase dataBase() {
		return new DataBase();
	}

}
