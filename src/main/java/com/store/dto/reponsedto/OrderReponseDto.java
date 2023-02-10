package com.store.dto.reponsedto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderReponseDto {

	private String address;
	
	private Date createDate = new Date();
	
	private AccountReponseDto accountReponseDto;
	
	private List<OrderDetailReponseDto> orderDetailReponseDtos;
}
