package project.shop.repository.mapper;

import java.util.List;

import vo.OrderVO;

public interface OrderMapper {
	
	public int insertOrder(OrderVO order);
	public OrderVO selectOrder(int oNum);
	public int selectOrderCount(String loginId);
	public List<OrderVO> selectOrderList(String loginId, int startRow, int count);
}
