package com.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Accounts")
public class Account  implements Serializable{
	
	@Id
	@NotBlank(message = "Vui lòng nhập username")
	private String username;
	
	@NotBlank(message = "Vui lòng nhập password")
	private String password;
	
	@NotBlank(message = "Vui lòng nhập họ và tên")
	private String fullname;
	
	@NotBlank(message = "Vui lòng nhập email")
	@Email(message = "không đúng định dạng email")
	private String email;
	
	private String photo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Authority> authorities;


	public Account(String username, String password, String fullname, String email, String photo) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.photo = photo;
	}

	public Account() {
	}
}
