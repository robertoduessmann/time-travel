package com.space.timetravel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.space.timetravel.dto.TravelDTO;
import com.space.timetravel.exception.InvalidPGIException;
import com.space.timetravel.exception.TravelAlreadyExistsException;
import com.space.timetravel.mapper.TravelMapper;
import com.space.timetravel.model.Travel;
import com.space.timetravel.repository.TravelRepository;

@Service
public class TravelService {

	@Autowired
	private TravelRepository repository;
	@Autowired
	private TravelMapper mapper;

	@Transactional
	public TravelDTO save(TravelDTO travelDTO) {
		Travel travel = repository.save( mapper.toEntity( travelDTO ) );
		return mapper.toDTO( travel );
	}

	public List<TravelDTO> getAll() {
		ArrayList<Travel> travels = new ArrayList<>();
		repository.findAll().forEach( travels::add );
		return travels.stream()
				.map( mapper::toDTO )
				.collect( Collectors.toList() );
	}

	public TravelDTO get(String travelId) {
		UUID id = UUID.fromString( travelId );
		return repository.findById( id )
				.map( mapper::toDTO )
				.orElse( null );
	}
}
