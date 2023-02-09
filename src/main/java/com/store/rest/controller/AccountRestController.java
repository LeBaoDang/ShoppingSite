package com.store.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.entity.Account;
import com.store.entity.Authority;
import com.store.service.AccountService;
import com.store.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AuthorityService authorityService;
	
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
	
	@GetMapping("/list")
	public List<Account> findAll(){
		return accountService.findAll();
	}
	
	
	@GetMapping("login-in-account")
	public Account getdn() {
		Account account = accountService.findById(request.getUserPrincipal().getName());
		return account;
	}
	
	@GetMapping("login-in-account-role")
	public List<Authority> role(){
		Account account = accountService.findById(request.getUserPrincipal().getName());
		List<Authority> authority = authorityService.findAuthority(account.getUsername()); 
		return authority ;
	}
		
}
