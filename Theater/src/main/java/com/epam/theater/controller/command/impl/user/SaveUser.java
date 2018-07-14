package com.epam.theater.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.theater.controller.command.Command;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

public class SaveUser implements Command {
	private static final Logger logger = LoggerFactory.getLogger(SaveUser.class);

	private UserService userService;

	@Override
	public String execute(String request) {
		String[] userData = request.split(SEPARATOR);
		String firstName = userData[0];
		String lastName = userData[1];
		String email = userData[2];
		String response = null;

		try {
			userService.save(firstName, lastName, email);
			response = "Save user has been successful";
		} catch (ServiceException e) {
			response = "Error user save";
			logger.error("Error of executing command", e);
		}

		return response;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
