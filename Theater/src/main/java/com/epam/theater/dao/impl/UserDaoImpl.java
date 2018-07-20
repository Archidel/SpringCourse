package com.epam.theater.dao.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.epam.theater.bean.DataBase;
import com.epam.theater.bean.User;
import com.epam.theater.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	private DataBase dataBase;

	@Override
	public void save(User user) {
		dataBase.getUsers().add(user);
	}

	@Override
	public void remove(User user) {
		dataBase.getUsers().remove(user);
	}

	@Override
	public User getById(Long id) {
		Optional<User> optinalUsers = dataBase.getUsers().stream().filter(p -> p.getId().equals(id)).findFirst();
		return optinalUsers.orElse(null);
	}

	@Override
	public Collection<User> getAll() {
		return dataBase.getUsers();
	}

	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}

	@Override
	public Long getFreeId() {
		Set<User> users = dataBase.getUsers();
		Long userSize = (long) users.size();
		Long freeId;

		while (true) {
			if (idIsFree(users, userSize)) {
				freeId = userSize;
				break;
			} else {
				idIsFree(users, userSize++);
			}
		}

		return freeId;
	}

	private boolean idIsFree(Set<User> users, Long id) {
		boolean isFree = true;
		for (User user : users) {
			if (user.getId() == id) {
				isFree = false;
			}
		}
		return isFree;
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> optinalUsers = dataBase.getUsers().stream().filter(p -> p.getEmail().equalsIgnoreCase(email)).findFirst();
		return optinalUsers.orElse(null);
	}

}
