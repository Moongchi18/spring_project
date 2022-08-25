package project.shop.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.mapper.OrderMapper;
import vo.CartVO;
import vo.JoinCartAndItemVO;
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
	
	public int updateOrderStatus(OrderVO order) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.updateOrderStatus(order);
	}
	public List<OrderVO> selectMyOrderList(String loginId, int startRow, int count) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.selectMyOrderList(loginId, startRow, count);
	}
	public int updateOrderRequest(OrderVO order) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.updateOrderRequest(order);
	}
	// 장바구니
	public int insertCartItem(CartVO cart) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.insertCart(cart);
	}
	public List<CartVO> selectCart(int mNum) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.selectCartById(mNum);
	}
	public int deleteCartItem(int iNum) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.deleteCartItem(iNum);
	}
	public int deleteCartAll(int mNum) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.deleteCartAll(mNum);
	}
	public List<JoinCartAndItemVO> joinItemCart(String mId){
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		return mapper.joinItemCart(mId);
	}
}
