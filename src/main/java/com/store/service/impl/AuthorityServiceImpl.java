package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entity.Account;
import com.store.entity.Authority;
import com.store.repository.AccountRepo;
import com.store.repository.AuthorityRepo;
import com.store.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	AuthorityRepo authorityRepo;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountRepo.getAdministrators();
		return authorityRepo.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return authorityRepo.findAll();
	}

}
