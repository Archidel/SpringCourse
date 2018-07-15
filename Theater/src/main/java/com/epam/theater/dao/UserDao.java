package com.epam.theater.dao;

import com.epam.theater.bean.User;

public interface UserDao extends AbstractDomainObjectDao<User> {
	Long getFreeId();
	
	User getUserByEmail(String email);
}
