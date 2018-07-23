package com.epam.theater.bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

public class DataBase {
	private Set<User> users;
	private Set<Auditorium> auditoriums;
	private Set<Event> events;
	private Map<User, Integer> discountCounter;
	private Statistic statistic;

	@PostConstruct
	public void fillData() {
		users = new HashSet<User>();
		events = new HashSet<Event>();
		auditoriums = new HashSet<Auditorium>();
		discountCounter = new HashMap<User, Integer>();
		setStatistic(new Statistic());

		users.add(new User(0L, "Albert", "Zarankovich", "Albert_Zarankovich@epam.com"));
		users.add(new User(1L, "Peter", "Burko", "Peter_Burko@epam.com"));
		users.add(new User(2L, "Nikita", "Mihalchuk", "Nikita_Mihalchuk@epam.com"));
		users.add(new User(3L, "Andrey", "Meleh", "Andrey_Meleh@epam.com"));
		users.add(new User(4L, "Alex", "Zaycev", "Alex_Zaycev@epam.com"));

		auditoriums.add(new Auditorium("auditorium_1", 100,
				new HashSet<Long>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L))));
		auditoriums.add(new Auditorium("auditorium_2", 200, new HashSet<Long>(
				Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L))));
		auditoriums.add(new Auditorium("auditorium_3", 130,
				new HashSet<Long>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L))));
		auditoriums.add(new Auditorium("auditorium_4", 50, new HashSet<Long>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L))));
		auditoriums.add(new Auditorium("auditorium_5", 80,
				new HashSet<Long>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L))));
		auditoriums.add(new Auditorium("auditorium_6", 20, new HashSet<Long>(Arrays.asList(1L, 2L, 3L, 4L, 5L))));
	}

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

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

}
