package com.epam.theater.service;

import java.util.Collection;
import com.epam.theater.bean.DomainObject;

public interface AbstractDomainObjectService<T extends DomainObject> {
	public void remove(Long id);

	public T getById(Long id);

	public Collection<T> getAll();
}
