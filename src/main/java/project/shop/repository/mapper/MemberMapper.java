package project.shop.repository.mapper;

import org.apache.ibatis.annotations.Param;

import vo.MemberVO;

public interface MemberMapper {
	
	public int insert(MemberVO member);
	public MemberVO select(String m_id);
	public int selectMemberNum(@Param("m_id")String id,@Param("m_pw")String pw);
	public int update(MemberVO member);
	public MemberVO selectMember(int m_num);
	public MemberVO selectNum(int m_num);
	public MemberVO findid(String m_email);

}
