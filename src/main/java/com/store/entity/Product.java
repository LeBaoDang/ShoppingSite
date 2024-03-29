package com.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity @Table(name = "Products")
public class Product  implements Serializable{
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String image;
	
	private Double price;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	private Date createDate = new Date();
	
	private Boolean available;
	
	private String Description;
	
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;	
}
