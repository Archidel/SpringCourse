package com.epam.theater.service;

import java.util.Set;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.service.exception.ServiceException;

public interface AuditoriumService {
	
	String SEPARATOR = ",";
	
	Set<Auditorium> getAll() throws ServiceException;

	Auditorium getByName(String name) throws ServiceException;

	void save(String name, String numberOfSeats, String vipSeats) throws ServiceException;
	
	static boolean checkString(String input) {
		return (input == null || input.isEmpty());
	}

	static boolean valid(String name, String numberOfSeats, String vipSeats) {
		return (name == null || name.isEmpty() || numberOfSeats == null || numberOfSeats.isEmpty() || vipSeats == null || vipSeats.isEmpty());
	}
	
}
