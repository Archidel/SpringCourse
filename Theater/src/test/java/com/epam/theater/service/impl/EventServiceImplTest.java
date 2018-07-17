package com.epam.theater.service.impl;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.epam.theater.bean.Event;
import com.epam.theater.bean.EventRating;
import com.epam.theater.bean.User;
import com.epam.theater.service.AuditoriumService;
import com.epam.theater.service.EventService;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class EventServiceImplTest {

	private ApplicationContext context;
	private UserService userService;
	private AuditoriumService auditoriumService;
	private EventService eventService;
	private User user;

	@Before
	public void setup() throws ServiceException {
		context = new ClassPathXmlApplicationContext("appContext.xml");
		userService = (UserService) context.getBean("userService");
		eventService = (EventService) context.getBean("eventService");
		auditoriumService = (AuditoriumService) context.getBean("auditoriumService");
		user = new User(100L, "TestFirstname", "TestLastName", "TestFirstname_TestLastName@epam.com");
		userService.save(user);

		Set<Long> vipSeats = new HashSet<Long>();
		vipSeats.add(1L);
		vipSeats.add(2L);
		vipSeats.add(3L);
		vipSeats.add(4L);
		vipSeats.add(5L);

		LocalDateTime ldt = LocalDateTime.of(2018, Month.AUGUST, 26, 12, 00);

		Event event = new Event();
		event.setName("TestEvent");
		event.setBasePrice(100.0);
		event.setRating(EventRating.MID);
		event.addAirDateTime(ldt);
		event.assignAuditorium(ldt, auditoriumService.getByName("testAuditorium"));
		eventService.save(event);
	}

	@Test
	public void getEventById_shouldReturnEvent() throws ServiceException {
		Event event = eventService.getByName("TestEvent");
		assertNotNull(event);
		assertEquals(1, event.getAuditoriums().size());
	}

	@Test(expected = ServiceException.class)
	public void getEventById_shouldthrowException_whenInvalidId() throws ServiceException {
		Event event = eventService.getByName("invalidId");
		assertNull(event);
	}

}
