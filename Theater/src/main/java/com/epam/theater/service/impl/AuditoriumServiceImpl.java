package com.epam.theater.service.impl;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.dao.AuditoriumDao;
import com.epam.theater.service.AuditoriumService;
import com.epam.theater.service.exception.ServiceException;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
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
		if (AuditoriumService.checkString(name)) {
			throw new ServiceException("Invalid auditorium name");
		}

		Auditorium auditorium = auditoriumDao.getByName(name);

		if (auditorium == null) {
			throw new ServiceException("Auditorium not found!");
		}

		return auditorium;
	}

	@Transactional
	@Override
	public void save(String name, String numberOfSeats, String vipSeats) throws ServiceException {
		if (AuditoriumService.valid(name, numberOfSeats, vipSeats)) {
			throw new ServiceException("Invalid auditorium data");
		}

		try {
			long number = Long.parseLong(numberOfSeats);
			String[] arrVips = vipSeats.split(SEPARATOR);
			Set<Long> seats = new TreeSet<>();

			for (String seat : arrVips) {
				seats.add(Long.parseLong(seat));
			}

			auditoriumDao.save(new Auditorium(name, number, seats));
			Auditorium auditorium = auditoriumDao.getByName(name);
			auditoriumDao.setSeatsByAuditoriumId(seats, auditorium.getId());
		} catch (NumberFormatException e) {
			throw new ServiceException(e);
		}

	}

}
