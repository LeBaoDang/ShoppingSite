package com.store.service;

import java.util.List;

import com.store.entity.Authority;

public interface AuthorityService {
	
	public List<Authority> findAuthoritiesOfAdministrators();
	
	public List<Authority> findAll();

}
