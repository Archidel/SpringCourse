package com.epam.theater.dao.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.theater.bean.Auditorium;
import com.epam.theater.dao.AuditoriumDao;

@Repository
public class AuditoriumDaoImpl implements AuditoriumDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked" })
	@Override
	public Set<Auditorium> getAll() {
		Set<Auditorium> auditoriums = (Set<Auditorium>) jdbcTemplate.query("SELECT * FROM auditorium", new BeanPropertyRowMapper<Auditorium>(Auditorium.class));
		return auditoriums;
	}

	@Override
	public Auditorium getByName(String name) {
		Auditorium auditorium = (Auditorium) jdbcTemplate.queryForObject("SELECT * FROM auditorium where u_email = ? ", new Object[] { name }, new BeanPropertyRowMapper<Auditorium>(Auditorium.class));
		return auditorium;
	}

}
