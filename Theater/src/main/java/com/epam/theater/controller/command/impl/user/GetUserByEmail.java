package com.epam.theater.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.theater.bean.User;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

public class GetUserByEmail implements Command {
	private static final Logger logger = LoggerFactory.getLogger(GetUserByEmail.class);

	private UserService userService;

	@Override
	public String execute(String request) {
		String response = null;
		String[] userData = request.split(SEPARATOR);
		String email = userData[0];

		try {
			User user = userService.getUserByEmail(email);
			response = user.toString();
		} catch (ServiceException e) {
			response = "Error getting of user by email";
			logger.error("Error of executing command", e);
		}

		return response;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
