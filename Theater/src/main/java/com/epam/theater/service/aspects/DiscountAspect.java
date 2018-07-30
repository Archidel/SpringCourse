package com.epam.theater.service.aspects;

import java.util.Map;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.theater.bean.DataBase;
import com.epam.theater.bean.User;

//@Aspect
//@Component
public class DiscountAspect {

/*	@Autowired
	private DataBase dataBase;

	@After("execution(* com.epam.theater.service.DiscountService.getDiscount(..)) && (args(user,..))")
	public void count(User user) {
		Map<User, Integer> counters = dataBase.getDiscountCounter();
		Integer disCount = counters.get(user);

		if (disCount == null) {
			disCount = 0;
		}
		disCount++;

		dataBase.getDiscountCounter().put(user, disCount);
	}*/
}
