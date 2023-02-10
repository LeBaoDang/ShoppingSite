package com.store.dto.requestdto;

import java.util.List;

import lombok.Data;

@Data
public class RoleRequestDto {
	
	private String name;
	
	private List<AuthorityRequestDto> authorityRequestDtos;
	
}
