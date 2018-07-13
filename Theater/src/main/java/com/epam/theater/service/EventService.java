package com.epam.theater.service;

import com.epam.theater.bean.Event;

public interface EventService extends AbstractDomainObjectService <Event> {

	public Event getByName(String name);
}
