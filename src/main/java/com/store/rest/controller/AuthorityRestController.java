package com.store.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.entity.Authority;
import com.store.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {
	
	@Autowired
	AuthorityService authorityService;
	
	@GetMapping
	public ResponseEntity<List<Authority>> findAll(@RequestParam("admin")
	Optional<Boolean> admin) {
		try {
			if (admin.orElse(false)) {
				return new ResponseEntity<List<Authority>>(authorityService.findAuthoritiesOfAdministrators(), HttpStatus.OK);
			}
			return new ResponseEntity<List<Authority>>(authorityService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Authority>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Authority> post(@RequestBody Authority auth) {
		try {
			return new ResponseEntity<Authority>(authorityService.create(auth), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Authority>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		try {
			authorityService.delete(id);
			return new ResponseEntity<Void> (HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}

}
