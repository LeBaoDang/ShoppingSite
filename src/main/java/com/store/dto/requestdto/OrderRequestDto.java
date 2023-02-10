package com.store.dto.requestdto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDto {

	private String address;

	private Date createDate = new Date();
	
	private AccountRequestDto accountRequestDto;
	
	private List<OrderDetailRequestDto> orderDetailRequestDtos;

}
