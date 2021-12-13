package com.capgemini.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Scheme;
import com.capgemini.service.ISchemeService;

@RestController
public class SchemeController implements ISchemeController {

	private static final Logger LOG = LoggerFactory.getLogger(SchemeController.class);

	@Autowired
	private ISchemeService iSchemeService;

	/**
	 * This method will add Scheme data into database by taking body from user.
	 */
//	http://localhost:8082/addscheme
	@PostMapping("/addscheme")
	public ResponseEntity<Scheme> addScheme(@RequestBody Scheme scheme) {
		LOG.info("Controller addScheme");
		Scheme result = iSchemeService.addScheme(scheme);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Scheme added successfully.");
		LOG.info(headers.toString());
		ResponseEntity<Scheme> response = new ResponseEntity<>(result, headers, HttpStatus.CREATED);
		return response;
	}

	// ------------------------------------------------------------------------------------------

	/**
	 * This method will update existing Scheme data into database by taking body from user.
	 */
//	http://localhost:8082/updatescheme
	@PutMapping("/updatescheme")
	public ResponseEntity<Scheme> updateScheme(@RequestBody Scheme scheme) {
		LOG.info("Controller updateScheme");
		Scheme result = iSchemeService.updateScheme(scheme);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Scheme updated successfully.");
		LOG.info(headers.toString());
		ResponseEntity<Scheme> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
		return response;
	}

	// ------------------------------------------------------------------------------------------

	/**
	 * User needs to provide schemeId. Based on that id, this method will find Scheme equal to that id.
	 */
//	http://localhost:8082/viewschemebyid/{schemeId}
	@GetMapping("/viewschemebyid/{schemeId}")
	public ResponseEntity<Scheme> viewSchemeById(@PathVariable(name = "schemeId") int schemeId) {
		LOG.info("Controller viewSchemeById");
		Scheme result = iSchemeService.viewScheme(schemeId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Scheme having id \"" + schemeId + "\" available in database.");
		LOG.info(headers.toString());
		ResponseEntity<Scheme> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
		return response;
	}

	// ------------------------------------------------------------------------------------------

	/**
	 * This method will display all Scheme details stored into database.
	 */
//	http://localhost:8082/viewallschemes
	@GetMapping("/viewallschemes")
	public ResponseEntity<List<Scheme>> getAllSchemes() {
		LOG.info("Controller getAllSchemes");
		List<Scheme> result = iSchemeService.viewAllScheme();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All Schemes details are retrieved.");
		LOG.info(headers.toString());
		ResponseEntity<List<Scheme>> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
		return response;
	}

	// ------------------------------------------------------------------------------------------
	
	/**
	 * This method will delete Scheme data from database by taking schemeId from user.
	 */
//	http://localhost:8082/deleteschemebyid/{schemeId}
	@DeleteMapping("/deleteschemebyid/{schemeId}")
	public ResponseEntity<Scheme> deleteSchemeById(@PathVariable(name = "schemeId") int schemeId) {
		LOG.info("Controller deleteSchemeById");
		Scheme result = iSchemeService.deleteScheme(schemeId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Scheme having id \"" + schemeId + "\" deleted successfully.");
		LOG.info(headers.toString());
		ResponseEntity<Scheme> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
		return response;
	}

	// ------------------------------------------------------------------------------------------

	/**
	 * This method will display Scheme data from database based on scheme type provided by user.
	 */
//	http://localhost:8082/viewbytype/{schemeType}
	@GetMapping("/viewbytype/{schemeType}")
	public ResponseEntity<List<Scheme>> getSchemeByType(@PathVariable(name = "schemeType") String schemeType) {
		LOG.info("Controller getSchemeByType");
		List<Scheme> result = iSchemeService.viewSchemesByType(schemeType);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Schemes with type \"" + schemeType + "\" retrieved from database.");
		LOG.info(headers.toString());
		ResponseEntity<List<Scheme>> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
		return response;
	}

	// ------------------------------------------------------------------------------------------

	
	/**
	 * This method will display Scheme data from database based on scheme launch date provided by user.
	 */
//	http://localhost:8082/viewbylaunchdate/{schemeLaunchDate}
	@GetMapping("/viewbylaunchdate/{schemeLaunchDate}")
	public ResponseEntity<List<Scheme>> getSchemeByLaunchDate(
			@PathVariable(name = "schemeLaunchDate") @DateTimeFormat(iso = ISO.DATE) LocalDate schemeLaunchDate) {
		LOG.info("Controller getSchemeByType");
		List<Scheme> result = iSchemeService.viewSchemeByLaunchDate(schemeLaunchDate);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Schemes with launch date \"" + schemeLaunchDate + "\" retrieved from database.");
		LOG.info(headers.toString());
		ResponseEntity<List<Scheme>> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
		return response;
	}

	// ------------------------------------------------------------------------------------------

	/**
	 * This method will display Scheme data from database based on scheme Eligibility provided by user.
	 */
//	http://localhost:8082/viewbyeligibility/{schemeEligibility}
	@GetMapping("/viewbyeligibility/{schemeEligibility}")
	public ResponseEntity<List<Scheme>> getSchemeByEligibility(
			@PathVariable(name = "schemeEligibility") String schemeEligibility) {
		LOG.info("Controller getSchemeByEligibility");
		List<Scheme> result = iSchemeService.viewSchemesByEligibility(schemeEligibility);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Schemes with eligibility \"" + schemeEligibility + "\" retrieved from database.");
		LOG.info(headers.toString());
		ResponseEntity<List<Scheme>> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
		return response;
	}
}
