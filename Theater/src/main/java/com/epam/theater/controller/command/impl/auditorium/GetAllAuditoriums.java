package com.epam.theater.controller.command.impl.auditorium;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.controller.command.Command;
import com.epam.theater.service.AuditoriumService;
import com.epam.theater.service.exception.ServiceException;

@Component(value = "get_all_auditoriums")
public class GetAllAuditoriums implements Command {

	@Autowired
	private Logger logger;

	@Autowired
	private AuditoriumService auditoriumService;

	@Override
	public String execute(Map<String, String> request) {
		String response = null;
		
		try {
			Collection<Auditorium> auditoriums = auditoriumService.getAll();
			response = auditoriums.toString();
		} catch (ServiceException e) {
			logger.error("Command 'Get all auditoriums' has been failed", e);
			response = "Command 'Get all auditoriums' has been failed";
		}

		return response;
	}

}
