package com.jaymiaosha.common.access;


import com.jaymiaosha.pojo.MiaoshaUser;

/**
 * 将用户信息放入到当前线程中
 */
public class UserContext {

	//当前线程
	private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();
	
	public static void setUser(MiaoshaUser user) {
		userHolder.set(user);
	}
	
	public static MiaoshaUser getUser() {
		return userHolder.get();
	}

}
