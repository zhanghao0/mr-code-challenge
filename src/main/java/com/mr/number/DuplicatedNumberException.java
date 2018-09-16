package com.mr.number;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedNumberException extends RuntimeException {

	private static final long serialVersionUID = -5435025526351795578L;

	public DuplicatedNumberException(String message) {
		super(message);
	}

}
