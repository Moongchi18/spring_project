package project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import project.shop.repository.MemberDao;
import vo.MemberVO;

@Component
public class MemberService {
	
	@Autowired
	private MemberDao dao;	
	//회원가입 메소드
	public boolean join(MemberVO member)  throws DuplicateKeyException{
		try {
		if(dao.insert(member) > 0) {
			return true;
		}else {
			return false;
		}
	} catch (DuplicateKeyException e) {
		e.printStackTrace();		
	}finally {
		System.out.println("에러");
	}
	return false;
	}
<<<<<<< HEAD
	
=======
//	public boolean findid(String m_email) {
//		if(dao.findid(m_email) == 1) {
//			return true;
//		}else {
//			return false;
//		}
//	}
>>>>>>> e01d8ae86e39346dd11e70e372f8a57b72552b11
	//로그인 메소드
	public boolean login(String m_id,String m_pw) {
		if(dao.selectMemberNum(m_id, m_pw) == 1) {
			return true;
		}else {
			return false;
		}
	}
	//회원정보목록 메소드
	public MemberVO getMemberInfo(String loginId) {
		return dao.select(loginId);
	}
	
	/////////////////////////////////////////////
	public MemberVO selectNum(int m_num) {
		return dao.selectNum(m_num);
	}
	//////////////////////////////////////////////
	public MemberVO select(String m_id) {
		return dao.select(m_id);
	}
	//회원정보 수정 메소드
	public int update(MemberVO member) {
		return dao.update(member);
	}
	public MemberVO findid(String m_email) {
		return dao.findid(m_email);
	}
	public MemberVO findpw(String m_id, String m_email) {
		return dao.findpw(m_id, m_email);
	}
	
	public MemberVO getMemberInfo2(String id ,String pw) {
		return dao.selectidpw(id,pw);
	}
	public int delete(String m_id, String m_pw) {
		return dao.delete(m_id, m_pw);
	}
}







