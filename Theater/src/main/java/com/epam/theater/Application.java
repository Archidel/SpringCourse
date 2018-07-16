package com.epam.theater;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.theater.bean.User;
import com.epam.theater.controller.Controller;
import com.epam.theater.controller.command.Command;
import com.epam.theater.controller.command.impl.user.GetAllUsers;
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
		Controller controller = (Controller) context.getBean("controller");
		User user = null;

		// Save user
		user = new User(4L, "Albert", "Zarankovich", "Albert_Zarankovich@epam.com");
		userService.save(user);

		// GetAll users
		try {
			Collection<User> users = userService.getAll();
			System.out.println(users.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting users", e);
		}

		// GetUserByEmail
		try {
			user = userService.getUserByEmail("Albert_Zarankovich@epam.com");
			System.out.println(user.toString());
		} catch (ServiceException e) {
			logger.error("Error of getting user: " + user, e);
		}

		// Remove user
		try {
			user = userService.getById(4L);
			userService.remove(user);
		} catch (ServiceException e) {
			logger.error("Error of removing user: " + user.toString(), e);
		}

		/*
		 * // Save user command = Controller.getCommand("user_save"); response =
		 * command.execute("Vladimir Ulyanov Vladimir_Ulyanov@epam.com 26.08.1995");
		 * printResponse(response);
		 * 
		 * // Get all users from DB command = Controller.getCommand("get_all_users");
		 * response = command.execute(null); printResponse(response);
		 * 
		 * // get user by id command = Controller.getCommand("get_user_by_id"); response
		 * = command.execute("2"); printResponse(response);
		 * 
		 * // remove user by id command = Controller.getCommand("user_remove"); response
		 * = command.execute("3"); printResponse(response);
		 */
	}

	private static void printResponse(String response) {
		if (response != null) {
			System.out.println(response);
		}
	}

}
