package project.shop.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.mapper.MemberMapper;
import vo.MemberVO;

@Component
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public int insert(MemberVO member) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.insert(member);
	}
	public MemberVO select(String m_id) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.select(m_id);
	}
	public int selectMemberNum(String m_id, String m_pw) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.selectMemberNum(m_id, m_pw);
	}
	public int update(MemberVO member) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.update(member);
	}
	public MemberVO selectMember(int m_num) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.selectMember(m_num);

	}
	public MemberVO selectNum(int m_num) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.selectNum(m_num);
	}
	public MemberVO findid(String m_email) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.findid(m_email);
	}
	public MemberVO findpw(String m_id, String m_email) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.findpw(m_id, m_email);
	}
	public MemberVO selectidpw(String m_id, String m_pw) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.selectidpw(m_id, m_pw);
	}
	public int delete(String m_id, String m_pw) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.deleteMember(m_id, m_pw);
	}
	public int idChk(String m_id) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.idChk(m_id);
	}
}
