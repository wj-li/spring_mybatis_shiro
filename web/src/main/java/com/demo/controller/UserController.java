package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.User;
import com.demo.service.UserService;
import com.github.pagehelper.PageHelper;

/**
 * 
 * UserController.java
 *
 * @author liwenjian
 * @date 2017年7月19日 上午11:51:20
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/findPage", method = RequestMethod.GET)
	public Object findPage(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userService.select();
		return super.success(list);
	}
}
