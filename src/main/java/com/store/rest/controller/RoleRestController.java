package com.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.entity.Role;
import com.store.service.RoleService;

@CrossOrigin("*")
@RequestMapping("/rest/roles")
public class RoleRestController {

	@Autowired
	RoleService roleService;
	
	@GetMapping
	public ResponseEntity< List<Role> > findAll(){
		try {
			return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
