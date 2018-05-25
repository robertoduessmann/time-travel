package com.space.timetravel.exception;

public class TravelAlreadyExistsException extends RuntimeException {

	public TravelAlreadyExistsException(){
		super("Invalid time travel! The same person cannot travel to the same place and date twice!");
	}
}
