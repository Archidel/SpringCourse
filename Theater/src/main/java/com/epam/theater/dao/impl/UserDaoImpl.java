package com.epam.theater.dao.impl;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.theater.bean.User;
import com.epam.theater.bean.mapper.UserRowMapper;
import com.epam.theater.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(User user) {
		jdbcTemplate.update("INSERT INTO user (u_firstname, u_lastname, u_email) VALUES (?, ?, ?)", user.getFirstName(),user.getLastName(), user.getEmail());
	}

	@Override
	public void remove(Long id) {
		jdbcTemplate.update("DELETE from user WHERE u_id = ?", id);
	}

	@Override
	public User getById(Long id) {
		return (User) jdbcTemplate.queryForObject("SELECT * FROM user where u_id = ? ", new Object[] { id }, new UserRowMapper());
	}

	@Override
	public Collection<User> getAll() {
		return jdbcTemplate.query("SELECT * FROM user", new UserRowMapper());
	}

	@Override
	public User getUserByEmail(String email) {
		return (User) jdbcTemplate.queryForObject("SELECT * FROM user where u_email = ? ", new Object[] { email }, new UserRowMapper());
	}

}
