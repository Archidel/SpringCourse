package com.epam.theater.service;

import java.time.LocalDateTime;
import java.util.Set;

import com.epam.theater.bean.Event;
import com.epam.theater.bean.Ticket;
import com.epam.theater.bean.User;

public interface BookingService {
	public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats);

	public void bookTickets(Set<Ticket> tickets);

	public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);

}
