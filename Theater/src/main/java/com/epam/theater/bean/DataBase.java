package com.epam.theater.bean;

import java.util.Map;
import java.util.Set;

public class DataBase {
	private Set<User> users;
	private Set<Auditorium> auditoriums;
	private Set<Event> events;
	private Map<User, Integer> discountCounter;

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Auditorium> getAuditoriums() {
		return auditoriums;
	}

	public void setAuditoriums(Set<Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Map<User, Integer> getDiscountCounter() {
		return discountCounter;
	}

	public void setDiscountCounter(Map<User, Integer> discountCounter) {
		this.discountCounter = discountCounter;
	}

}
