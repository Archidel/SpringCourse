package com.epam.theater.bean.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.theater.bean.Event;
import com.epam.theater.bean.EventRating;

public class EventRowMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setId(rs.getLong("ev_id"));
		event.setName(rs.getString("ev_name"));
		event.setBasePrice(rs.getDouble("ev_price"));
		event.setRating(EventRating.valueOf(rs.getString("ev_raiting")));
		return event;
	}
}
