package com.augustars.xmall.xtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.augustars.xmall.base.controller.BaseController;
import com.augustars.xmall.xtest.service.HelloService;
@Controller("testController")
public class TestController extends BaseController{
	@Reference(version="1.0.0")
	private HelloService helloService;
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String test(String name) throws Exception{
		helloService.sayHello(name);
		return name;
	}
}
