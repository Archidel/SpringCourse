package com.epam.theater.service.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CounterAspect {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@After("execution(* com.epam.theater.service.EventService.getByName(..))")
	public void getByName() {
		jdbcTemplate.update("UPDATE statistic_counter SET cs_get_name_times = cs_get_name_times + 1 where cs_id = 1");
	}

	@Before("execution(* com.epam.theater.service.BookingService.bookTickets(..))")
	public void bookTickets() {
		jdbcTemplate.update("UPDATE statistic_counter SET cs_ticket_was_booking_times = cs_ticket_was_booking_times + 1 where cs_id = 1");
	}

	@Before("execution(* com.epam.theater.service.BookingService.getTicketsPrice(..))")
	public void getTicketsPrice() {
		jdbcTemplate.update("UPDATE statistic_counter SET cs_get_price_times = cs_get_price_times + 1 where cs_id = 1");
	}
}
