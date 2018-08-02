package com.epam.theater.dao.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.theater.bean.Event;
import com.epam.theater.dao.EventDao;
import com.epam.theater.service.exception.ServiceException;

@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Event event) {
		jdbcTemplate.update("INSERT INTO event (ev_name, ev_price, ev_raiting) VALUES (?, ?, ?)", event.getName(), event.getBasePrice(), event.getRating().toString());
	}

	@Override
	public void remove(Long id) {
		jdbcTemplate.update("DELETE from event WHERE ev_id = ?", id);
	}

	@Override
	public Event getById(Long id) {
		return null;
	}

	@Override
	public Collection<Event> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getByName(String name) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAirDatesByEventId(LocalDateTime ldt, Long id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAuditoriumToEvent(Long auditoriumId, Long eventId) throws ServiceException {
		// TODO Auto-generated method stub
		
	}


}
