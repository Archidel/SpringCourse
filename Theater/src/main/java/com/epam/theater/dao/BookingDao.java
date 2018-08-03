package com.epam.theater.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.theater.bean.Ticket;

public interface BookingDao {
	void bookingTicket(Long userId, Long eventId, LocalDateTime localDateTime, Long seat);
	
	List<Ticket> getPurchasedTicketsForEvent(Long eventId, LocalDateTime localDateTime);
}
