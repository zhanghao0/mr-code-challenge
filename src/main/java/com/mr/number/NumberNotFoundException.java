package com.mr.number;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NumberNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2038910497363440523L;

	public NumberNotFoundException(String message) {
		super(message);
	}

}
