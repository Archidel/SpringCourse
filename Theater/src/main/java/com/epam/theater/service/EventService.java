package com.epam.theater.service;

import java.util.Date;
import java.util.List;

import com.epam.theater.bean.Event;
import com.epam.theater.service.exception.ServiceException;

public interface EventService extends AbstractDomainObjectService<Event> {

	public Event getByName(String name) throws ServiceException;

	List<Event> getForDateRange(Date from, Date to) throws ServiceException;
}
