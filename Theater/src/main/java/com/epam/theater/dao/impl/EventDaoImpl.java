package com.epam.theater.dao.impl;

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

	}

	@Override
	public void remove(Long id) {
		jdbcTemplate.update("DELETE from event WHERE ev_id = ?", id);
	}

	@Override
	public Event getById(Long id) {
		// TODO Auto-generated method stub
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


}
