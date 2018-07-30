package com.epam.theater.service;

import java.time.LocalDateTime;
import java.util.Set;

import com.epam.theater.bean.Event;
import com.epam.theater.bean.Ticket;
import com.epam.theater.bean.User;
import com.epam.theater.service.exception.ServiceException;

public interface BookingService {
	double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats) throws ServiceException;

	void bookTickets(Set<Ticket> tickets);

	Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) throws ServiceException;

}
