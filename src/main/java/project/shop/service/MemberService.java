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
	//회원정보 수정 메소드
	public int modifyMember(MemberVO member) {
		return dao.update(member);
	}
	public MemberVO readMember(int MemberNum) {
		return dao.selectMember(MemberNum);
	}
	
}
