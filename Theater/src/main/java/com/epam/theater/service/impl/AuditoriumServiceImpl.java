package com.epam.theater.service.impl;

import java.util.Set;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.dao.AuditoriumDao;
import com.epam.theater.service.AuditoriumService;
import com.epam.theater.service.exception.ServiceException;

public class AuditoriumServiceImpl implements AuditoriumService {

	private AuditoriumDao auditoriumDao;

	@Override
	public Set<Auditorium> getAll() throws ServiceException {
		Set<Auditorium> auditoriums = auditoriumDao.getAll();

		if (auditoriums == null) {
			throw new ServiceException("List of auditoriums not found!");
		}

		return auditoriums;
	}

	@Override
	public Auditorium getByName(String name) throws ServiceException {
		Auditorium auditorium = auditoriumDao.getByName(name);

		if (auditorium == null) {
			throw new ServiceException("Auditorium not found!");
		}

		return auditorium;
	}

	public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
		this.auditoriumDao = auditoriumDao;
	}

}
