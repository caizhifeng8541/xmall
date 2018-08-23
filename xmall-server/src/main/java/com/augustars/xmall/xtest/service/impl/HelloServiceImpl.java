package com.augustars.xmall.xtest.service.impl;

import org.springframework.stereotype.Service;

import com.augustars.xmall.xtest.service.HelloService;
@Service("helloService")
@com.alibaba.dubbo.config.annotation.Service(version="1.0.0")
public class HelloServiceImpl implements HelloService {
	public void sayHello(String name) throws Exception {
		System.out.println("你好！" + name);
	}
}
