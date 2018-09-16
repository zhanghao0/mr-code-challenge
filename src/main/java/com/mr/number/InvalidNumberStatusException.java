package com.mr.number;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNumberStatusException extends RuntimeException {

	private static final long serialVersionUID = 7841072526507453756L;

	public InvalidNumberStatusException(String message) {
		super(message);
	}

}
