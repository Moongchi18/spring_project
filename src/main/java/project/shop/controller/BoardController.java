package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import project.shop.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	public BoardController(BoardService service) {
		this.service = service;
	}
	
}
