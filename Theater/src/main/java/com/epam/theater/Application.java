package com.epam.theater;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.bean.Event;
import com.epam.theater.bean.EventRating;
import com.epam.theater.bean.Ticket;
import com.epam.theater.bean.User;
import com.epam.theater.service.AuditoriumService;
import com.epam.theater.service.BookingService;
import com.epam.theater.service.DiscountService;
import com.epam.theater.service.EventService;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private static UserService userService;
	private static AuditoriumService auditoriumService;
	private static BookingService bookingService;
	private static DiscountService discountService;
	private static EventService eventService;

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
		User user = null;

		// ###############################
		// ###### User functionality #####
		// ###############################

		// Save user
		userService.save(new User(4L, "Albert", "Zarankovich", "Albert_Zarankovich@epam.com"));
		printResponse("User has been saved");

		// GetAll users
		try {
			Collection<User> users = userService.getAll();
			printResponse(users.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting users", e);
		}

		// GetUserByEmail
		try {
			user = userService.getUserByEmail("Albert_Zarankovich@epam.com");
			printResponse(user.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting user: " + user, e);
		}

		// get user by ID
		try {
			user = userService.getById(2L);
			printResponse(user.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting user by id", e);
		}

		// Remove user
		try {
			user = userService.getById(2L);
			userService.remove(user);
			printResponse("User has been removed");
		} catch (ServiceException e) {
			logger.error("Error of removing user: " + user.toString(), e);
		}

		// ###############################
		// ### Auditorium functionality ##
		// ###############################

		// Auditorium getAll
		try {
			Collection<Auditorium> auditoriums = auditoriumService.getAll();
			printResponse(auditoriums.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting auditorium list", e);
		}

		// Auditorium getByName
		try {
			Auditorium auditorium = auditoriumService.getByName("auditorium_1");
			printResponse(auditorium.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting auditorium", e);
		}
		// ###############################
		// ##### Event functionality #####
		// ###############################

		// Event save
		LocalDateTime ldt1 = LocalDateTime.of(2018, Month.AUGUST, 8, 18, 00);
		LocalDateTime ldt2 = LocalDateTime.of(2018, Month.DECEMBER, 9, 12, 15);
		LocalDateTime ldt3 = LocalDateTime.of(2018, Month.JULY, 18, 11, 45);
		LocalDateTime ldt4 = LocalDateTime.of(2018, Month.JUNE, 20, 19, 00);

		try {
			Auditorium auditorium1 = auditoriumService.getByName("auditorium_2");
			Auditorium auditorium2 = auditoriumService.getByName("auditorium_3");
			Auditorium auditorium3 = auditoriumService.getByName("auditorium_4");
			Auditorium auditorium4 = auditoriumService.getByName("auditorium_5");

			// Evemt #1
			Event event = new Event();
			event.setName("Event #1");
			event.setBasePrice(100.0);
			event.setRating(EventRating.HIGH);
			event.addAirDateTime(ldt1);
			event.addAirDateTime(ldt2);
			event.assignAuditorium(ldt1, auditorium1);
			event.assignAuditorium(ldt2, auditorium2);
			eventService.save(event);
			printResponse("Event has been saved");

			// Evemt #2
			event = new Event();
			event.setName("Event #2");
			event.setBasePrice(15.0);
			event.setRating(EventRating.LOW);
			event.addAirDateTime(ldt3);
			event.addAirDateTime(ldt4);
			event.assignAuditorium(ldt3, auditorium3);
			event.assignAuditorium(ldt4, auditorium4);
			eventService.save(event);
			printResponse("Event has been saved");
		} catch (ServiceException e) {
			logger.error("Error save event", e);
		}

		// Event get by name
		try {
			Event event = eventService.getByName("Event #1");
			printResponse(event.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting event by name", e);
		}

		// Event get by name
		try {
			Event event = eventService.getByName("Event #1");
			printResponse(event.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting event by name", e);
		}

		// Event get by id
		try {
			Event event = eventService.getById(1L);
			printResponse(event.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting event by id", e);
		}

		// Event remove
		try {
			Event event = eventService.getById(0L);
			eventService.remove(event);
			printResponse("Event has been removed");
		} catch (ServiceException e) {
			logger.error("Error of removing event", e);
		}

		// ###############################
		// ### Booking functionality ##
		// ###############################

		// getPrice
		try {
			Event event = eventService.getByName("Event #2");
			user = userService.getUserByEmail("Albert_Zarankovich@epam.com");
			double totalprice = bookingService.getTicketsPrice(event, ldt3, user,
					new HashSet<Long>(Arrays.asList(6L, 66L)));
			printResponse("Total price: " + totalprice);
		} catch (ServiceException e) {
			logger.error("Error of booking", e);
		}

		// getPrice
		try {
			Event event = eventService.getByName("Event #2");
			user = userService.getUserByEmail("Albert_Zarankovich@epam.com");
			Set<Ticket> tickets = new HashSet<Ticket>();
			tickets.add(new Ticket(user, event, ldt3, 6L));
			tickets.add(new Ticket(user, event, ldt3, 66L));

			bookingService.bookTickets(tickets);
			printResponse("Tickets has been booked");
		} catch (ServiceException e) {
			logger.error("Error of booking", e);
		}

		// getPurchesTickes
		try {
			Event event = eventService.getByName("Event #2");
			Set<Ticket> tickets = bookingService.getPurchasedTicketsForEvent(event, ldt3);
			printResponse(tickets.toString());
		} catch (ServiceException e) {
			logger.error("Error of booking", e);
		}

	}

	private static void printResponse(String response) {
		if (response != null) {
			System.out.println(response);
		}
	}

	public static void setUserService(UserService userService) {
		Application.userService = userService;
	}

	public static void setAuditoriumService(AuditoriumService auditoriumService) {
		Application.auditoriumService = auditoriumService;
	}

	public static void setBookingService(BookingService bookingService) {
		Application.bookingService = bookingService;
	}

	public static void setDiscountService(DiscountService discountService) {
		Application.discountService = discountService;
	}

	public static void setEventService(EventService eventService) {
		Application.eventService = eventService;
	}

}
