package com.store.service.impl;

import com.store.dto.reponsedto.AccountReponseDto;
import com.store.dto.requestdto.AccountRequestDto;
import com.store.entity.Account;
import com.store.entity.Authority;
import com.store.entity.Role;
import com.store.repository.AccountRepo;
import com.store.repository.AuthorityRepo;
import com.store.service.AccountService;
import com.store.service.RoleService;
import com.store.service.impl.exception.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	AuthorityRepo authorityRepo;
	
	@Autowired
	RoleService roleService;
	
	private final ModelMapper mapper = new ModelMapper();

	private final BCryptPasswordEncoder bcrytPass = new BCryptPasswordEncoder();

	@Override
	public Account findById(String username) {
		Optional<Account> optionalAccount = accountRepo.findById(username);
		if (!optionalAccount.isPresent()) {
			throw new ResourceNotFoundException(String.format("account.not.found.with.username:%s", username));
		}
		return accountRepo.findById(username).get();
	}

	/*
	 * => trong String_Boot khi ko định nghĩ cụ thể thì @Transactional nó mặc định
	 * chỉ bắt Error => kích hoạt cơ chế rollbackon khi có xảy ra lỗi Exception &
	 * Error
	 */
	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public AccountReponseDto saveAccount(AccountRequestDto AccountDto) {
		Account account = new Account();
		Optional<Account> optionalAccount = accountRepo.findById(AccountDto.getUsername());
		if (optionalAccount.isPresent()) {
			throw new ResourceNotFoundException(
					String.format("account.already.exist.with.username:%s", AccountDto.getUsername()));
		}
		
		AccountDto.setPassword(bcrytPass.encode(AccountDto.getPassword()));
		
		account  =  mapper.map(AccountDto, Account.class);
		
		accountRepo.save(account);
		
		authorityRepo.register(account.getUsername());
		
		AccountReponseDto accountReponseDto = mapper.map(account, AccountReponseDto.class);
		
		return accountReponseDto;
	}

	@Override
	public List<Account> getAdministrators() {
		return accountRepo.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		return accountRepo.findAll();
	}

	@Override
	public List<Authority> RoleFindAll(String username) {
		return authorityRepo.findAuthority(username);
	}
}
