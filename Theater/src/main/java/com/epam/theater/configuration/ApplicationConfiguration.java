package com.epam.theater.configuration;

import java.util.HashMap;
import java.util.HashSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.bean.DataBase;
import com.epam.theater.bean.Event;
import com.epam.theater.bean.User;

@ContextConfiguration
@ComponentScan("com.epam.theater")
public class ApplicationConfiguration {

	@Bean
	public DataBase dataBase() {
		DataBase dataBase = new DataBase();
		dataBase.setUsers(new HashSet<User>());
		dataBase.setEvents(new HashSet<Event>());
		dataBase.setAuditoriums(new HashSet<Auditorium>());
		dataBase.setDiscountCounter(new HashMap<User, Integer>());
		return dataBase;
	}

}
