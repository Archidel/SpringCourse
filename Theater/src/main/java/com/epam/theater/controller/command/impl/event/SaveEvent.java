package com.epam.theater.controller.command.impl.event;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.User;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.EventService;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "save_event")
public class SaveEvent implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private EventService eventService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
/*
		try {
//			response = users.toString();
		} catch (ServiceException e) {
			logger.error("Command 'Get all users' has been failed", e);
			response = "Command 'Get all users' has been failed";
		}
*/
		return response;
	}

}
