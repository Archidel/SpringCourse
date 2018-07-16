package com.epam.theater.service;

import com.epam.theater.bean.Event;
import com.epam.theater.service.exception.ServiceException;

public interface EventService extends AbstractDomainObjectService<Event> {

	static boolean valid(String line) {
		return (line != null && !line.isEmpty());
	}

	public Event getByName(String name) throws ServiceException;

}
