package com.epam.theater.service;

import com.epam.theater.bean.Event;
import com.epam.theater.service.exception.ServiceException;

public interface EventService extends AbstractDomainObjectService<Event> {

	static boolean checkString(String input) {
		return (input == null || input.isEmpty());
	}

	Event getByName(String name) throws ServiceException;

}
