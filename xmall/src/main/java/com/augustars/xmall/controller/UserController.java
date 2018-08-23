package com.augustars.xmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.augustars.xmall.base.controller.BaseController;
import com.augustars.xmall.entity.User;
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String testSession() throws Exception{
		User user = (User) session.getAttribute("user");
		System.out.println(user.getUsername());
		System.out.println(session);
		return "Hello Session";
	}
}
