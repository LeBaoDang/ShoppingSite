package com.store.dto.requestdto;

import java.util.List;

import lombok.Data;

@Data
public class AccountRequestDto {
	
	private String username;
	
	private String fullname;
	
	private String email;
	
	private String password;
	
	private String photo;
	
	private List<OrderDto> orderDto;
	
	private List<AuthorityDto> authoritiesDto;
	
}
