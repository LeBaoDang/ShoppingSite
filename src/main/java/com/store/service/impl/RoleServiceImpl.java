package com.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entity.Role;
import com.store.repository.RoleRepo;
import com.store.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepo roleRepo;
	
	@Override
	public List<Role> findAll() {
		
		return roleRepo.findAll();
		
	}

}
