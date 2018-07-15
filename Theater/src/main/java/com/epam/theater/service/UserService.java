package com.epam.theater.service;

import java.util.Collection;

import com.epam.theater.bean.User;
import com.epam.theater.service.exception.ServiceException;

public interface UserService {

	static boolean valid(String line) {
		return (line != null && !line.isEmpty());
	}

	static boolean valid(String firstName, String lastName, String email) {
		return (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty() && email != null
				&& !email.isEmpty());
	}

	void save(String firstName, String lastName, String email) throws ServiceException;

	Collection<User> getAll() throws ServiceException;

	void remove(String id) throws ServiceException;

	User getById(String id) throws ServiceException;

	User getUserByEmail(String email) throws ServiceException;
}
