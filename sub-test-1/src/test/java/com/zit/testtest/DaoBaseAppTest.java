package com.zit.testtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zit.test1.common.JedisUtil;

//@ActiveProfiles(value="sit")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:/spring-context-utils.xml")
//@ContextConfiguration(locations = "spring-context-utils.xml")
public class DaoBaseAppTest {

	// private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-context.xml");
	// private static UserInfoService userService = (UserInfoService) context.getBean("userInfoService");
	Logger logger = org.slf4j.LoggerFactory.getLogger(DaoBaseAppTest.class);
	
	//@Autowired
	private JedisUtil ju2;
	
	private ApplicationContext ac;
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-context-utils.xml");
		ju2 = (JedisUtil)ac.getBean("jedisUtil");
		
	}
	
	@After
	public void des() {
		System.out.println("des()...");
	}
	
	
	@Test
	public void test() {
		//JedisUtil ju = new JedisUtil();
		ju2.set("name7", "zhou7");
		String temp = ju2.get("name7");
		System.out.println("huoqu:" + temp);
	}
	
	@Test
	public void tes2() {
		System.out.println("hahaha");
	}
	
	public static void main(String[] args) {
		JedisUtil ju = new JedisUtil();
		ju.set("name7", "zhou7");
		String temp = ju.get("name7");
		System.out.println("huoqu:" + temp);
	}
}
