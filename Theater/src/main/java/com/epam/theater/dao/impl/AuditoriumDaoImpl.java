package com.epam.theater.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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

	@Override
	public void save(Auditorium auditorium) {
		jdbcTemplate.update("INSERT INTO auditorium (au_name, au_number_of_seats) VALUES (?, ?)", auditorium.getName(), auditorium.getNumberOfSeats());
	}

	@Override
	public void setSeatsByAuditoriumId(Set<Long> vipSeats, Long id) {
		String sql = "INSERT IGNORE INTO vipseats (au_id, vp_seat_number) VALUES (?, ?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				for(Long seat : vipSeats){
					ps.setLong(1, id);
					ps.setLong(2, seat);
				}
			}
			
			@Override
			public int getBatchSize() {
				return vipSeats.size();
			}
		});
	}

}
