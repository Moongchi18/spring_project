package project.shop.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.MemberService;
import vo.BoardVO;
import vo.MemberVO;
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
		
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "join_form";
	}
	@RequestMapping("/join")
	public String join(MemberVO member) {
		if(service.join(member)) {
			return "join_success";
		}else {
			return "join_fail";
		}
	}
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "login_form";
	}
	@RequestMapping("/login")
	public String login(String m_id,@RequestParam("m_pw")String m_pw, HttpSession session) {
		MemberVO member = service.getMemberInfo(m_id);
		
		if(service.login(m_id, m_pw)) {
			session.setAttribute("loginId", m_id);
			session.setAttribute("m_type", member.getM_pw());
			return "index";
		}else {
			return "login_fail";
		}
	}
	@RequestMapping("/myPage")
	public ModelAndView myPage(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		
		String loginId = (String)session.getAttribute("loginId");
		
		if(loginId != null && loginId.length() > 0) {
			
			MemberVO member = service.getMemberInfo(loginId);
			mv.addObject("memberInfo",member);
			mv.setViewName("my_page");
		}else {
			System.out.println("로그인 정보 없음");
		}return mv;
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/updateForm")
	public ModelAndView updateForm(@RequestParam("m_num")int m_num) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("original", service.selectNum(m_num));
		mv.setViewName("update_form");
		return mv;
	}
	
	
	@PostMapping("/update")
	public ModelAndView update(MemberVO member) {
		ModelAndView mv = new ModelAndView();
		int result = service.update(member);
		
		mv.addObject("updatem_num", member.getM_num());
		mv.addObject("updateResult",result);
		mv.setViewName("update_result");
		return mv;
	}
	@RequestMapping("/FindIdForm")
	public String FindIdForm() {
		return "find_id";
	}
	
	@RequestMapping("/FindId")
	public String find_id(MemberVO member) {
		if(service.findid(member))
		
	}
	
	@RequestMapping("/join")
	public String join(MemberVO member) {
		if(service.join(member)) {
			return "join_success";
		}else {
			return "join_fail";
		}








}