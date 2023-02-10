package com.store.dto.requestdto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountRequestDto {
	
	@NotBlank(message = "Vui lòng nhập username")
	private String username;
	
	@NotBlank(message = "Vui lòng nhập password")
	private String password;
	
	@NotBlank(message = "Vui lòng nhập họ và tên")
	private String fullname;
	
	@NotBlank(message = "Vui lòng nhập email")
	@Email(message = "không đúng định dạng email")
	private String email;
	
	private String photo;
	
	private List<OrderRequestDto> orderDto;
	
	private List<AuthorityRequestDto> authoritiesDto;
	
}
