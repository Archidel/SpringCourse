package com.epam.theater.controller.command.impl.auditorium;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.controller.Constants;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.AuditoriumService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "get_auditorium_by_name")
public class GetAuditoriumsByName implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private AuditoriumService auditoriumService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		String name = request.get(Constants.PARAM_NAME_AUDITORIUM_NAME);
		
		try {
			Auditorium auditorium = auditoriumService.getByName(name);
			response = auditorium.toString();
		} catch (ServiceException e) {
			logger.error("Command 'Get auditorium by name' has been failed", e);
			response = "Command 'Get auditorium by name' has been failed";
		}

		return response;
	}

}
