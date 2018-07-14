package com.epam.theater.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.theater.controller.command.Command;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

public class RemoveUser implements Command {
	private static final Logger logger = LoggerFactory.getLogger(RemoveUser.class);

	private UserService userService;

	@Override
	public String execute(String request) {
		String[] userData = request.split(SEPARATOR);
		String id = userData[0];
		String response = null;
		
		try {
			userService.remove(id);
			response = "User was removed successful";
		} catch (ServiceException e) {
			response = "Error getting of user";
			logger.error("Error of executing command", e);
		}

		return response;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
