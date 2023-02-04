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
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	AuthorityRepo authorityRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	
	public List<Authority> findAll(){
		return authorityRepo.findAll();
	}
	
	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		// lấy ra tài khoản admins
		List<Account> accounts = accountRepo.getAdministrators();
		return authorityRepo.authoritiesOf(accounts);
	}

	@Override
	public Authority create(Authority auth) {
		return authorityRepo.save(auth);
	}


	@Override
	public void register(String username) {
		authorityRepo.register(username);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
		
}
