package com.epam.theater.service;

import java.util.Collection;
import com.epam.theater.bean.DomainObject;
import com.epam.theater.service.exception.ServiceException;

public interface AbstractDomainObjectService<T extends DomainObject> {
	void remove(String id) throws ServiceException;

	T getById(String id) throws ServiceException;

	Collection<T> getAll() throws ServiceException;

}
