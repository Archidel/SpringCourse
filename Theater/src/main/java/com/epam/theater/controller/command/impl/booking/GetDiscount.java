package com.epam.theater.controller.command.impl.booking;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.DiscountService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "get_discount")
public class GetDiscount implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private DiscountService discountService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String eventName = request.get(Constants.PARAM_NAME_EVENT_NAME);
		String seat = request.get(Constants.PARAM_NAME_USER_SEATS);
		String userEmail = request.get(Constants.PARAM_NAME_USER_EMAIL);
		String date = request.get(Constants.PARAM_NAME_EVENT_DATETIME);

		try {
			byte price = discountService.getDiscount(userEmail, eventName, date, seat);
			StringBuilder builder = new StringBuilder();
			response = builder.append("Discount: ").append(price).toString();
		} catch (ServiceException e) {
			logger.error("Command 'Get discount' has been failed", e);
			response = "Command 'Get discount' has been failed";
		}

		return response;
	}

}
