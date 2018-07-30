package com.epam.theater.controller.command.impl.user;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "save_user")
public class SaveUser implements Command {
	
	@Autowired
	private Logger logger;

	@Autowired
	private UserService userService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String name = request.get(Constants.PARAM_NAME_USER_NAME);
		String surname = request.get(Constants.PARAM_NAME_USER_SURNAME);
		String email = request.get(Constants.PARAM_NAME_USER_EMAIL);

		try {
			userService.save(name, surname, email);
			response = "User saved successfully";
		} catch (ServiceException e) {
			logger.error("Command 'Save user' has been failed", e);
			response = "Command 'Save user' has been failed";
		}

		return response;
	}

}
