package com.store.dto.reponsedto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductReponseDto {
	
	private String name;
	
	private String image;
	
	private Double price;
	
	private Date createDate = new Date();
	
	private Boolean available;
	
	private String Description;
	
	private CategoryReponseDto categoryReponseDto;
	
	private List<OrderDetailReponseDto> orderDetailReponseDto;
}
