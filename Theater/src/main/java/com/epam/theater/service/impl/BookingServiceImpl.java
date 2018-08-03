package com.epam.theater.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.bean.Event;
import com.epam.theater.bean.EventRating;
import com.epam.theater.bean.Ticket;
import com.epam.theater.bean.User;
import com.epam.theater.dao.AuditoriumDao;
import com.epam.theater.dao.BookingDao;
import com.epam.theater.dao.EventDao;
import com.epam.theater.dao.UserDao;
import com.epam.theater.service.BookingService;
import com.epam.theater.service.exception.ServiceException;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EventDao eventDao;

	@Autowired
	private AuditoriumDao auditoriumDao;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Transactional
	@Override
	public void bookTicket(String eventName, String date, String userEmail, String seatStr) throws ServiceException {
		if(BookingService.validate(eventName, date, userEmail, seatStr)){
			throw new ServiceException("Invalid ticket data");
		}
		
		LocalDateTime localDateTime = LocalDateTime.parse(date);
		Event event = eventDao.getByName(eventName);
		User user = userDao.getUserByEmail(userEmail);
		Long seat = Long.parseLong(seatStr);
		
		bookingDao.bookingTicket(user.getId(), event.getId(), localDateTime, seat);
	}

	@Override
	public List<Ticket> getPurchasedTicketsForEvent(String eventName, String dateTime) throws ServiceException {
		if(BookingService.validate(eventName, dateTime)){
			throw new ServiceException("Invalid get purchased data");
		}

		Event event = eventDao.getByName(eventName);
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
		List<Ticket> tickets = bookingDao.getPurchasedTicketsForEvent(event.getId(), localDateTime);

		if(tickets == null) {
			throw new ServiceException("Tickes is null by request");
		}
		
		return tickets;
	}

	@Transactional
	@Override
	public double getTicketsPrice(String eventName, String date, String userEmail, String seats) throws ServiceException {
		if(BookingService.validate(eventName, date, userEmail, seats)) {
			throw new ServiceException("Invalid input data");
		}
		String [] arrSeats = seats.split(SEPARATOR);
		Set<Long> userSeats = new TreeSet<Long>();
		for(String seat: arrSeats) {
			userSeats.add(Long.parseLong(seat));
		}
		
		Event event = eventDao.getByName(eventName);
		LocalDateTime localDateTime = LocalDateTime.parse(date);

		double raitingCoefficient = 1;
		double vipSeatCoefficient = 1;
		double stackOfVipSeats = 0;
		double totalPrice = 0;

		if (event.getRating().equals(EventRating.HIGH)) {
			raitingCoefficient = 1.2;
		}

		Auditorium auditorium = auditoriumDao.getByDate(new java.sql.Date(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
		Set<Long> vipSeats = auditorium.getVipSeats();

		for (Long uSeat : userSeats) {
			for (Long vipSeat : vipSeats) {
				if (vipSeat.equals(uSeat)) {
					vipSeatCoefficient = 2;
					stackOfVipSeats += 1;
				}
			}
		}

		if (stackOfVipSeats == 0) {
			totalPrice = event.getBasePrice() * raitingCoefficient * userSeats.size();
		} else {
			totalPrice = event.getBasePrice() * userSeats.size() * raitingCoefficient
					+ ((event.getBasePrice() * stackOfVipSeats * vipSeatCoefficient) / 2);
		}
		
		return totalPrice;
	}

}
