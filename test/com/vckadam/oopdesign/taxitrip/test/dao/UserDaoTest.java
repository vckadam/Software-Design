package com.vckadam.oopdesign.taxitrip.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.taxitrip.dao.user.UserDao;
import com.vckadam.oopdesign.taxitrip.dao.user.UserDaoImpl;
import com.vckadam.oopdesign.taxitrip.model.User;

public class UserDaoTest {

	private UserDao useDao;
	
	@Before
	public void setUp() throws Exception {
		this.useDao = new UserDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		this.useDao = null;
	}

	@Test
	public void testGetUserById() {
		User expectedUser = new User(1,false,"client");
		assertEquals(expectedUser.toString(),this.useDao.getUserById(1).toString());
	}

}
