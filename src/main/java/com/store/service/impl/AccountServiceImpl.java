package com.store.service.impl;

import com.store.entity.Account;
import com.store.repository.AccountRepo;
import com.store.repository.AuthorityRepo;
import com.store.service.AccountService;
import com.store.service.impl.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepo accountRepo;
    
    @Autowired
    AuthorityRepo authorityRepo;

    private final BCryptPasswordEncoder bcrytPass = new BCryptPasswordEncoder();

    @Override
    public Account findById(String username) {
        Optional<Account> optionalAccount = accountRepo.findById(username);
        if(!optionalAccount.isPresent()){
            throw new ResourceNotFoundException(String.format("account.not.found.with.username:%s", username));
        }
        return accountRepo.findById(username).get();
    }

	@Override
    /* => trong String_Boot khi ko định nghĩ cụ thể thì @Transactional nó mặc định chỉ bắt Error
    * => kích hoạt cơ chế rollbackon khi có xảy ra lỗi Exception & Error */
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public Account saveAccount(Account requestAccount) {
        Optional<Account> optionalAccount = accountRepo.findById(requestAccount.getUsername());
        if(optionalAccount.isPresent()) {
            throw new ResourceNotFoundException(String.format("account.already.exist.with.username:%s", requestAccount.getUsername()));
        }
        requestAccount.setPassword(bcrytPass.encode(requestAccount.getPassword()));
        accountRepo.save(requestAccount);
        authorityRepo.register(requestAccount.getUsername());
		return requestAccount;
	}

}
