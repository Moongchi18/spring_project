package project.shop.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate session;
	public MemberDao(SqlSessionTemplate session) {
		this.session = session;
	}
	

}
