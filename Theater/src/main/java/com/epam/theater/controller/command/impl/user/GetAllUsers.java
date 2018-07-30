package com.epam.theater.controller.command.impl.user;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.User;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "get_all_users")
public class GetAllUsers implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private UserService userService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;

		try {
			Collection<User> users = userService.getAll();
			response = users.toString();
		} catch (ServiceException e) {
			logger.error("Command 'Get all users' has been failed", e);
			response = "Command 'Get all users' has been failed";
		}

		return response;
	}

}
