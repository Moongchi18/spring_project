package project.shop.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.mapper.OrderMapper;
import vo.OrderVO;

@Component
public class OrderDao {
	
	@Autowired
	private SqlSessionTemplate session;
	public OrderDao(SqlSessionTemplate session) {
		this.session = session;
	}
	////////////////////////////////////////////////////////////
	public int insertOrder(OrderVO order) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.insertOrder(order);
	}
	
	public OrderVO selectOrder(int oNum) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.selectOrder(oNum);
	}
	
	public int selectOrderCount(String loginId) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.selectOrderCount(loginId);
	}
	
	public List<OrderVO> selectOrderList(String loginId, int startRow, int count){
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.selectOrderList(loginId, startRow, count);
	}
	
	public List<OrderVO> selectOrderById(String loginId){
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.selectOrderById(loginId);
	}
	
}
