package com.space.timetravel.repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.space.timetravel.model.Travel;

public interface TravelRepository extends CrudRepository<Travel, UUID> {

	Optional<Travel> findByPersonalGalacticIdentifierAndPlaceAndDate(String personalGalacticIdentifier, String place,
			Date date);
}
