package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {
	

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

}
