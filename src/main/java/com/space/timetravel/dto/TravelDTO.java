package com.space.timetravel.dto;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TravelDTO {

	private UUID id;

	private String personalGalacticIdentifier;

	private String place;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	private Date date;

	public TravelDTO() {
	}

	public TravelDTO(UUID id, String personalGalacticIdentifier, String place, Date date) {
		this.id = id;
		this.personalGalacticIdentifier = personalGalacticIdentifier;
		this.place = place;
		this.date = date;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getPersonalGalacticIdentifier() {
		return personalGalacticIdentifier;
	}

	public void setPersonalGalacticIdentifier(String personalGalacticIdentifier) {
		this.personalGalacticIdentifier = personalGalacticIdentifier;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
