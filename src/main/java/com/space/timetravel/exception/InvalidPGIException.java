package com.space.timetravel.exception;

public class InvalidPGIException extends RuntimeException {

	public InvalidPGIException() {
		super( "Invalid personal galactic identifier! PGI always starts with a letter, between 5-10 characters! " );
	}
}
