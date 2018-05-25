package com.space.timetravel.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.space.timetravel.exception.InvalidPGIException;
import com.space.timetravel.exception.TravelAlreadyExistsException;

@ControllerAdvice
public class TimeTravelExceptionHandler {

	@ExceptionHandler(value = { InvalidPGIException.class, TravelAlreadyExistsException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String exceptionHandler(Exception e) {
		return e.getMessage();
	}

}
