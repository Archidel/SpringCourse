package com.epam.theater.service;

import java.util.List;
import com.epam.theater.bean.Ticket;
import com.epam.theater.service.exception.ServiceException;

public interface BookingService {
	String SEPARATOR = ",";

	double getTicketsPrice(String eventName, String date, String userEmail, String seats) throws ServiceException;

	void bookTicket(String eventName, String date, String userEmail, String seats) throws ServiceException;

	List<Ticket> getPurchasedTicketsForEvent(String eventName, String dateTime) throws ServiceException;

	static boolean validate(String eventName, String date, String userEmail, String seats) {
		return checkString(eventName) || checkString(date) || checkString(userEmail) || checkString(seats);
	}

	static boolean validate(String eventName, String date) {
		return checkString(eventName) || checkString(date);
	}
	
	static boolean checkString(String input) {
		return (input == null || input.isEmpty());
	}

}
