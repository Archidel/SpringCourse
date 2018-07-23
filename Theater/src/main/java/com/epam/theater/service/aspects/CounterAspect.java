package com.epam.theater.service.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.DataBase;
import com.epam.theater.bean.Statistic;

@Aspect
@Component
public class CounterAspect {
	@Autowired
	private DataBase dataBase;

	@After("execution(* com.epam.theater.service.EventService.getByName(..))")
	public void getByName() {
		Statistic statistic = dataBase.getStatistic();
		statistic.setNameTimes(statistic.getNameTimes() + 1);
//		System.err.println(statistic.toString());
	}

	@Before("execution(* com.epam.theater.service.BookingService.bookTickets(..))")
	public void bookTickets() {
		Statistic statistic = dataBase.getStatistic();
		statistic.setTicketWasbookingTimes(statistic.getTicketWasbookingTimes() + 1);
//		System.err.println(statistic.toString());
	}

	@Before("execution(* com.epam.theater.service.BookingService.getTicketsPrice(..))")
	public void getTicketsPrice() {
		Statistic statistic = dataBase.getStatistic();
		statistic.setPriceTimes(statistic.getPriceTimes() + 1);
//		System.err.println(statistic.toString());
	}

}
