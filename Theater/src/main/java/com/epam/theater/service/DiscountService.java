package com.epam.theater.service;

import com.epam.theater.service.exception.ServiceException;

public interface DiscountService {
	byte getDiscount(String userEmail, String eventName, String airDateTime, String numberOfTickets) throws ServiceException;
	
	static boolean validate(String userEmail, String eventName, String airDateTime, String numberOfTickets) {
		return checkString(userEmail) || checkString(eventName) || checkString(airDateTime) || checkString(numberOfTickets);
	}

	static boolean checkString(String input) {
		return (input == null || input.isEmpty());
	}
	
}
