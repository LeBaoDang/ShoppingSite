package com.store.service;

import java.util.List;

import com.store.entity.Account;
import com.store.entity.Role;

public interface AccountService {

	public Account findById(String username);

	public Account saveAccount(Account requestAccount);
	
	public List<Account> getAdministrators();
	
	public List<Account> findAll();
	
}
