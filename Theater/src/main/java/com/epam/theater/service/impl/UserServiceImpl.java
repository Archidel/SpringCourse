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
	public Collection<User> getAll() throws ServiceException {
		Collection<User> users = userDao.getAll();
		if (users == null) {
			throw new ServiceException("User list is empty");
		}

		return users;
	}

	@Override
	public User getUserByEmail(String email) throws ServiceException {
		User user = userDao.getUserByEmail(email);

		if (user == null) {
			throw new ServiceException("User not found");
		}

		return user;
	}

	@Override
	public void remove(User user) throws ServiceException {
		if (user == null) {
			throw new ServiceException("User not found");
		}

		userDao.remove(user);
	}

	@Override
	public User getById(Long id) throws ServiceException {
		User user = userDao.getById(id);

		if (user == null) {
			throw new ServiceException("User not found");
		}

		return user;
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

}
