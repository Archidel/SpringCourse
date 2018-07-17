package com.epam.theater.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.epam.theater.bean.User;
import com.epam.theater.service.UserService;
import com.epam.theater.service.exception.ServiceException;

@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class UserServiceImplTest {

	private ApplicationContext context;
	private UserService userService;
	private User user;

	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("appContext.xml");
		userService = (UserService) context.getBean("userService");
		user = new User(100L, "TestFirstname", "TestLastName", "TestFirstname_TestLastName@epam.com");
		userService.save(user);
	}

	@Test
	public void testGetUserById_shouldReturnValidUser() throws ServiceException {
		User testUser = userService.getById(100L);
		assertNotNull(testUser);
		assertEquals(user, testUser);
	}

	@Test
	public void testGetUserByEmail_shouldReturnValidUser() throws ServiceException {
		User testUser = userService.getUserByEmail("TestFirstname_TestLastName@epam.com");
		assertNotNull(testUser);
		assertEquals(user, testUser);
	}

	@Test(expected = ServiceException.class)
	public void testGetUserById_shouldThrowException_whenInvalidUserId() throws ServiceException {
		User testUser = userService.getById(999L);
		assertNull(testUser);
	}

	@Test(expected = ServiceException.class)
	public void testGetUserByEmail_shouldThrowException_whenInvalidEmail() throws ServiceException {
		User testUser = userService.getUserByEmail("IvalidEmail@invalid.com");
		assertNull(testUser);
	}

	@Test(expected = ServiceException.class)
	public void testRemoveUser_shouldThrowException_whenUserDosentExist() throws ServiceException {
		User testUser = userService.getById(100L);
		userService.remove(testUser);
		testUser = userService.getById(100L);
		assertNull(testUser);
	}

}
