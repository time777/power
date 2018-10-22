package com.yunshuju.common.utils.exception;

/**
 * 生产主键异常
 */
public class GeneratorIdException extends MeException {

	public GeneratorIdException() {
		super("生成主键发生异常");
	}

	public GeneratorIdException(String message) {
		super(message);
	}

}
