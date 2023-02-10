package com.store.dto.reponsedto;

import lombok.Data;

@Data
public class OrderDetailReponseDto {

	private Double price;

	private Integer quantity;
	
	private ProductReponseDto productReponseDto;
	
	private OrderReponseDto orderReponseDto;
}
