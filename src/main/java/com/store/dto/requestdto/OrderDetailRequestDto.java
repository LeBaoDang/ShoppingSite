package com.store.dto.requestdto;

import lombok.Data;

@Data
public class OrderDetailRequestDto {

	private Double price;

	private Integer quantity;
	
	private ProductRequestDto productRequestDto;
	
	private OrderRequestDto orderRequestDto;
}
