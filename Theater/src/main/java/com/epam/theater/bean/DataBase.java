package com.epam.theater.bean;

import java.util.Set;

public class DataBase {
	private Set<User> users;
	private Set<Auditorium> auditoriums;

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

}
