package com.demo.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.shiro.UserPasswordToken;
import com.demo.util.CookieUtils;
import com.demo.util.Principal;

/**
 * 
 * LoginController.java
 *
 * @author liwenjian
 * @date 2017年7月19日 上午11:51:15
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	private final static String COOKIE_USERNAME = "username";

	@Resource(name = "imageCaptchaService")
	private com.octo.captcha.service.CaptchaService imageCaptchaService;

	@RequestMapping("")
	public String index(HttpServletRequest request, String redirectUrl, Model model) {
		if (redirectUrl != null && !redirectUrl.startsWith(request.getContextPath() + "/")) {
			redirectUrl = null;
		}
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "redirect:/";
		}
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		model.addAttribute("redirectUrl", redirectUrl);
		return "/login/index";
	}

	/**
	 * 登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param captchaId
	 *            验证码ID
	 * @param captcha
	 *            验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public Object login(@RequestParam(value = "username") String username,
			@RequestParam(value = "captchaId") String captchaId, String captcha, String remember_me,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		String password = request.getParameter("password");

		username = username.trim();
		password = password.trim();

		boolean isRmemberName = "1".equals(remember_me) ? true : false;// 记住账号
		String host = com.demo.util.IpUtil.getIpAddr(request);

		if (StringUtils.isEmpty(username)) {
			return super.error("请输入用户名");
		}
		if (StringUtils.isEmpty(password)) {
			return super.error("请输入密码");
		}
		// 校验验证码
		if (!imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase())) {
			return super.error("验证码校验失败");
		}

		UserPasswordToken token = new UserPasswordToken(username, password, captchaId, captcha, isRmemberName,
				host);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			return super.error("你输入的用户名或密码不正确");
		} catch (LockedAccountException e) {
			return super.error("该用户已被锁定");
		} catch (DisabledAccountException e) {
			return super.error("该用户已禁用");
		} catch (IncorrectCredentialsException e) {
			return super.error("你输入的用户名或密码不正确");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return super.error("系统繁忙~~");
		}
		Principal principal = (Principal) subject.getPrincipal();
		session.setAttribute(Principal.PRINCIPAL_ATTRIBUTE_NAME, principal);
		CookieUtils.addCookie(request, response, COOKIE_USERNAME, principal.getUsername());

		return super.success("登录成功");
	}

	/**
	 * 用户退出操作
	 *
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session.removeAttribute(Principal.PRINCIPAL_ATTRIBUTE_NAME);
		CookieUtils.removeCookie(request, response, COOKIE_USERNAME);
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/";
	}
}
