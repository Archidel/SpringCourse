package com.epam.theater.service.impl;

import java.util.Collection;
import com.epam.theater.bean.User;
import com.epam.theater.dao.UserDao;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(String firstName, String lastName, String email) throws ServiceException {
		if (!UserService.valid(firstName, lastName, email)) {
			throw new ServiceException("Invalid user data");
		}

		userDao.save(new User(firstName, lastName, email));
	}

	@Override
	public Collection<User> getAll() throws ServiceException {
		Collection<User> users = userDao.getAll();
		if (users == null) {
			throw new ServiceException("User list is empty");
		}

		return users;
	}

}
