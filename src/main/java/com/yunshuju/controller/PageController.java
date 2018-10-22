package com.yunshuju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yunshuju.security.SecurityContextUtils;

/**
 * 功能：
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-21 16:50:34
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@Controller
public class PageController {


	@GetMapping(value = "/",produces = "text/html")
	public String main() {
		if (SecurityContextUtils.getCurrentUser() != null) {
			return "redirect:index.html";
		} else {
			return "login";
		}
	}

	@GetMapping("index.html")
	public String index() {
		return "index";
	}

	@GetMapping("/login.html")
	public String login() {
		if (SecurityContextUtils.getCurrentUser() != null) {
			return "redirect:index.html";
		} else {
			return "login";
		}
	}

	@GetMapping("/noAccess.html")
	public String noAccess() {
		return "noAccess";
	}

	@GetMapping("/403.html")
	public String toForbidden() {
		return "/error/403";
	}

	@GetMapping("/404.html")
	public String toNotFond() {
		return "/error/404";
	}

	@GetMapping("/500.html")
	public String toServerError() {
		return "/error/500";
	}

}
