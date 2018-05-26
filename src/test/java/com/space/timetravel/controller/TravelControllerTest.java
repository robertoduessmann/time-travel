package com.space.timetravel.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.space.timetravel.TimeTravelApplication;
import com.space.timetravel.dto.TravelDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TimeTravelApplication.class)
public class TravelControllerTest {

	private static final String PGI_1 = "traveler1";
	private static final String PGI_2 = "traveler2";
	private static final String PGI_3 = "traveler3";
	private static final String PLACE = "Liverpool";
	private static final Date DATE = new Date();

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void save() {
		ResponseEntity response = restTemplate
				.postForEntity( "/travels", buildTravelRequest( PGI_1 ), TravelDTO.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
	}

	@Test
	public void get() {
		ResponseEntity<TravelDTO> createdResponse = restTemplate
				.postForEntity( "/travels", buildTravelRequest( PGI_2 ), TravelDTO.class );
		TravelDTO createdTravel = createdResponse.getBody();

		ResponseEntity<TravelDTO> response = restTemplate
				.getForEntity( "/travels/" + createdTravel.getId(), TravelDTO.class );
		TravelDTO travel = response.getBody();

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( travel.getPersonalGalacticIdentifier(), equalTo( PGI_2 ) );
		assertThat( travel.getPlace(), equalTo( PLACE ) );
		assertThat( travel.getDate(), equalTo( DATE ) );

	}

	@Test
	public void getAll() {
		restTemplate.postForEntity( "/travels", buildTravelRequest( PGI_3 ), TravelDTO.class );

		ResponseEntity<TravelDTO[]> response = restTemplate
				.getForEntity( "/travels", TravelDTO[].class );
		List<TravelDTO> travels = Arrays.asList( response.getBody() );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( travels, hasSize( 1 ) );
		assertThat( travels.get( 0 ).getPersonalGalacticIdentifier(), equalTo( PGI_3 ) );
	}

	private TravelDTO buildTravelRequest(String personalGalacticIdentifier) {
		return new TravelDTO( null, personalGalacticIdentifier, PLACE, DATE );
	}
}