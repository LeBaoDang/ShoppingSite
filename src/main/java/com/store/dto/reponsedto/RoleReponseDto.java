package com.store.dto.reponsedto;

import java.util.List;

import lombok.Data;

@Data
public class RoleReponseDto {
	
	private String name;
	
	private List<AuthorityReponseDto> authorityReponseDtos;
	
}
