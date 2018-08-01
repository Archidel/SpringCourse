package com.epam.theater.controller.command.impl.event;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.EventService;
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
		String auditName = request.get(Constants.PARAM_NAME_AUDITORIUM_NAME);
		String eventName = request.get(Constants.PARAM_NAME_EVENT_NAME);
		String eventBasePrice = request.get(Constants.PARAM_NAME_EVENT_BASE_PRICE);
		String eventRating = request.get(Constants.PARAM_NAME_EVENT_RATING);
		String eventDate = request.get(Constants.PARAM_NAME_EVENT_DATETIME);

		try {
			eventService.save(auditName, eventName, eventBasePrice, eventRating, eventDate);
			response = "Event was saved";
		} catch (ServiceException e) {
			logger.error("Command 'save event' has been failed", e);
			response = "Command 'save event' has been failed";
		}

		return response;
	}

}
