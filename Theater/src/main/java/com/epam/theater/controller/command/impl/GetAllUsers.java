package com.epam.theater.controller.command.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.theater.bean.User;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

public class GetAllUsers implements Command {
	private static final Logger logger = LoggerFactory.getLogger(GetAllUsers.class);

	private UserService userService;

	@Override
	public String execute(String request) {
		String response = null;

		try {
			Collection<User> users = userService.getAll();
			response = users.toString();
		} catch (ServiceException e) {
			response = "Error getting list of users";
			logger.error("Error of executing command", e);
		}

		return response;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
