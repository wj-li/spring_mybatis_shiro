package com.demo.util;

import java.io.Serializable;

/**
 * 
 * Principal.java
 *
 * @author liwenjian
 * @date 2017年7月19日 上午11:52:33
 * @version 1.0.0
 */
public class Principal implements Serializable {

	/** 用户Cookie名称 */
	public static final String USERNAME_COOKIE_NAME = "u_c_n";

	/** "身份信息"参数名称 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = Principal.class.getName() + ".PRINCIPAL";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;

	/** 用户名 */
	private String username;

	/**
	 * 登录IP
	 */
	private String ip;

	/**
	 * @param id
	 *            ID
	 * @param username
	 *            用户名
	 */
	public Principal(Integer id, String username, String ip) {
		this.id = id;
		this.username = username;
		this.ip = ip;
	}

	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取用户名
	 * 
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 * 
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}