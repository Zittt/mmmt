package com.zit.order.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zit.order.service.UserService;
import com.zit.order.service.impl.UserServiceImpl;
import com.zit.order.util.JedisUtil;

public class UserMapperDaoTest extends DaoBaseAppTest{
	
	private static final Logger logger = LoggerFactory.getLogger(UserMapperDaoTest.class);
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private JedisUtil jedisUtil;
	
	@Test
	public void test1() {
		 //ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-datasource.xml"); 
		// UserService userService = ctx.getBean(UserServiceImpl.class);
		System.out.println(userService.getUserById(23).getUserName());
		
		
		for(int i = 0; i<5; i++) {
			jedisUtil.set("name" + i, "zhou" + (i + 1));
			System.out.println(jedisUtil.get("name" + i));
		}
	}
}
