package com.epam.theater.bean.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.epam.theater.bean.Auditorium;

public class AuditoriumRowMapper implements RowMapper<Auditorium> {

	@Override
	public Auditorium mapRow(ResultSet rs, int i) throws SQLException {
		Auditorium auditorium = new Auditorium();
		auditorium.setName(rs.getString("au_id"));
		auditorium.setName(rs.getString("au_name"));
		auditorium.setNumberOfSeats(rs.getInt("au_number_of_seats"));
		return auditorium;
	}

}
