package com.yunshuju.common.utils.exception;

/**
 * 执行失败异常
 */
public class ExecuteFailException extends MeException {
	public ExecuteFailException() {
		super("执行失败异常");
	}

	public ExecuteFailException(Throwable cause) {
		super(cause);
	}

}
