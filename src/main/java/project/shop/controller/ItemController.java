package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;
	public ItemController(ItemService service) {
		this.service = service;
	}
	/////////////////////////////////////////
	
	@GetMapping("items")
	public ModelAndView itemList(@RequestParam(defaultValue = "1")int page) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("iPage", service.makeItemPage(page));
		mv.setViewName("items/list");
		return mv;
	}
	
}
