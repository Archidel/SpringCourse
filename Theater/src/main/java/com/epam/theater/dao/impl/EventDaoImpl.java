package com.epam.theater.dao.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.theater.bean.DataBase;
import com.epam.theater.bean.Event;
import com.epam.theater.dao.EventDao;
import com.epam.theater.service.exception.ServiceException;

@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
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

	@Override
	public Long getFreeId() {
		Set<Event> events = dataBase.getEvents();
		Long eventSize = (long) events.size();
		Long freeId;

		while (true) {
			if (idIsFree(events, eventSize)) {
				freeId = eventSize;
				break;
			} else {
				idIsFree(events, eventSize++);
			}
		}

		return freeId;
	}

	private boolean idIsFree(Set<Event> events, Long id) {
		boolean isFree = true;
		for (Event event : events) {
			if (event.getId() == id) {
				isFree = false;
			}
		}
		return isFree;
	}

}
