package project.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.BoardService;
import vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	@GetMapping("/reviews")
	public ModelAndView board(@RequestParam(defaultValue="1")int page) {
		ModelAndView mv = new ModelAndView();
		int bType=2;
		mv.addObject("bPage", service.makeBoardPage(bType, page));

		mv.setViewName("reviews/list");
		return mv;
	}
	
	@GetMapping("/reviews/search")
	public ModelAndView boardSearch(@RequestParam(defaultValue = "1")int page, String search) {
		ModelAndView mv = new ModelAndView();
		int bType=2;
		mv.addObject("bPage", service.boardSearch(bType, search, page));
		mv.addObject("search", search);
		mv.setViewName("reviews/search");
		return mv;
	}
	
	@GetMapping("/reviews/write")
	public String writeForm(HttpSession session, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		if(loginId != null) {
			return "reviews/write";			
		} else {
			model.addAttribute("message", "게시글을 작성하기 위해서는 로그인이 필요합니다.");
			return "redirect:/loginForm";
		}
	}
	@PostMapping("/reviews/write")
	public ModelAndView write(BoardVO board, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId= (String)session.getAttribute("loginId");
		int result;
		if(loginId != null) {
			result = service.write(board, loginId, 2);
		} else {
			result = 0;
			mv.addObject("message","게시글 등록을 위해서는 로그인이 필요합니다.");
			mv.setViewName("login_form");
		}
		mv.addObject("result", result);
		mv.setViewName("redirect:/reviews");
		return mv;
	}
	
	@GetMapping("/reviews/read")
	public ModelAndView read(@RequestParam("bNum")int bNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId= (String)session.getAttribute("loginId");
		BoardVO board = service.readNoCount(bNum);
		service.read(bNum, loginId);
		service.readNoCount(bNum);
		mv.addObject("board", board);
		mv.addObject("loginId", loginId);
		mv.setViewName("reviews/read");
		return mv;
	}
	
	@GetMapping("/reviews/update")
	public ModelAndView updateForm(int bNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		BoardVO board = service.readNoCount(bNum);
		mv.addObject("original", board);
		String loginId = (String)session.getAttribute("loginId");
		if(board.getbWriter().equals(loginId)) {
			mv.setViewName("reviews/update");
		} else {
			mv.setViewName("login_form");
		}
		return mv;
	}
	
	@PostMapping("/reviews/update")
	public String update(BoardVO board, HttpSession session, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		boolean result = service.update(board, loginId);
		if (result) {
			return "redirect://localhost:8080/reviews/read?bNum="+board.getbNum();
		} else {
			model.addAttribute("bNum", board.getbNum());
			return "reviews/update-fail";
		}
		
	}
	
	@GetMapping("/reviews/delete")
	public ModelAndView delete(int bNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		Boolean result = service.delete(bNum, loginId);
		if(result) {
			mv.setViewName("redirect:/reviews");
		} else {
			mv.addObject("bNum", bNum);
			mv.setViewName("revides/delete-fail");
		}
		return mv;
	}
	
}
