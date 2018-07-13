package com.epam.theater.service;

import java.util.Set;

import com.epam.theater.bean.Auditorium;

public interface AuditoriumService {
	public Set<Auditorium> getAll();

	public Auditorium getByName(String name);
}
