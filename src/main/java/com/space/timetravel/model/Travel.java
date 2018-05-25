package com.space.timetravel.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Travel {

	@Id
	@Column(updatable = false)
	private UUID id;

	private String personalGalacticIdentifier;

	private String place;

	private Date date;

	@PrePersist
	public void prePersist() {
		id = UUID.randomUUID();
	}

	public Travel() {
	}

	public Travel(UUID id, String personalGalacticIdentifier, String place, Date date) {
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
