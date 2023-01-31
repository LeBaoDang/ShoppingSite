package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired 
	OrderService orderService;

    // den trang de dat hang
    @GetMapping("/checkout")
    public String checkOut(){
        return "/order/checkout";
    }

    // liet ke tat ca don hang da dat
    @GetMapping("/list")
    public String orderList(Model model, HttpServletRequest request){
        // lấy ra user đã đăng nhập
        String username = request.getRemoteUser();
        // lấy ra list của đơn hàng
        model.addAttribute("orders", orderService.findByUsername(username));
        return "/order/list";
    }

    // xem lai chi tiet don hang da dat
    @GetMapping("/detail/{ id }")
    public String orderDetail(@PathVariable (" id ") Long id, Model model ){
    	model.addAttribute("order", orderService.findById(id));
        return "/order/detail"; 
    }

}
