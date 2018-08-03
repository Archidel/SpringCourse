package com.epam.theater.controller.command.impl.booking;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.Ticket;
import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.BookingService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "get_purchased_tickets")
public class GetPurchasedTicketsForEvent implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private BookingService bookingService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String eventName = request.get(Constants.PARAM_NAME_EVENT_NAME);
		String date = request.get(Constants.PARAM_NAME_EVENT_DATETIME);

		try {
			List<Ticket> tickets = bookingService.getPurchasedTicketsForEvent(eventName, date);
			response = tickets.toString();
		} catch (ServiceException e) {
			logger.error("Command 'get purchased tickets' has been failed", e);
			response = "Command 'get purchased tickets' has been failed";
		}

		return response;
	}

}
