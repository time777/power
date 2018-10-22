package com.yunshuju.common.utils.exception;

/**
 * 运行中的异常
 */
public abstract class MeException extends RuntimeException {

	public MeException() {
		super();
	}

	public MeException(String message) {
		super(message);
	}

	public MeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MeException(Throwable cause) {
		super(cause);
	}

	protected MeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
