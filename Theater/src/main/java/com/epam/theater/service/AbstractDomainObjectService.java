package com.epam.theater.service;

import java.util.Collection;
import com.epam.theater.bean.DomainObject;

public interface AbstractDomainObjectService<T extends DomainObject> {
	public T save(T object);

	public void remove(T object);

	public T getById(Long id);

	public Collection<T> getAll();
}
