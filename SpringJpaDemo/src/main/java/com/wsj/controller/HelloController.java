package com.wsj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "Hello, SpringBoot With Docker";
	}
}
