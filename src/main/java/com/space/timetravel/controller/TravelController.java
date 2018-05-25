package com.space.timetravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.space.timetravel.dto.TravelDTO;
import com.space.timetravel.service.TravelService;

@RestController
@RequestMapping(value = "travels")
public class TravelController {

	@Autowired
	private TravelService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity save(@RequestBody TravelDTO travel) {
		return ResponseEntity.ok( service.save( travel ) );
	}

}
