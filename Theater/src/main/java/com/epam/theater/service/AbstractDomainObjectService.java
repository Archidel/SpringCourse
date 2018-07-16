package com.epam.theater.service;

import java.util.Collection;
import com.epam.theater.bean.DomainObject;
import com.epam.theater.bean.User;
import com.epam.theater.service.exception.ServiceException;

public interface AbstractDomainObjectService<T extends DomainObject> {
	public void remove(T object) throws ServiceException;

	public T getById(Long id) throws ServiceException;

	public Collection<T> getAll() throws ServiceException;

	public void save(T object);
}
