package com.space.timetravel.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import com.space.timetravel.dto.TravelDTO;
import com.space.timetravel.model.Travel;

public class TravelMapperTest {

	private static final UUID ID = UUID.fromString( "618f8104-ce84-40ee-b703-3a716b078584" );
	private static final String PGI = "child1";
	private static final String PLACE = "Manchester";
	private static final Date DATE = new Date();

	private TravelMapper mapper = new TravelMapper();

	@Test
	public void toEntity() {
		TravelDTO travelDTO = new TravelDTO( ID, PGI, PLACE, DATE );

		Travel travel = mapper.toEntity( travelDTO );

		assertThat( travel.getId(), equalTo( ID ) );
		assertThat( travel.getPersonalGalacticIdentifier(), equalTo( PGI ) );
		assertThat( travel.getPlace(), equalTo( PLACE ) );
		assertThat( travel.getDate(), equalTo( DATE ) );
	}

	@Test
	public void toEntityWhenIsNull() {
		Travel travel = mapper.toEntity( null );

		assertThat( travel, nullValue() );
	}

	@Test
	public void toDTO() {
		Travel travel = new Travel( ID, PGI, PLACE, DATE );

		TravelDTO travelDTO = mapper.toDTO( travel );

		assertThat( travelDTO.getId(), equalTo( ID ) );
		assertThat( travelDTO.getPersonalGalacticIdentifier(), equalTo( PGI ) );
		assertThat( travelDTO.getPlace(), equalTo( PLACE ) );
		assertThat( travelDTO.getDate(), equalTo( DATE ) );
	}

	@Test
	public void toDTOWhenIsNull() {
		TravelDTO travelDTO = mapper.toDTO( null );

		assertThat( travelDTO, nullValue() );
	}

}