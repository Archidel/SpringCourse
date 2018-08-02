package com.epam.theater.service.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.User;

@Aspect
@Component
public class DiscountAspect {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@After("execution(* com.epam.theater.service.DiscountService.getDiscount(..)) && (args(user,..))")
	public void count(User user) {
		int count = jdbcTemplate.queryForObject("SELECT count(*) FROM statistic_discount WHERE sd_user_email = ?", new Object[] { user.getEmail() }, Integer.class);
		if(count == 0) {
			jdbcTemplate.update("UPDATE statistic_discount SET cs_user_discount = cs_user_discount + 1 where cs_user_email = ?", new Object[] { user.getEmail() }, String.class);
		}else {
			jdbcTemplate.update("INSERT INTO statistic_discount (sd_user_email, cs_user_discount) VALUES (?, ?)", user.getEmail(), 1);
		}
	}
}
