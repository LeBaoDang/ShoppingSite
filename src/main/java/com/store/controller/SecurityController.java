package com.store.controller;

import com.store.entity.Account;
import com.store.service.AccountService;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/login/form")
    public String loginForm(Model model){
        model.addAttribute("message", " <b style=\"position: absolute;  right: 0px;  width: 300px; color: red \" > Vui lòng đăng nhập! </b> "); 
        return "/security/login";
    }

    @GetMapping("/login/success")
    public String loginSuccess(Model model){
    	 model.addAttribute("message", " <b style=\"position: absolute;  right: 0px;  width: 300px; color: chartreuse \" > Đăng nhập thành công! </b> ");
         return "/security/login";
    }

    @GetMapping("/login/success/oauth2")
    public String loginSuccessOauth2(Model model, OAuth2AuthenticationToken oauth2){
        userService.loginFromOAuth2(oauth2);
        model.addAttribute("message", " <b style=\"position: absolute;  right: 0px;  width: 300px; color: chartreuse \" > Đăng nhập thành công! </b> ");
        return "/security/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("message"," <b style=\"position: absolute;  right: 0px;  width: 300px; color: red \" > Sai thông tin đăng nhập! </b> ");
        return "/security/login";
    }

    @GetMapping("/unauthoried")
    public String unauthoried(Model model){
        model.addAttribute("message"," <b style=\"position: absolute;  right: 0px;  width: 300px; color: red \" > Không có quyền truy xuất! </b> ");
        return "/security/login";
    }

    @GetMapping("/logoff/success")
    public String logoffSuccess(Model model){
        model.addAttribute("message"," <b style=\"position: absolute;  right: 0px;  width: 300px; color: chartreuse \" > Bạn đã đăng xuất thành công! </b>");
        return "/security/login";
    }

    @GetMapping("/profile")
    public String proFile(Model model){
        Account account = accountService.findById(request.getUserPrincipal().getName());
        model.addAttribute("account", account);
        return "/security/profile";
    }


}
