package project.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.OrderService;
import vo.OrderVO;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService service;
	public OrderController(OrderService service) {
		this.service = service;
	}
	///////////////////////////////////////
	@GetMapping("/sales")
	public ModelAndView createOrder(@RequestParam(defaultValue = "1")int page,OrderVO order, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		System.out.println(order);
		if(order.getiRegister().equals(loginId)){
			mv.addObject("order", service.makeOrderPage(loginId, page));
			mv.setViewName("sales/list");
		} else {
			mv.addObject("message", "잘못된 접근입니다.");
			mv.setViewName("member/login_form");
		}
		
		return mv;
	}
	
	
	
}
