package com.epam.theater.service;

import java.util.Set;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.service.exception.ServiceException;

public interface AuditoriumService {
	public Set<Auditorium> getAll() throws ServiceException;

	public Auditorium getByName(String name) throws ServiceException;
}
