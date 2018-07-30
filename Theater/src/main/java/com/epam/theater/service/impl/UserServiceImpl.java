package com.epam.theater.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.theater.bean.User;
import com.epam.theater.dao.UserDao;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void save(String name, String surname, String email) throws ServiceException {
		if (UserService.validate(name, surname, email)) {
			throw new ServiceException("Invalid user data");
		}

		userDao.save(new User(name, surname, email));
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
		if (UserService.checkString(email)) {
			throw new ServiceException("Invalid user's email");
		}

		User user = userDao.getUserByEmail(email);

		if (user == null) {
			throw new ServiceException("User not found");
		}

		return user;
	}

	@Override
	public void remove(String id) throws ServiceException {
		if (UserService.checkString(id)) {
			throw new ServiceException("Invalid user's id");
		}

		userDao.remove(Long.parseLong(id));
	}

	@Override
	public User getById(String id) throws ServiceException {
		if (UserService.checkString(id)) {
			throw new ServiceException("Invalid user's id");
		}

		return userDao.getById(Long.parseLong(id));
	}

}
