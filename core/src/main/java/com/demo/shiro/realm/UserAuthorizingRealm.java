package com.demo.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.shiro.UserPasswordToken;
import com.demo.util.Principal;

/**
 * 
 * UserAuthorizingRealm.java
 *
 * @author liwenjian
 * @date 2017年7月19日 上午11:51:56
 * @version 1.0.0
 */
public class UserAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 查询获得用户信息 AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
	 *
	 * AuthenticationInfo有两个作用： 1、如果Realm 是AuthenticatingRealm
	 * 子类，则提供给AuthenticatingRealm 内部使用的
	 * CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）；
	 * 2、提供给SecurityManager来创建Subject（提供身份信息）；
	 *
	 * @param authcToken
	 * @return
	 * @throws org.apache.shiro.authc.AuthenticationException
	 */

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UserPasswordToken token = (UserPasswordToken) authcToken;
		String username = token.getUsername();
		String password = new String(token.getPassword());
		String ip = token.getHost();
		if (username != null && password != null) {
			User user = userService.findByUser(new User(username));
			if (user == null) {
				throw new UnknownAccountException();
			} else if (user.getDisabled() != null && user.getDisabled()) {
				// 用户禁用状态 true:禁用 ,false:有效
				throw new DisabledAccountException();
			} else if (user.getLocked() != null && user.getLocked()) {
				// 用户锁定状态 true:锁定，false：未锁定
				throw new LockedAccountException();
			} else {
				// 密码校验
				if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
					throw new IncorrectCredentialsException();
				}
			}
			return new SimpleAuthenticationInfo(new Principal(user.getId(), username, ip), password, getName());
		}
		throw new UnknownAccountException();
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 配置用户权限
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			// 推荐将权限标识加入缓存，然后从缓存获取权限标识集合，并添加给SimpleAuthorizationInfo，避免频繁访问数据库获取资源
			// 如：List<String> permissions = redisService.findUserPermissions(userId);
			// 权限标识与applicationContext-shiro.xml配置的filterChainDefinitionsperms中类似["admin:userModule"]
			// 以下示例
			List<String> permissions = new ArrayList<String>();
			permissions.add("admin:userModule");// 赋予这个标识才有权限访问/user/findPage.html
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.addStringPermissions(permissions);
			return authorizationInfo;

		}
		return null;
	}

}
