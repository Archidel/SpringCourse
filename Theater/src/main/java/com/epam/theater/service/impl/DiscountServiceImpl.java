package com.epam.theater.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NavigableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.theater.bean.Event;
import com.epam.theater.dao.EventDao;
import com.epam.theater.service.DiscountService;
import com.epam.theater.service.exception.ServiceException;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private EventDao eventDao;

	@Override
	public byte getDiscount(String userEmail, String eventName, String airDateTime, String numberOfTickets) throws ServiceException {
		if (DiscountService.validate(userEmail, eventName, airDateTime, numberOfTickets)) {
			throw new ServiceException("Invalid data for discount");
		}

		byte discount = 0;

		Event event = eventDao.getByName(eventName);
		LocalDateTime localDateTime = LocalDateTime.parse(airDateTime);
		Long number = Long.parseLong(numberOfTickets);

		NavigableSet<LocalDateTime> ldts = event.getAirDates();
		for (LocalDateTime dateTime : ldts) {
			LocalDateTime tempDateTime = LocalDateTime.from(dateTime);
			long days = tempDateTime.until(localDateTime, ChronoUnit.DAYS);

			if (days <= 5) {
				discount = 5;
			}

		}

		if (number == 10) {
			discount += 50;
		}

		return discount;
	}

}
