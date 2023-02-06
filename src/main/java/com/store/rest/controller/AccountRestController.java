package com.store.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.entity.Account;
import com.store.entity.Authority;
import com.store.entity.Role;
import com.store.repository.AuthorityRepo;
import com.store.repository.RoleRepo;
import com.store.service.AccountService;
import com.store.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AuthorityRepo repo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	HttpServletRequest request;
	
	
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
	
	
	@GetMapping("logged-in")
	public Account getdn() {
		Account account = accountService.findById(request.getUserPrincipal().getName());
		account.getAuthorities().stream().map(au -> au.getRole().getId())
				.collect(Collectors.toList()).toArray(new String[0]);
		return account;
	}
	
	@GetMapping("logged-inn")
	public String[] role(){
		Account account = accountService.findById(request.getUserPrincipal().getName());
		String role[] =  account.getAuthorities().stream().map(au -> au.getRole().getId())
				.collect(Collectors.toList()).toArray(new String[0]);
		return role ;
	}
		
}
