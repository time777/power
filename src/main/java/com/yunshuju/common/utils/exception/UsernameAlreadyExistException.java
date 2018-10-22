package com.yunshuju.common.utils.exception;

/**
 * 用户名已存在异常
 */
public class UsernameAlreadyExistException extends MeException {

	public UsernameAlreadyExistException() {
		super("用户名已存在异常");
	}

}
