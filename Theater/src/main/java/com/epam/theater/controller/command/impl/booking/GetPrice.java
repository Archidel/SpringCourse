package com.epam.theater.controller.command.impl.booking;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.BookingService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "get_price")
public class GetPrice implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private BookingService bookingService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String eventName = request.get(Constants.PARAM_NAME_EVENT_NAME);
		String seats = request.get(Constants.PARAM_NAME_USER_SEATS);
		String userEmail = request.get(Constants.PARAM_NAME_USER_EMAIL);
		String date = request.get(Constants.PARAM_NAME_EVENT_DATETIME);

		try {
			double price = bookingService.getTicketsPrice(eventName, date, userEmail, seats);
			StringBuilder builder = new StringBuilder();
			response = builder.append("User: ").append(userEmail).append(" got price: " + price).toString();
		} catch (ServiceException e) {
			logger.error("Command 'Get price' has been failed", e);
			response = "Command 'Get price' has been failed";
		}

		return response;
	}

}
