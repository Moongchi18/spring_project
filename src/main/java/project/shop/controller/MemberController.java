package project.shop.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.shop.service.MemberService;
import project.shop.service.OrderService;
import vo.BoardVO;
import vo.MemberVO;
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
		
	@Autowired
	private OrderService orderservice;
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "member/join_form";
	}
	
	@RequestMapping("/join")
	public String join(MemberVO member, HttpSession session) {
		if(session.getAttribute("checkId")=="ok"){			
			if(service.join(member)) {
				session.setAttribute("checkId", "x");
				return "member/join_success";
			}else {
				session.setAttribute("checkId", "x");
				return "member/join_fail";
			}
		} else {
			session.setAttribute("checkId", "x");
			return "member/join_fail";
		}
			
	}
	@RequestMapping("/joincheck")
	public ModelAndView idchk(String m_id, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		int idchk = service.idChk(m_id);
		System.out.println(idchk);
		System.out.println(m_id);
		mv.addObject("id", m_id);
		if(idchk == 1) {
			mv.addObject("message", "중복된 아이디입니다.");
		}else {
			mv.addObject("message", "사용가능한 아이디입니다..");
			session.setAttribute("checkId", "ok");
		}
		mv.setViewName("member/join_form");
		return mv;
	}
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "member/login_form";
	}
	@RequestMapping("/login")
	public String login(String m_id,String m_pw, HttpSession session) {
		MemberVO member = service.getMemberInfo(m_id);
		System.out.println(member);
		if(service.login(m_id, m_pw)) {
			session.setAttribute("loginId", m_id);
			session.setAttribute("m_type", member.getM_type());

			return "redirect:/";
		}else {
			return "member/login_fail";
		}
	}
	@RequestMapping("/myPage")
	public ModelAndView myPage(@RequestParam(defaultValue = "1")int page, HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		
		String loginId = (String)session.getAttribute("loginId");
		
		if(loginId != null && loginId.length() > 0) {
			
			MemberVO member = service.getMemberInfo(loginId);
			
			mv.addObject("MyOrderList", orderservice.MyOrderPage(loginId, page));
			mv.addObject("memberInfo",member);
			mv.setViewName("member/my_page");
		}else {
			System.out.println("로그인 정보 없음");
		}return mv;
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/updateForm")
	public ModelAndView updateForm(@RequestParam("m_num")int m_num) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("original", service.selectNum(m_num));
		mv.setViewName("member/update_form");
		return mv;
	}
	
	
	@PostMapping("/update")
	public ModelAndView update(MemberVO member) {
		ModelAndView mv = new ModelAndView();
		int result = service.update(member);
		
		mv.addObject("updateResult",result);
		mv.setViewName("member/update_result");
		return mv;
	}
	@RequestMapping("/FindIdForm")
	public String FindIdForm() {
		return "member/find_id";
	}
	

	@RequestMapping("/FindId")
	public ModelAndView findid(String m_email) {
		ModelAndView mv = new ModelAndView();
		MemberVO result = service.findid(m_email);
		
		if(result != null) {
			mv.addObject("findidsuccess",result);
			mv.setViewName("member/find_successid");
		}else {
			mv.addObject("message", "이메일 주소를 다시 확인해주세요");
			mv.addObject("result", result);
			mv.setViewName("member/find_id");
		}
		return mv;	
	}
	@RequestMapping("/FindPasswordForm")
	public String FindPwForm() {
		return "member/find_pw";
	}
	@RequestMapping("/FindPw")
	public ModelAndView findpw(String m_id, String m_email) {
		ModelAndView mv = new ModelAndView();
		MemberVO result = service.findpw(m_id, m_email);
		
		if(result != null) {
			mv.addObject("findpwsuccess",result);
			mv.setViewName("member/find_successpw");
		}else {
			mv.addObject("message", "이메일 주소또는 아이디를 다시 확인해주세요");
			mv.setViewName("member/find_pw");
		}
		return mv;	
	}
	@RequestMapping("/deleteform")
	public ModelAndView deleteform(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
//		mv.addObject("memberInfo", memberInfo);
		mv.setViewName("member/delete_member");
		return mv;
		}
	
	@RequestMapping("/deleteMember")
	public ModelAndView deleteMember(String m_id, String m_pw) {
		ModelAndView mv = new ModelAndView();
		System.out.println(m_id + " / " + m_pw);
		MemberVO result = service.getMemberInfo2(m_id, m_pw);
		
		if(result != null) {
			mv.addObject(result);
			mv.addObject("m_id", m_id);
			mv.addObject("m_pw", m_pw);
			mv.setViewName("member/delete_success");
		}else {
			mv.addObject("message", "아이디와 비밀번호가 회원정보와 다릅니다.");
			mv.setViewName("member/delete_member");
		}
		return mv;
	}
	@RequestMapping("/delete")
	public ModelAndView delete(String m_id, String m_pw, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("m_id : " + m_id);
		System.out.println("m_pw : " + m_pw);
		int result = service.delete(m_id, m_pw);
		session.invalidate();
		
		mv.addObject("deletecomplete",result);
		mv.setViewName("redirect:/");
		return mv;
	}
	

}





