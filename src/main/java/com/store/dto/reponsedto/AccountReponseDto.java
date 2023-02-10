package com.store.dto.reponsedto;

import java.util.List;

import com.store.dto.requestdto.AuthorityRequestDto;
import com.store.dto.requestdto.OrderRequestDto;

import lombok.Data;

@Data
public class AccountReponseDto {

private String username;
	
	private String fullname;
	
	private String email;
	
	private String photo;
	
	private List<OrderRequestDto> orderDto;
	
	private List<AuthorityRequestDto> authoritiesDto;
	
}
