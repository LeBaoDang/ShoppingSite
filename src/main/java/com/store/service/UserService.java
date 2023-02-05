package com.store.service;

import com.store.entity.Account;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	AccountService accountService;

	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		try {
			Account account = accountService.findById(username);
			// tạo UserDetails từ Account
			String password = account.getPassword();
			String[] roles = account.getAuthorities().stream()
					.map(au -> au.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username)
					.password(pe.encode(password))
					.roles(roles).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found");
		} 
		 */
		
		try {
			Account user = accountService.findById(username);
			String password = user.getPassword();
			String role[] = user.getAuthorities().stream().map(au -> au.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername( username ).password( password ).roles( role ).build() ;
		} catch (Exception ex) {
			throw new UsernameNotFoundException(username + "not found");
		} 

	}

	/*
	public Users doLogin(Users usersRequest) {
		Users userReponse = userRepo.findByUsername(usersRequest.getUsername());
		if (ObjectUtils.isNotEmpty(userReponse)) {
			// check pass người dùng nhập vào và pass trong database 
			boolean checkPassword = bcrytPass.matches(usersRequest.getHashPassword(), userReponse.getHashPassword());
			return checkPassword ? userReponse : null;
		}
		return null;
	}*/

}
