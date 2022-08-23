package project.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView createOrder(@RequestParam(defaultValue = "1")int page, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		
		mv.addObject("pageList", service.makeOrderPage(loginId, page));
		mv.setViewName("sales/list");
		return mv;
	}
	@RequestMapping("/myPage/MyOrder")
	public ModelAndView MyOrder(@RequestParam(defaultValue = "1")int page, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		
		mv.addObject("MyOrderList", service.MyOrderPage(loginId, page));
		mv.setViewName("member/MyOrder");
		return mv;
	}
	
	@PostMapping("/sales/update")
	public ModelAndView updateOrder(OrderVO order, HttpSession session) {
		System.out.println(order);
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		System.out.println(loginId);
		boolean result = service.updateOrderStatus(order, loginId);
		
		if(result) {
			mv.setViewName("redirect:/sales");
		} else {
			mv.addObject("message", "잘못된 접근입니다.");
			mv.setViewName("member/login_form");
		}
		
		return mv;
	}
	
	@GetMapping("sales/read")
	public ModelAndView readOrder(int oNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		OrderVO order = service.readOrder(oNum, loginId);
		if(order==null) {
			mv.addObject("message", "잘못된 접근입니다.");
			mv.setViewName("member/login_form");
		} else {
			mv.addObject("order", order);
			mv.setViewName("sales/read");
		}
		
		return mv;
	}
}
