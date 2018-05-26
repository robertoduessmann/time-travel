package com.space.timetravel.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.space.timetravel.dto.TravelDTO;
import com.space.timetravel.exception.InvalidPGIException;
import com.space.timetravel.exception.TravelAlreadyExistsException;
import com.space.timetravel.mapper.TravelMapper;
import com.space.timetravel.model.Travel;
import com.space.timetravel.repository.TravelRepository;

@RunWith(MockitoJUnitRunner.class)
public class TravelServiceTest {

	private static final UUID ID = UUID.fromString( "cca82ff9-4613-4ff8-8dc7-51c4ca3259a5" );
	private static final String PGI = "person1";
	private static final String PLACE = "London";
	private static final Date DATE = new Date();
	private static final String PGI_BIGGER_THAN_10 = "personpersonperson";
	private static final String PGI_SMALLER_THAN_5 = "per";
	private static final String PGI_WITH_INVALID_FIRST_CHAR = "1person";

	@InjectMocks
	private TravelService service;
	@Mock
	private TravelRepository repository;
	@Mock
	private TravelMapper mapper;

	@Test
	public void save() {
		TravelDTO travelRequest = new TravelDTO( null, PGI, PLACE, DATE );
		Travel mappedTravel = new Travel( null, PGI, PLACE, DATE );
		Travel savedTravel = new Travel( ID, PGI, PLACE, DATE );
		TravelDTO travelResponse = new TravelDTO( ID, PGI, PLACE, DATE );
		when( mapper.toEntity( travelRequest ) ).thenReturn( mappedTravel );
		when( repository.save( mappedTravel ) ).thenReturn( savedTravel );
		when( mapper.toDTO( savedTravel ) ).thenReturn( travelResponse );

		TravelDTO travel = service.save( travelRequest );

		assertThat( travel, equalTo( travelResponse ) );
		verify( mapper ).toEntity( travelRequest );
		verify( repository ).save( mappedTravel );
		verify( mapper ).toDTO( savedTravel );
	}

	@Test(expected = InvalidPGIException.class)
	public void saveNullPGI() {
		service.save( new TravelDTO( null, null, PLACE, DATE ) );
	}

	@Test(expected = InvalidPGIException.class)
	public void savePGISmallerThanAllowed() {
		service.save( new TravelDTO( null, PGI_SMALLER_THAN_5, PLACE, DATE ) );
	}

	@Test(expected = InvalidPGIException.class)
	public void savePGIBiggerThanAllowed() {
		service.save( new TravelDTO( null, PGI_BIGGER_THAN_10, PLACE, DATE ) );
	}

	@Test(expected = InvalidPGIException.class)
	public void savePGIWithInvalidFirstChar() {
		service.save( new TravelDTO( null, PGI_WITH_INVALID_FIRST_CHAR, PLACE, DATE ) );
	}

	@Test(expected = TravelAlreadyExistsException.class)
	public void saveExistingTravel() {
		Travel travel = new Travel( ID, PGI, PLACE, DATE );
		when( repository.findByPersonalGalacticIdentifierAndPlaceAndDate( PGI, PLACE, DATE ) )
				.thenReturn( Optional.of( travel ) );

		service.save( new TravelDTO( null, PGI, PLACE, DATE ) );
	}

	@Test
	public void get() {
		Travel savedTravel = new Travel( ID, PGI, PLACE, DATE );
		TravelDTO mappedTravel = new TravelDTO( ID, PGI, PLACE, DATE );
		when( repository.findById( ID ) ).thenReturn( Optional.of( savedTravel ) );
		when( mapper.toDTO( savedTravel ) ).thenReturn( mappedTravel );

		TravelDTO travel = service.get( ID.toString() );

		assertThat( travel, equalTo( mappedTravel ) );
		verify( repository ).findById( ID );
		verify( mapper ).toDTO( savedTravel );
	}

	@Test
	public void getAll() {
		Travel travel = new Travel( ID, PGI, PLACE, DATE );
		TravelDTO mappedTravel = new TravelDTO( ID, PGI, PLACE, DATE );
		when( repository.findAll() ).thenReturn( Collections.singletonList( travel ) );
		when( mapper.toDTO( travel ) ).thenReturn( mappedTravel );

		List<TravelDTO> travels = service.getAll();

		assertThat( travels, hasSize( 1 ) );
		assertThat( travels.get( 0 ), equalTo( mappedTravel ) );
		verify( repository ).findAll();
		verify( mapper ).toDTO( travel );
	}
}