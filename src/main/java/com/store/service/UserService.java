package com.store.service;

import com.store.entity.Account;

import java.util.Optional;
import java.util.stream.Collectors;

import com.store.repository.AccountRepo;
import com.store.repository.AuthorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	AccountService accountService;

	@Autowired
	AuthorityRepo authorityRepo;

	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	AccountRepo accountRepo;

	private final BCryptPasswordEncoder bcrytPass = new BCryptPasswordEncoder();

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
			return User.withUsername(username).password(password).roles(role).build() ;
		} catch (Exception ex) {
			throw new UsernameNotFoundException(username + "not found");
		} 

	}

    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		// sinh ra 1 mật khẩu ngẫu nhiên từ thời điểm hệ thống
		String password = Long.toHexString(System.currentTimeMillis());
		UserDetails user = User.withUsername(email)
				.password(pe.encode(password)).roles("GUEST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		// nơi chứa toàn bộ tt về security
		SecurityContextHolder.getContext().setAuthentication(auth);

		Account account = new Account();
		Optional<Account> optionalAccount = accountRepo.findById(email);
		if(!optionalAccount.isPresent()){
			account.setPassword(bcrytPass.encode(password));
			String fullname = oauth2.getPrincipal().getAttribute("name");
			account = new Account( email, account.getPassword(), fullname, email,null  );
			accountRepo.save(account);
			authorityRepo.register(email);
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
