package com.epam.theater.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.bean.Event;
import com.epam.theater.bean.EventRating;
import com.epam.theater.bean.mapper.EventRowMapper;
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
		return jdbcTemplate.queryForObject("SELECT * FROM event where ev_id = ? ", new Object[] {id}, new EventRowMapper());
	}

	@Override
	public Event getByName(String name) throws ServiceException {
		Event event = (Event) jdbcTemplate.queryForObject("SELECT * FROM event where ev_name = ? ", new Object[] {name}, new EventRowMapper());
		event.setAirDates(getAirDatesByEventId(event.getId()));
		event.setAuditoriums(getAuditoriumsByEventId(event.getId()));
		return event;
	}

	@Override
	public void addAirDatesByEventId(LocalDateTime ldt, Long id) throws ServiceException {
		jdbcTemplate.update("INSERT INTO airDates (ad_date, ev_id) VALUES (?, ?)", java.sql.Date.valueOf(ldt.toLocalDate()), id);
	}

	@Override
	public void addAuditoriumToEvent(Long auditoriumId, Long eventId) throws ServiceException {
		jdbcTemplate.update("INSERT INTO event_has_auditorium (ev_id, au_id) VALUES (?, ?)", eventId, auditoriumId);
	}

	private TreeSet<LocalDateTime> getAirDatesByEventId(Long id) {
		TreeSet<LocalDateTime> airDates = new TreeSet<LocalDateTime>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from airDates");

		for (Map<String, Object> one : rows) {
			Date date = (Date) one.get("ad_date");
			airDates.add(new java.sql.Timestamp(date.getTime()).toLocalDateTime());
		}

		return airDates;
	}

	private NavigableMap<LocalDateTime, Auditorium> getAuditoriumsByEventId(final Long id) {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from event inner join event_has_auditorium eha on eha.ev_id = event.ev_id inner join auditorium audit on audit.au_id = eha.au_id where event.ev_id = ?", id);
		NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<LocalDateTime, Auditorium>();
		
		for (Map<String, Object> one : rows) {
			Auditorium auditorium = new Auditorium();
			auditorium.setName((String) one.get("au_name"));
			auditorium.setId(((long) (Integer) one.get("au_id")));
			auditorium.setNumberOfSeats((long) (Integer) one.get("au_number_of_seats"));
			auditorium.setVipSeats(getAuditVipSeatsByAuditId(auditorium.getId()));
			
			Set<LocalDateTime> dates = getAuditDateByAuditId(auditorium.getId());
			for(java.time.LocalDateTime date: dates) {
				auditoriums.put(date, auditorium);
			}
			
		}
		
		return auditoriums;
	}
	
	private Set<LocalDateTime> getAuditDateByAuditId(Long id) {
		Set<LocalDateTime> dates = new TreeSet<LocalDateTime>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from auditorium audit inner join auditorium_has_auditorium_date ahad on ahad.aud_id = audit.au_id inner join auditorium_date auditDate on auditDate.aud_id = ahad.aud_id where audit.au_id = ?", id);

		for (Map<String, Object> one : rows) {
			Date date = (Date) one.get("aud_date");
			dates.add(new java.sql.Timestamp(date.getTime()).toLocalDateTime());
		}

		return dates;
	}
	
	private Set<Long> getAuditVipSeatsByAuditId(Long id) {
		Set<Long> vipSeats = new TreeSet<Long>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from auditorium audit inner join vipSeats vip on audit.au_id = vip.au_id where audit.au_id = ?", id);

		for (Map<String, Object> one : rows) {
			vipSeats.add((long) (Integer) one.get("vp_seat_number"));
		}

		return vipSeats;
	}

	@Override
	public Collection<Event> getAll() {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from event");
		Collection<Event> events = new ArrayList<>();
		
		for (Map<String, Object> one : rows) {
			Event event = new Event();
			event.setId(((Integer) one.get("ev_id")).longValue());
			event.setName(((String) one.get("ev_name")));
			event.setRating(EventRating.valueOf((String) one.get("ev_raiting")));	
			event.setAuditoriums(getAuditoriumsByEventId(event.getId()));
			
			events.add(event);
		}
		
		return events;
	}
	
}
