package com.store.controller;

import com.store.entity.Account;
import com.store.service.AccountService;
import com.store.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    
    @Autowired
    AuthorityService authorityService;

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("account", new Account());
        return "/security/register";
    }

    @PostMapping("/register")
    public String createAccount(Model model,
    		@RequestParam("confirmPassword") String confirmPassword, 
    		@RequestParam("password") String password,
    		@Validated @ModelAttribute("account") Account accountForm, 
    		Errors error){
    	
        if(error.hasErrors()) {
        	if(confirmPassword.equals("")) {
        		model.addAttribute("errorConfirmPassword", "Không được để trống!");
        	}
        	model.addAttribute("message", " <b style=\"position: absolute;  right: 0px;  width: 300px; color: red \" > Bạn vui lòng sửa lỗi sau! </b> " );
        	return "/security/register";
        }
        
        if(!confirmPassword.equals(password)) {
        	model.addAttribute("error", " Xác nhận mật khẩu không chính xác ");
        	return "/security/register";
        } 
        
        else {
        	try {
                accountService.saveAccount(accountForm);
                return "/product/list";
            } catch (Exception ex) {
                model.addAttribute("message", "<b style=\"position: absolute;  right: 0px;  width: 300px; color: red\" > Tài khoản này đã được sử dụng! </b>");
                return "/security/register";
            }
        }
    }
}
