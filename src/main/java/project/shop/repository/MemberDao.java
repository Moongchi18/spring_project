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
	public MemberVO select(String id) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.selectMember(id);
	}
	public int selectMemberNum(String m_id, String m_pw) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.selectMemberNum(m_id, m_pw);
	}
	public int update(MemberVO member) {
		MemberMapper mapper=session.getMapper(MemberMapper.class);
		return mapper.update(member);
	}
	public MemberVO selectMember(int MemberNum) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.select(MemberNum);

	}
}
