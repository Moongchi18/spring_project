package project.shop.controller;

import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.BoardService;
import project.shop.service.ItemService;

import vo.ItemVO;
import vo.BoardVO;

@Controller
public class IndexController {
	
	@Autowired
	private ItemService itemService;
	public IndexController(ItemService service) {
		this.itemService=service;
	}
	@Autowired
	private BoardService boardService;

	
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(defaultValue = "0")int iType) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("test : " + itemService.selectReadCount());
		mv.addObject("readcountbest", itemService.selectReadCount());
		mv.addObject("itemlist", itemService.mainItempage(iType));	
		mv.setViewName("index");
		return mv;
	}
}
