package com.store.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.entity.Account;
import com.store.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping
	public ResponseEntity< List<Account> > getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		try {
			if(admin.orElse(false)) {
				return new ResponseEntity<List<Account>>(accountService.getAdministrators(), HttpStatus.OK);
			}
			return new ResponseEntity<List<Account>>(accountService.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Account>>(HttpStatus.NOT_FOUND);
		}
	}

}
