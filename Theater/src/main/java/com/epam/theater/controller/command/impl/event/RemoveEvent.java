package com.epam.theater.controller.command.impl.event;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.EventService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "remove_event")
public class RemoveEvent implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private EventService eventService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String id = request.get(Constants.PARAM_NAME_EVENT_ID);

		try {
			eventService.remove(id);
			response = "Event was removed";
		} catch (ServiceException e) {
			logger.error("Command 'Remove event by id' has been failed", e);
			response = "Command 'Remove event by id' has been failed";
		}

		return response;
	}

}
