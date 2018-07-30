package com.epam.theater.dao.impl;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.theater.bean.User;
import com.epam.theater.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(User user) {
		jdbcTemplate.update("INSERT INTO user (u_id, u_firstname, u_lastname) VALUES (?, ?, ?)", user.getFirstName(),user.getLastName(), user.getEmail());
	}

	@Override
	public void remove(Long id) {
		jdbcTemplate.update("DELETE from user WHERE u_id = ? ", id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public User getById(Long id) {
		User user = (User) jdbcTemplate.queryForObject("SELECT * FROM user where u_id = ? ", new Object[] { id }, new BeanPropertyRowMapper(User.class));
		return user;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection<User> getAll() {
		List<User> persons = jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper(User.class));
		return persons;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public User getUserByEmail(String email) {
		User user = (User) jdbcTemplate.queryForObject("SELECT * FROM user where u_email = ? ", new Object[] { email }, new BeanPropertyRowMapper(User.class));
		return user;
	}

}
