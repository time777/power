package com.yunshuju.common.utils.exception;

/**
 * 日期超出有效范围异常
 */
public class IllegalDataException extends MeException {

	public IllegalDataException() {
		super("日期超出有效范围异常");
	}

	public IllegalDataException(String message) {
		super(message);
	}


}
