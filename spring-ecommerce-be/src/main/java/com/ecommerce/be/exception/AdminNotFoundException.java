package com.ecommerce.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AdminNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6706718673171746033L;

	private Object[] args;

	public AdminNotFoundException() {
		super();
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public AdminNotFoundException(String message) {
		super(message);
	}

	public AdminNotFoundException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
