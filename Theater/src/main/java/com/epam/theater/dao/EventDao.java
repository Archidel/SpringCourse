package com.epam.theater.dao;

import java.time.LocalDateTime;

import com.epam.theater.bean.Event;
import com.epam.theater.service.exception.ServiceException;

public interface EventDao extends AbstractDomainObjectDao<Event> {
	public Event getByName(String name) throws ServiceException;
	
	void setAirDatesByEventId(LocalDateTime ldt, Long id) throws ServiceException;
	
	void setAuditoriumToEvent(Long auditoriumId, Long eventId) throws ServiceException;
}
