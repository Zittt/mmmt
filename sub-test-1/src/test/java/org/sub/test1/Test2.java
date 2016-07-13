package org.sub.test1;

import com.zit.test1.common.JedisUtil;

public class Test2 {
	
	public static void main(String[] args) {
		JedisUtil ju = new JedisUtil();
//		ju.init();
		
		
		ju.set("name2", "zhou2");
		ju.set("name3", "zhou3");
		ju.set("name4", "zhou4");
		
		System.out.println(ju.get("name2"));
		System.out.println(ju.get("name3"));
		System.out.println(ju.get("name4"));
	}
}
