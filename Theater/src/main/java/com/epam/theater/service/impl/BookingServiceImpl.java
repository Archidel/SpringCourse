package com.epam.theater.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.bean.Event;
import com.epam.theater.bean.EventRating;
import com.epam.theater.bean.Ticket;
import com.epam.theater.bean.User;
import com.epam.theater.dao.UserDao;
import com.epam.theater.service.BookingService;
import com.epam.theater.service.exception.ServiceException;

public class BookingServiceImpl implements BookingService {

	private UserDao userDao;

	@Override
	public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats)
			throws ServiceException {
		if (!event.airsOnDateTime(dateTime)) {
			throw new ServiceException("Airs on date time is false!");
		}

		double raitingCoefficient = 1;
		double vipSeatCoefficient = 1;
		double stackOfVipSeats = 0;
		double totalPrice = 0;

		if (event.getRating().equals(EventRating.HIGH)) {
			raitingCoefficient = 1.2;
		}

		NavigableMap<LocalDateTime, Auditorium> auditoriums = event.getAuditoriums();
		Auditorium auditorium = auditoriums.get(dateTime);
		Set<Long> vipSeats = auditorium.getVipSeats();

		for (Long userSeat : seats) {
			for (Long vipSeat : vipSeats) {
				if (vipSeat.equals(userSeat)) {
					vipSeatCoefficient = 2;
					stackOfVipSeats += 1;
				}
			}
		}

		if (stackOfVipSeats == 0) {
			totalPrice = event.getBasePrice() * raitingCoefficient * seats.size();
		} else {
			totalPrice = event.getBasePrice() * seats.size() * raitingCoefficient
					+ ((event.getBasePrice() * stackOfVipSeats * vipSeatCoefficient) / 2);
		}

		return totalPrice;
	}

	@Override
	public void bookTickets(Set<Ticket> tickets) {
		User user = null;

		for (Ticket ticket : tickets) {
			user = ticket.getUser();
		}

		user.setTickets(new TreeSet<Ticket>(tickets));
	}

	@Override
	public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) throws ServiceException {
		if (!event.airsOnDateTime(dateTime)) {
			throw new ServiceException("Airs on date time is false");
		}

		Collection<User> users = userDao.getAll();
		Set<Ticket> ticketList = new HashSet<>();

		for (User user : users) {
			NavigableSet<Ticket> tickets = user.getTickets();
			for (Ticket ticket : tickets) {
				if (ticket.getDateTime().equals(dateTime) && ticket.getEvent().equals(event)) {
					ticketList.add(ticket);
				}
			}
		}

		return ticketList;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
