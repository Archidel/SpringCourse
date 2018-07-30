package com.epam.theater.service;

import com.epam.theater.bean.User;
import com.epam.theater.service.exception.ServiceException;

public interface UserService extends AbstractDomainObjectService<User> {

	User getUserByEmail(String email) throws ServiceException;

	void save(String name, String surname, String email) throws ServiceException;

	static boolean validate(String name, String surname, String email) {
		return checkString(name) || checkString(surname) || checkString(email);
	}

	static boolean checkString(String input) {
		return (input == null || input.isEmpty());
	}

}
