package com.store.dto.reponsedto;

import java.util.List;

import com.store.dto.requestdto.AuthorityDto;
import com.store.dto.requestdto.OrderDto;

import lombok.Data;

@Data
public class AccountReponseDto {

private String username;
	
	private String fullname;
	
	private String email;
	
	private String photo;
	
	private List<OrderDto> orderDto;
	
	private List<AuthorityDto> authoritiesDto;
	
}
