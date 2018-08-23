package com.augustars.xmall.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.augustars.xmall.entity.User;
import com.augustars.xmall.service.UserService;

@Component("shiroDBRealm")
public class ShiroDBRealm extends AuthorizingRealm{
	@Reference(version="1.0.0")
	private UserService userService;
	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		return null;
	}
	
	/**
	 * 进行用户身份验证的时候调用
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//	对于原始的Token令牌强制转换成UsernamepasswordToken类型对象
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		//	获得用户登录名
		String loginName = userToken.getUsername();
		//	对原始的密码进行加密
		String password = new String(userToken.getPassword());
		password = EncryptionUtil.encrypt(password);
		//	将加密后的密码重新设定回UserToken中
		userToken.setPassword(password.toCharArray());
		try {
            //  通过用户登录名查找正确的用户信息对象
			User user = userService.getUserByLoginName(loginName);
			//	使用Shiro进行验证信息是否正确
			SimpleAuthenticationInfo info = 
						new SimpleAuthenticationInfo(user.getLoginName(),user.getPassword(),getName());
			//	默认登录成功，绑定Session对象
			SecurityUtils.getSubject().getSession().setAttribute("user", user);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
