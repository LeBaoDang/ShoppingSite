package com.store.service.impl;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entity.Account;
import com.store.entity.Authority;
import com.store.repository.AccountRepo;
import com.store.repository.AuthorityRepo;
import com.store.service.AuthorityService;
import com.store.service.impl.exception.ResourceNotFoundException;


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
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public Authority create(Authority auth) {
		return authorityRepo.save(auth);
	}


	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void delete(Long id) {
		
		Optional<Authority> authorityOption = authorityRepo.findById(id);
		
		if(!authorityOption.isPresent()) {
			throw new ResourceNotFoundException(String.format("authority.does.not.exist.with.id:%s", id));
		}
		
		authorityRepo.deleteById(id);
	}
		
}
