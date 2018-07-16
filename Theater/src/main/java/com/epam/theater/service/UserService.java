package com.epam.theater.service;

import com.epam.theater.bean.User;
import com.epam.theater.service.exception.ServiceException;

public interface UserService extends AbstractDomainObjectService<User> {

	 public User getUserByEmail(String email) throws ServiceException;
}
