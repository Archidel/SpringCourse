package com.epam.theater.dao;

import java.util.Set;

import com.epam.theater.bean.Auditorium;

public interface AuditoriumDao {

	public Set<Auditorium> getAll();

	public Auditorium getByName(String name);

	void save(Auditorium auditorium);

	void setSeatsByAuditoriumId(Set<Long> vipSeats, Long id);

}
