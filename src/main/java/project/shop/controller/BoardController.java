package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import project.shop.service.MemberService;

@Controller
public class BoardController {
	
	@Autowired
	private MemberService service;
	public BoardController(MemberService service) {
		this.service = service;
	}
	
}
