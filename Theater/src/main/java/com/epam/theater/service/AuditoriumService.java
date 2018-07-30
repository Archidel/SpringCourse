package com.epam.theater.service;

import java.util.Set;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.service.exception.ServiceException;

public interface AuditoriumService {
	Set<Auditorium> getAll() throws ServiceException;

	Auditorium getByName(String name) throws ServiceException;

	static boolean checkString(String input) {
		return (input == null || input.isEmpty());
	}
}
