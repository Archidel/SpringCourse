package com.epam.theater.bean.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.theater.bean.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("u_id"));
		user.setFirstName(rs.getString("u_firstname"));
		user.setLastName(rs.getString("u_lastname"));
		user.setEmail(rs.getString("u_email"));
		return user;
	}
}
