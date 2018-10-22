package com.yunshuju.common.utils.exception;

/**
 * 登录失败异常
 */
public class LoginFailedException extends MeException {

	public LoginFailedException() {
		super("登录失败异常");
	}

	public LoginFailedException(String message) {
		super(message);
	}

}
