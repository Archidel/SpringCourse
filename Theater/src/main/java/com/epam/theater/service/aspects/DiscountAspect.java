package com.epam.theater.service.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.User;
import com.epam.theater.dao.UserDao;

@Aspect
@Component
public class DiscountAspect {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserDao userDao;
	
	@After("execution(* com.epam.theater.service.DiscountService.getDiscount(..)) && (args(userEmail,..))")
	public void count(String userEmail) {
		User user = userDao.getUserByEmail(userEmail);
		int count = jdbcTemplate.queryForObject("SELECT count(*) FROM statistic_discount WHERE sd_user_email = ?", new Object[] { user.getEmail() }, Integer.class);

		if(count == 0) {
			jdbcTemplate.update("INSERT INTO statistic_discount (sd_user_email, sd_user_discount) VALUES (?, ?)", user.getEmail(), 1);
		}else {
			jdbcTemplate.update("UPDATE statistic_discount SET sd_user_discount = sd_user_discount + 1 where sd_user_email = ?", user.getEmail());
		}
	}
}
