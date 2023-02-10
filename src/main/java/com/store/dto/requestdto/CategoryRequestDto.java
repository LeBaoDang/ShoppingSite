package com.store.dto.requestdto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryRequestDto {

	private String name;
	
	private List<ProductRequestDto> productRequestDtos;
	
}
