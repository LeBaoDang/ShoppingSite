package com.store.service.impl;

import com.store.dto.reponsedto.RoleReponseDto;
import com.store.entity.Role;
import com.store.repository.RoleRepo;
import com.store.service.RoleService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo roleRepo;
    
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<Role> findAll() {
    	
//    	List<RoleReponseDto> reponseDto =  Arrays.asList(mapper.map(roles,RoleReponseDto.class));
    	
        return roleRepo.findAll();
    }
}
