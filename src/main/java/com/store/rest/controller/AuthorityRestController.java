package com.store.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import com.store.service.AccountService;
import com.store.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {
	@Autowired
	AuthorityService authorityService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	AccountService accountService;

	@GetMapping
	public ResponseEntity<List<Authority>> findAll(@RequestParam("admin")
	Optional<Boolean> admin) {
		try {
			if (admin.orElse(false)) {
				return new ResponseEntity<>(authorityService.findAuthoritiesOfAdministrators(), HttpStatus.OK);
			}
			return new ResponseEntity<>(authorityService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Authority> post(@RequestBody Authority auth) {
		try {
			return new ResponseEntity<>(authorityService.create(auth), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		try {
			authorityService.delete(id);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}
	}
	
}
