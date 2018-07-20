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
	public Collection<Event> getAll() throws ServiceException {
		Collection<Event> events = eventDao.getAll();
		if (events == null) {
			throw new ServiceException("Events not found");
		}

		return events;
	}

	@Override
	public Event getByName(String name) throws ServiceException {
		if (!EventService.valid(name)) {
			throw new ServiceException("Event's name is not valid!");
		}

		Event event = eventDao.getByName(name);

		if (event == null) {
			throw new ServiceException("Event not found");
		}

		return event;
	}

	@Override
	public void remove(Event event) throws ServiceException {
		if (event == null) {
			throw new ServiceException("Event not found");
		}

		eventDao.remove(event);

	}

	@Override
	public Event getById(Long id) throws ServiceException {
		Event event = eventDao.getById(id);

		if (event == null) {
			throw new ServiceException("Event not found");
		}

		return event;
	}

	@Override
	public void save(Event event) {
		event.setId(eventDao.getFreeId());
		eventDao.save(event);
	}

}
