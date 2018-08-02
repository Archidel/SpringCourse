package com.epam.theater.service;

import com.epam.theater.bean.Event;
import com.epam.theater.service.exception.ServiceException;

public interface EventService extends AbstractDomainObjectService<Event> {

	static boolean checkString(String input) {
		return (input == null || input.isEmpty());
	}

	static boolean validate(String auditName, String eventName, String basePrice, String eventRating, String datetime) {
		return checkString(auditName) || checkString(eventName) || checkString(basePrice) || checkString(eventRating)
				|| checkString(datetime);
	}

	
	Event getByName(String name) throws ServiceException;

	void save(String auditName, String eventName, String basePrice, String eventRating, String datetime)
			throws ServiceException;
}
