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

		User user = new User();
		user.setId(userDao.getFreeId());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);

		userDao.save(user);
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
	public void remove(String id) throws ServiceException {
		Long userId = Long.parseLong(id);
		User user = userDao.getById(userId);

		if (user == null) {
			throw new ServiceException("User not found");
		}

		userDao.remove(user);
	}

	@Override
	public User getById(String id) throws ServiceException {
		Long userId = Long.parseLong(id);
		User user = userDao.getById(userId);

		if (user == null) {
			throw new ServiceException("User not found");
		}

		return user;
	}

	@Override
	public User getUserByEmail(String email) throws ServiceException {
		if (UserService.valid(email)) {
			throw new ServiceException("Invalid email");
		}

		User user = userDao.getUserByEmail(email);

		if (user == null) {
			throw new ServiceException("User not found");
		}

		return user;
	}

}
