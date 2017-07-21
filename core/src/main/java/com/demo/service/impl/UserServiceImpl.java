package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserMapper;
import com.demo.entity.User;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}

	public int insert(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	public int insertSelective(User user) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(user);
	}

	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(user);
	}

	public int updateByPrimaryKey(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(user);
	}

	public List<User> select() {
		// TODO Auto-generated method stub
		return userMapper.select();
	}

	public User findByUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.findByUser(user);
	}

}
