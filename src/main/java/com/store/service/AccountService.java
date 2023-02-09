package com.store.service;

import java.util.List;

import com.store.dto.requestdto.AccountDto;
import com.store.entity.Account;

public interface AccountService {

	public Account findById(String usernameDto);

	public AccountDto saveAccount(AccountDto accountForm);
	
	public List<Account> getAdministrators();
	
	public List<Account> findAll();
	
}
