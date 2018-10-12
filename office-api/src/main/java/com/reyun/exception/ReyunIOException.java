package com.reyun.exception;

/**
 * ReyunIOException
 * @author liruijie@reyun.com
 * @date 2015年12月11日
 */
public class ReyunIOException extends RuntimeException {
	private static final long serialVersionUID = -343427982157184053L;

	public ReyunIOException() {
		super();
	}

	public ReyunIOException(String message) {
		super(message);
	}

	public ReyunIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReyunIOException(Throwable cause) {
		super(cause);
	}
}
