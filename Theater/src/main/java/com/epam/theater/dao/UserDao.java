package com.epam.theater.dao;

import com.epam.theater.bean.User;

public interface UserDao extends AbstractDomainObjectDao<User> {
	int getFreeId();
}
