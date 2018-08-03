package com.epam.theater.controller.command.impl.booking;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.BookingService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "booking_ticket")
public class BookingTicket implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private BookingService bookingService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String eventName = request.get(Constants.PARAM_NAME_EVENT_NAME);
		String seat = request.get(Constants.PARAM_NAME_TICKET_SEAT);
		String userEmail = request.get(Constants.PARAM_NAME_USER_EMAIL);
		String date = request.get(Constants.PARAM_NAME_EVENT_DATETIME);

		try {
			bookingService.bookTicket(eventName, date, userEmail, seat);
			response = "Ticket has been booked";
		} catch (ServiceException e) {
			logger.error("Command 'booking ticket' has been failed", e);
			response = "Command 'Booking ticket' has been failed";
		}

		return response;
	}

}
