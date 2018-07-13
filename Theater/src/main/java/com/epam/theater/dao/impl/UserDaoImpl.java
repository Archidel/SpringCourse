package com.epam.theater.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.epam.theater.bean.DataBase;
import com.epam.theater.bean.User;
import com.epam.theater.dao.UserDao;

public class UserDaoImpl implements UserDao {

	private DataBase dataBase;

	@Override
	public void save(User user) {
		dataBase.getUsers().add(user);
	}

	@Override
	public void remove(User user) {

	}

	@Override
	public User getById(Long id) {
		dataBase.getUsers().
		return null;
	}

	@Override
	public Collection<User> getAll() {
		return dataBase.getUsers();
	}

	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}

	@Override
	public int getFreeId() {
		Set<User> user = dataBase.getUsers();

		
		
		int userSize = dataBase.getUsers().size();

		return 0;
	}

}
