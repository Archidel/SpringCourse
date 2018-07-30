package com.epam.theater.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.theater.bean.Event;
import com.epam.theater.dao.EventDao;
import com.epam.theater.service.EventService;
import com.epam.theater.service.exception.ServiceException;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;

	@Override
	public void remove(String id) throws ServiceException {
		if (EventService.checkString(id)) {
			throw new ServiceException("Invalid event id");
		}

		eventDao.remove(Long.parseLong(id));
	}

	@Override
	public Event getById(String id) throws ServiceException {
		if (EventService.checkString(id)) {
			throw new ServiceException("Invalid event id");
		}

		Event event = eventDao.getById(Long.parseLong(id));

		if (event == null) {
			throw new ServiceException("Event not found by id");
		}

		return event;
	}

	@Override
	public Collection<Event> getAll() throws ServiceException {
		Collection<Event> events = eventDao.getAll();

		if (events == null) {
			throw new ServiceException("Collection of event not flound");
		}

		return events;
	}

	@Override
	public Event getByName(String name) throws ServiceException {
		if (EventService.checkString(name)) {
			throw new ServiceException("Invalid event name");
		}

		Event event = eventDao.getByName(name);

		if (event == null) {
			throw new ServiceException("Event not found by name");
		}

		return event;
	}

}
