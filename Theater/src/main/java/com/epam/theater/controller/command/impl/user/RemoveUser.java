package com.epam.theater.controller.command.impl.user;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.User;
import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "remove_user")
public class RemoveUser implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private UserService userService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String id = request.get(Constants.PARAM_NAME_USER_ID);

		try {
			User user = userService.getById(id);
			response = user.toString();
		} catch (ServiceException e) {
			logger.error("Command 'Remove user' has been failed", e);
			response = "Command 'Remove user' has been failed";
		}

		return response;
	}

}