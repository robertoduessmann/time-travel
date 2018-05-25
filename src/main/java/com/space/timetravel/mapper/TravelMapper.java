package com.space.timetravel.mapper;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.space.timetravel.dto.TravelDTO;
import com.space.timetravel.model.Travel;

@Service
public class TravelMapper {

	public Travel toEntity(TravelDTO dto) {
		return Optional.ofNullable( dto )
				.map( this::mapToEntity )
				.orElse( null );
	}

	private Travel mapToEntity(TravelDTO dto) {
		return new Travel( dto.getId(), dto.getPersonalGalacticIdentifier(), dto.getPlace(), dto.getDate() );
	}

	public TravelDTO toDTO(Travel entity) {
		return Optional.ofNullable( entity )
				.map( this::mapToDTO )
				.orElse( null );
	}

	private TravelDTO mapToDTO(Travel entity) {
		return new TravelDTO( entity.getId(), entity.getPersonalGalacticIdentifier(), entity.getPlace(),
				entity.getDate() );
	}

}
