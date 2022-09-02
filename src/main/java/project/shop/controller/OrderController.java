package project.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.MemberService;
import project.shop.service.OrderService;
import vo.CartVO;
import vo.MemberVO;
import vo.OrderVO;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService service;
	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@Autowired
	private MemberService memberService;
	///////////////////////////////////////
	@GetMapping("/sales")
	public ModelAndView createOrder(@RequestParam(defaultValue = "1")int page, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		mv.addObject("allCount", service.selectOrderCount(loginId));
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
	@PostMapping("/sales/updateall")
	public ModelAndView updateAllOrder(@RequestParam(value = "oNumList[]")ArrayList<Integer> oNumList, 
			@RequestParam(value = "oStatusList[]")ArrayList<String> oStatus, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		System.out.println(loginId);
		for(int i=0; i<oNumList.size(); i++) {
			OrderVO order = service.readOrder(oNumList.get(i), loginId);
			System.out.println(oStatus.get(i));
			order.setoStatus(oStatus.get(i));
			boolean result = service.updateOrderStatus(order, loginId);
			if(!result) {
				mv.addObject("message", "잘못된 접근입니다.");
				mv.setViewName("member/login_form");
				break;
			}
		}

		mv.setViewName("redirect:/sales");
		return mv;
	}
		
	@PostMapping("/myPage/MyOrder/update")
	public ModelAndView updateOrderRequest(OrderVO order, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		
		System.out.println(order);
		boolean result = service.updateOrderRequest(order, loginId);
		if(result) {
			mv.setViewName("redirect:/myPage/MyOrder");
		}else {
			mv.addObject("message","잘못된 접근입니다.");
			mv.setViewName("member/login_form");
		}
		return mv;
	}
	
	@GetMapping("/sales/read")
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
	
	@GetMapping("/cart/additem")
	public ModelAndView addItemInCart(int iNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		int mNum = memberService.getMemberInfo(loginId).getM_num();
		System.out.println(new CartVO(mNum,loginId,iNum));
		service.insertCartItem(new CartVO(mNum,loginId,iNum));
		
		mv.setViewName("redirect://192.168.0.3:8080/items/read?iNum="+iNum);
		return mv;
	}
	
	@GetMapping("/cart")
	public ModelAndView cartList(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		System.out.println(loginId);
		System.out.println("조인 확인 : " + service.joinItemCart(loginId));
		
		mv.addObject("deliveryFee", memberService.select(loginId).getM_type()==2 ? 0:3000);
		
		mv.addObject("joinList", service.joinItemCart(loginId));
		
		mv.addObject("테스트","테스트");
		
		mv.setViewName("sales/cart");
		return mv;
	}
	
	@GetMapping("/cart/deleteone")
	@ResponseBody
	public HashMap<String, String> deleteCartOne(@RequestParam("iNum")int iNum, HttpSession session){
		System.out.println("delete : " + iNum);
		HashMap<String, String> map = new HashMap<>();
		String loginId = (String)session.getAttribute("loginId");
		
		boolean result = service.deleteCartItem(iNum, loginId);
		if(result) {
			map.put("message", "1개 삭제 성공");
		} else {
			map.put("message", "삭제 실패");
		}
		return map;
	}
	
	@PostMapping("/cart/deleteall")
	@ResponseBody
	public HashMap<String, String> deleteCartAll(@RequestParam("iNumArr[]")int[] iNumArr, HttpSession session){
		System.out.println("delete : " + iNumArr[0]);
		HashMap<String, String> map = new HashMap<>();
		String loginId = (String)session.getAttribute("loginId");
		ArrayList<Integer> iNumList = new ArrayList<Integer>();
		for(int i=0; i<iNumArr.length; i++) {
			iNumList.add(iNumArr[i]);
		}
		HashMap<Integer, Integer> duplicate_count = new HashMap<>();
		for(int i = 0; i<iNumList.size(); i++) {
			if(duplicate_count.containsKey(iNumList.get(i))){
				duplicate_count.put(iNumList.get(i), duplicate_count.get(iNumList.get(i)) +1);
			} else {
				duplicate_count.put(iNumList.get(i), 1);
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		for(int i = 0; i<duplicate_count.size(); i++) {
			if(duplicate_count.get(iNumList.get(i)) % 2 == 1){
				result.add(iNumList.get(i));
			}
		}
		
		for(int i = 0; i<result.size(); i++) {
			boolean res = service.deleteCartItem(result.get(i), loginId);
			System.out.println(res);
		}
		
		map.put("message", result.size() + "개의 장바구니 상품을 삭제했습니다.");
		
		return map;
	}
	
	@GetMapping("/carts")
	@ResponseBody
	@Async
	public HashMap<String, String> test(){
		HashMap<String, String> map = new HashMap<>();
		
		map.put("text", "테스트");
		
		return map;
	}
	
}
