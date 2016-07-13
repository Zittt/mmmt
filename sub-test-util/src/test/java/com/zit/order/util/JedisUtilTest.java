package com.zit.order.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JedisUtilTest extends BaseAppTest{
	
	@Autowired
	private JedisUtil jedisUtil;
	
	@Test
	public void test2() {
		System.out.println("jinlai...................");
		for(int i = 10; i<15; i++) {
			jedisUtil.set("name" + i, "zhou" + (i + 1));
			System.out.println(jedisUtil.get("name" + i));
		}
		System.out.println("wanbi.....................");
	}
}
