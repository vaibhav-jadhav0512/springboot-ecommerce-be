package com.ecommerce.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3424149540460869123L;

	private Object[] args;

	public CategoryAlreadyExistsException() {
		super();
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public CategoryAlreadyExistsException(String message) {
		super(message);
	}

	public CategoryAlreadyExistsException(String message, Object[] args) {
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

