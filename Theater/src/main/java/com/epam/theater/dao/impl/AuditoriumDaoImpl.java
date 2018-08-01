package com.epam.theater.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.bean.mapper.AuditoriumRowMapper;
import com.epam.theater.dao.AuditoriumDao;

@Repository
public class AuditoriumDaoImpl implements AuditoriumDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Set<Auditorium> getAll() {
		Set<Auditorium> auditoriums = new HashSet<Auditorium>(jdbcTemplate.query("SELECT * FROM auditorium", new AuditoriumRowMapper()));
		return auditoriums;
	}

	@Override
	public Auditorium getByName(String name) {
		Auditorium auditorium = (Auditorium) jdbcTemplate.queryForObject("SELECT * FROM auditorium where au_name = ? ", new Object[] { name }, new AuditoriumRowMapper());
		return auditorium;
	}

}
