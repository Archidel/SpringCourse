package com.epam.theater.dao.impl;

import java.util.Collection;
import java.util.Optional;
import com.epam.theater.bean.DataBase;
import com.epam.theater.bean.Event;
import com.epam.theater.dao.EventDao;
import com.epam.theater.service.exception.ServiceException;

public class EventDaoImpl implements EventDao {

	private DataBase dataBase;

	@Override
	public void save(Event event) {
		dataBase.getEvents().add(event);
	}

	@Override
	public void remove(Event event) {
		dataBase.getEvents().remove(event);
	}

	@Override
	public Event getById(Long id) {
		Optional<Event> event = dataBase.getEvents().stream().filter(p -> p.getId().equals(id)).findFirst();
		return event.orElse(null);
	}

	@Override
	public Collection<Event> getAll() {
		return dataBase.getEvents();
	}

	@Override
	public Event getByName(String name) throws ServiceException {
		Optional<Event> event = dataBase.getEvents().stream().filter(p -> p.getName().equalsIgnoreCase(name))
				.findFirst();
		return event.orElse(null);
	}

	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}

}
