package com.space.timetravel.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.space.timetravel.dto.TravelDTO;
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

}
