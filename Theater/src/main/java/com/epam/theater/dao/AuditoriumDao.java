package com.epam.theater.dao;

import java.sql.Date;
import java.util.Set;

import com.epam.theater.bean.Auditorium;

public interface AuditoriumDao {

	public Set<Auditorium> getAll();

	public Auditorium getByName(String name);

	void save(Auditorium auditorium);

	void setSeatsByAuditoriumId(Set<Long> vipSeats, Long id);
	
	Auditorium getByDate(Date date);

}
