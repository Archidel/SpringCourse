package com.epam.theater.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
		return new HashSet<Auditorium>(jdbcTemplate.query("SELECT * FROM auditorium", new AuditoriumRowMapper()));
	}

	@Override
	public Auditorium getByName(String name) {
		return (Auditorium) jdbcTemplate.queryForObject("SELECT * FROM auditorium where au_name = ? ", new Object[] { name }, new AuditoriumRowMapper());
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

	@Override
	public Auditorium getByDate(Date date) {
		Auditorium auditorium =  (Auditorium) jdbcTemplate.queryForObject(" select * from auditorium audit inner join auditorium_has_auditorium_date ahad on ahad.aud_id = audit.au_id inner join auditorium_date auditDate on auditDate.aud_id = ahad.aud_id where auditDate.aud_date = ?", new Object[] { date }, new AuditoriumRowMapper());
		auditorium.setVipSeats(getAuditVipSeatsByAuditId(auditorium.getId()));
		return auditorium;
	}

	private Set<Long> getAuditVipSeatsByAuditId(Long id) {
		Set<Long> vipSeats = new TreeSet<Long>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from auditorium audit inner join vipSeats vip on audit.au_id = vip.au_id where audit.au_id = ?", id);

		for (Map<String, Object> one : rows) {
			vipSeats.add(((Integer) one.get("vp_seat_number")).longValue());
		}

		return vipSeats;
	}
	
}
