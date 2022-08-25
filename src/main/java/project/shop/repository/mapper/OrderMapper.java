package project.shop.repository.mapper;

import java.util.List;

import vo.CartVO;
import vo.JoinCartAndItemVO;
import vo.OrderVO;

public interface OrderMapper {
	
	public int insertOrder(OrderVO order);
	public OrderVO selectOrder(int oNum);
	public int selectOrderCount(String loginId);
	public List<OrderVO> selectOrderList(String loginId, int startRow, int count);
	public List<OrderVO> selectOrderById(String loginId);
	public int updateOrderStatus(OrderVO order);
	public List<OrderVO> selectMyOrderList(String loginId, int startRow, int count);
	public int updateOrderRequest(OrderVO order);
	
	public int insertCart(CartVO cart);
	public List<CartVO> selectCartById(int mNum);
	public int deleteCartItem(int iNum);
	public int deleteCartAll(int mNum);
	
	public List<JoinCartAndItemVO> joinItemCart(String mId);
}
