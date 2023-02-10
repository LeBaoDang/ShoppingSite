package com.store.dto.requestdto;

import lombok.Data;

@Data
public class AuthorityRequestDto {

	private AccountRequestDto accountRequestDto;

	private RoleRequestDto roleRequestDto;
}
