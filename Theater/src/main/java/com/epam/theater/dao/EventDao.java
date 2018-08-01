package com.epam.theater.dao;

import com.epam.theater.bean.Event;
import com.epam.theater.service.exception.ServiceException;

public interface EventDao extends AbstractDomainObjectDao<Event> {
	public Event getByName(String name) throws ServiceException;
}
