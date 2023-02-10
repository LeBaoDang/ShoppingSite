package com.store.dto.requestdto;

import java.util.Date;
import java.util.List;

import com.store.dto.reponsedto.CategoryReponseDto;
import com.store.dto.reponsedto.OrderDetailReponseDto;

import lombok.Data;

@Data
public class ProductRequestDto {

private String name;
	
	private String image;
	
	private Double price;
	
	private Date createDate = new Date();
	
	private Boolean available;
	
	private String Description;
	
	private CategoryReponseDto categoryReponseDto;
	
	private List<OrderDetailReponseDto> orderDetailReponseDto;
	
}
