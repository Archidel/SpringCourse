package com.epam.theater.controller.command.impl.auditorium;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.AuditoriumService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "save_auditorium")
public class SaveAuditorium implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private AuditoriumService auditoriumService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String name = request.get(Constants.PARAM_NAME_AUDITORIUM_NAME);
		String numberOfSeats = request.get(Constants.PARAM_NAME_AUDITORIUM_NUMBER_OF_SEATS);
		String vipSeats = request.get(Constants.PARAM_NAME_AUDITORIUM_VIP_SEATS);

		try {
			auditoriumService.save(name, numberOfSeats, vipSeats);
			response = "Auditorium has been added";
		} catch (ServiceException e) {
			logger.error("Command 'Save auditorium' has been failed", e);
			response = "Command 'Save auditorium' has been failed";
		}

		return response;
	}

}
