package com.store.dto.reponsedto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryReponseDto {
	
	private String name;
	
	private List<ProductReponseDto> productReponseDto;

}
