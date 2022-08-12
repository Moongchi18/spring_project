package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import project.shop.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	public MemberController(MemberService service) {
		this.service = service;
	}
	
}
