package project.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.ItemService;
import vo.ItemTypeVO;
import vo.ItemVO;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;
	public ItemController(ItemService service) {
		this.service = service;
	}
	/////////////////////////////////////////
	@GetMapping("/items")
	public ModelAndView allItemList(@RequestParam(defaultValue = "1")int page, HttpSession session, @RequestParam(defaultValue = "0")int iType) {
		ModelAndView mv = new ModelAndView();
		

		mv.addObject("iPage", service.makeItemPage(page, iType));
		mv.addObject("allTypeString", service.selectAllTypeString());
		System.out.println(service.selectAllTypeString().get(1));
		
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
			return "login_form";
		}
	}
	
	@PostMapping("/items/write")
	public ModelAndView itemWrite(ItemVO item, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		int m_type = (int)session.getAttribute("m_type");
		boolean result = service.insert(item, loginId, m_type);
		
		int iType=item.getiType();
		if(iType>=10) {
			iType/=10;
		}
		
		if(result) {
			mv.setViewName("redirect:/items?iType="+iType);			
		} else {
			mv.addObject("message", "상품등록을 위해서는 로그인이 필요합니다.");
			mv.setViewName("login_form");
		}
		return mv;
	}
	
	@GetMapping("/items/read")
	public ModelAndView itemRead(int iNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		ItemVO item = service.read(iNum,loginId);
		if (item==null) {
			mv.setViewName("redirect:/items?iType=0");
		} else {
			mv.addObject("item", item);
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
			mv.setViewName("login_form");
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
			mv.setViewName("login_form");
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

}
