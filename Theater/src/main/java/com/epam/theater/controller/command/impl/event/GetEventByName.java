package com.epam.theater.controller.command.impl.event;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.Event;
import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.EventService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "get_event_by_name")
public class GetEventByName implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private EventService eventService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String name = request.get(Constants.PARAM_NAME_EVENT_NAME);

		try {
			Event event = eventService.getByName(name);
			response = event.toString();
		} catch (ServiceException e) {
			logger.error("Command 'Get event by name' has been failed", e);
			response = "Command 'Get event by name' has been failed";
		}

		return response;
	}

}
