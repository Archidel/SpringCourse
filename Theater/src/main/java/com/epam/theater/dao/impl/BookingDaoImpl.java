package com.epam.theater.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.theater.bean.Ticket;
import com.epam.theater.dao.BookingDao;

@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void bookingTicket(Long userId, Long eventId, LocalDateTime localDateTime, Long seat) {
		jdbcTemplate.update("INSERT INTO ticket (t_seat, u_id, ev_id, t_date) VALUES (?, ?, ?, ?)", seat, userId, eventId, localDateTime);
	}

	@Override
	public List<Ticket> getPurchasedTicketsForEvent(Long eventId, LocalDateTime localDateTime) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from ticket inner join event on event.ev_id = ticket.ev_id where event.ev_id = ? and ticket.t_date = ?", eventId, localDateTime);
		
		for (Map<String, Object> one : rows) {
			Ticket ticket = new Ticket();
			ticket.setId(((Integer) one.get("t_id")).longValue());
			ticket.setSeat(((Integer) one.get("t_seat")).longValue());
			ticket.setDateTime(localDateTime);
			tickets.add(ticket);
		}

		return tickets;
	}

}
