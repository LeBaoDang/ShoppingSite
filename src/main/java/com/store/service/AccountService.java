package com.store.service;

import java.util.List;

import com.store.dto.reponsedto.AccountReponseDto;
import com.store.dto.requestdto.AccountRequestDto;
import com.store.entity.Account;
import com.store.entity.Authority;
import com.store.entity.Role;

public interface AccountService {

	public Account findById(String usernameDto);

	public AccountReponseDto saveAccount(AccountRequestDto accountForm);
	
	public List<Account> getAdministrators();
	
	public List<Account> findAll();

	public List<Authority> RoleFindAll(String username);
	
}
