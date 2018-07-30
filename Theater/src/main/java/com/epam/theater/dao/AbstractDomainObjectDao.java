package com.epam.theater.dao;

import java.util.Collection;

import com.epam.theater.bean.DomainObject;

public interface AbstractDomainObjectDao<T extends DomainObject> {
	public void save(T object);

	public void remove(Long id);

	public T getById(Long id);

	public Collection<T> getAll();
}
