package com.epam.theater.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NavigableSet;

import org.springframework.stereotype.Service;

import com.epam.theater.bean.Event;
import com.epam.theater.bean.User;
import com.epam.theater.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Override
	public byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
		byte discount = 0;

		NavigableSet<LocalDateTime> dates = event.getAirDates();
		for (LocalDateTime ldt : dates) {

			LocalDateTime tempDateTime = LocalDateTime.from(ldt);
			long days = tempDateTime.until(airDateTime, ChronoUnit.DAYS);
			if (days <= 5) {
				discount = 5;
			}
		}

		if (numberOfTickets == 10) {
			discount += 50;
		}

		return discount;
	}

}
