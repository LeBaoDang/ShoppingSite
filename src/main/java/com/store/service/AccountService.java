package com.store.service;

import com.store.entity.Account;

public interface AccountService {

	public Account findById(String username);

	public Account saveAccount(Account requestAccount);

}
