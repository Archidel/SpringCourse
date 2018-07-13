package com.epam.theater.service;

import java.time.LocalDateTime;

import com.epam.theater.bean.Event;
import com.epam.theater.bean.User;

public interface DiscountService {
	byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets);
}
