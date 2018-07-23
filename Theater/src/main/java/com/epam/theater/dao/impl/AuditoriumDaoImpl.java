package com.epam.theater.dao.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.bean.DataBase;
import com.epam.theater.dao.AuditoriumDao;

@Repository
public class AuditoriumDaoImpl implements AuditoriumDao {

	@Autowired
	private DataBase dataBase;

	@Override
	public Set<Auditorium> getAll() {
		return dataBase.getAuditoriums();
	}

	@Override
	public Auditorium getByName(String name) {
		Optional<Auditorium> auditorium = dataBase.getAuditoriums().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
		return auditorium.orElse(null);
	}

	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}

}
