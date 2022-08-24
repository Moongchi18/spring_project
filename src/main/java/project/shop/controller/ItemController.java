package project.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.ItemService;
import project.shop.service.MemberService;
import project.shop.service.OrderService;
import vo.ItemOptionVO;
import vo.ItemTypeVO;
import vo.ItemVO;
import vo.MemberVO;
import vo.OrderVO;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;
	public ItemController(ItemService service) {
		this.service = service;
	}
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrderService orderService;
	/////////////////////////////////////////
	@GetMapping("/items")
	public ModelAndView allItemList(@RequestParam(defaultValue = "1")int page, HttpSession session, @RequestParam(defaultValue = "0")int iType) {
		ModelAndView mv = new ModelAndView();
		

		mv.addObject("iPage", service.makeItemPage(page, iType));
		mv.addObject("allTypeString", service.selectAllTypeString());
//		System.out.println(service.selectAllTypeString().get(1));
		
		mv.addObject("type", iType);
		
		ItemTypeVO typeString = service.selectTypeString(iType);
		if (typeString==null) {
			mv.addObject("typeString", null);
		} else {
			mv.addObject("typeString", typeString);
		}
			
		
		mv.setViewName("items/list");

		return mv;
	}
	
	@GetMapping("items/search")
	public ModelAndView itemSearch(@RequestParam(defaultValue = "1")int page, String search) {
		System.out.println(search);
		ModelAndView mv = new ModelAndView();
		mv.addObject("allTypeString", service.selectAllTypeString());

		mv.addObject("iPage", service.searchItem(search, page));
		mv.addObject("search",search);
		mv.setViewName("items/search");
		
		return mv;
	}

	
	@GetMapping("/items/write")
	public String itemWriteForm(HttpSession session, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		int m_type = (int)session.getAttribute("m_type");
		if(loginId != null && m_type > 1) {
			return "items/write";			
		} else {
			model.addAttribute("message", "상품등록을 위해서는 로그인이 필요합니다.");
			return "member/login_form";
		}
	}
	
	@PostMapping("/items/write")
	public ModelAndView itemWrite(ItemVO item, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		int m_type = (int)session.getAttribute("m_type");
		boolean result = service.insert(item, loginId, m_type);
		ItemVO io = service.readItemById(loginId);
		
		
		if(result) {
			mv.addObject("iNum", io.getiNum());
			mv.addObject("iPrice",io.getiPrice());
			mv.addObject("loginId", loginId);
			mv.addObject("iType", io.getiType());
			mv.setViewName("items/write-option");			
		} else {
			mv.addObject("message", "상품등록을 위해서는 로그인이 필요합니다.");
			mv.setViewName("member/login_form");
		}
		return mv;
	}
	
	@GetMapping("/items/write-option")
	public String itemOption(HttpSession session) {
		String loginId=(String)session.getAttribute("loginId");
		if(loginId == null) {
			return "member/login_form";
		} else {
			return "items/write-option";
		}
	}
	
	@PostMapping("/items/write-option")
	public ModelAndView itemOptionWrite(ItemOptionVO io, HttpSession session, int iType) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		int m_type = (int)session.getAttribute("m_type");
		boolean result = service.insertItemOption(io, loginId);
		System.out.println("write iType : " + iType);

		if(iType>=10) {
			iType/=10;
		}
		System.out.println("write iType : " + iType);

		if(result) {
			mv.setViewName("redirect:/items?iType="+iType);	
		} else {
			mv.addObject("message", "로그인 정보가 잘못되었습니다.");
			mv.setViewName("member/login_form");
		}
		
		return mv;
	}
	
	@GetMapping("/items/read")
	public ModelAndView itemRead(int iNum, HttpSession session) {
		System.out.println(iNum);
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		ItemVO item = service.read(iNum,loginId);
		ItemOptionVO io = service.readOption(iNum);
		System.out.println(io);
		
		if (item==null) {
			mv.setViewName("redirect:/items?iType=0");
		} else {
			mv.addObject("item", item);
			mv.addObject("io",io);
			mv.addObject("iTypeString",service.selectTypeString(item.getiType()));
			mv.setViewName("items/read");
		}
		return mv;
	}
	
	@GetMapping("/items/update")
	public ModelAndView itemUpdateForm(int iNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		ItemVO item = service.readNoCount(iNum);
		if(loginId != null && loginId.equals(item.getiRegister())) {
			mv.addObject("item", item);
			mv.setViewName("items/update");
		} else {
			mv.addObject("message", "로그인이 필요하거나, 해당글의 작성자가 아닙니다.");
			mv.setViewName("member/login_form");
		}
		return mv;
	}
	
	@PostMapping("/items/update")
	public ModelAndView itemUpdate(ItemVO item, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		boolean result = service.update(item, loginId);
		System.out.println(item);
		if(result) {
			mv.setViewName("redirect://localhost:8080/items/read?iNum=" + item.getiNum());
		} else {
			mv.addObject("message", "잘못된 접근입니다. 로그인 정보를 확인하세요");
			mv.setViewName("member/login_form");
		}
		return mv;
	}
	
	@GetMapping("/items/delete")
	public ModelAndView itemDelete(int iNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		ItemVO item = service.readNoCount(iNum);
		int iType=item.getiType();
		if (iType>=10) {
			iType=iType/10;
		}
		
		String loginId = (String)session.getAttribute("loginId");

		boolean result = service.delete(iNum, loginId);
		if(result) {
			mv.setViewName("redirect:/items?iType="+iType);
		} else {
			mv.setViewName("redirect://localhost:8080/items/read?iNum="+iNum);
		}
		return mv;
	}
	
	@PostMapping("/items/purchase")
	public ModelAndView itemPurchase(int iNum, String color, String option, int count, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		System.out.println(loginId);
		MemberVO member =  memberService.select(loginId);
		int oDeliveryFee=3000;
		if (member.getM_type()==2) {
			oDeliveryFee=0;
		} else {
			oDeliveryFee=3000;
		}
		
		mv.addObject("iTypeString",service.selectTypeString(service.readNoCount(iNum).getiType()).getiTypeString());
		mv.addObject("oDeliveryFee", oDeliveryFee);
		mv.addObject("color", color);
		mv.addObject("option", option);
		mv.addObject("count", count);
		mv.addObject("mAddress", member.getM_address());
		mv.addObject("item", service.readNoCount(iNum));
		mv.setViewName("items/purchase");
		return mv;
	}
	
	@PostMapping("/items/purchase-request")
	public ModelAndView itemPurchaseResult(OrderVO order, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		MemberVO member =  memberService.select(order.getmId());
		
		order.setmNum(member.getM_num());
		boolean result = orderService.createOrder(order, loginId);
		if(result) {
			mv.setViewName("items/purchase-request");
		} else {
			mv.addObject("message", "잘못된 접근입니다.");
			mv.setViewName("member/login_form");
		}
		
		return mv;
	}

}
