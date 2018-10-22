package com.yunshuju.security;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 功能：自定义CSRF匹配，排除不需要CSRF防御的资源，比如给第三方开放的接口
 * 		默认使用的是DefaultRequiresCsrfMatcher
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-17 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@Slf4j
public class CSRFSecurityRequestMatcher implements RequestMatcher {

	private final HashSet<String> allowedMethods = new HashSet<String>(
			Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));
	//需要排除的URL
	private Set<String> excludedUrls;

	@Override
	public boolean matches(HttpServletRequest request) {
		if (excludedUrls != null && excludedUrls.size() > 0) {
			String seveletPath = request.getServletPath();
			for (String excludedUrl : excludedUrls) {
				if (seveletPath.contains(excludedUrl)) {
					return false;
				}
			}
		}
		return !this.allowedMethods.contains(request.getMethod());
	}

	public Set<String> getExcludedUrls() {
		return excludedUrls;
	}

	public void setExcludedUrls(Set<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
}
