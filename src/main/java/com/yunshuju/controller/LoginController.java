package com.yunshuju.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能：
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-10-11 15:52:02
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@RestController
@SessionAttributes("user")
public class LoginController {

	/**
	 * 登录错误提示
	 *
	 * @param error
	 * @return
	 */
	@GetMapping("/login")
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "用户名或密码错误");
		}
		model.setViewName("login");
		return model;
	}

}
