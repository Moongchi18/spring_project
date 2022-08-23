package project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.MemberDao;
import project.shop.repository.OrderDao;
import vo.BoardPageVO;
import vo.BoardVO;
import vo.MemberVO;
import vo.OrderVO;

@Component
public class OrderService {
	
	@Autowired
	private OrderDao dao;
	public OrderService(OrderDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	private MemberDao memberDao;
	//////////////////////////////////////////
	public OrderVO readOrder(int oNum, String loginId) {
		OrderVO result = dao.selectOrder(oNum);
		MemberVO member = memberDao.select(loginId);
		if(result.getmId().equals(loginId) || member.getM_type() > 2) {
			return dao.selectOrder(oNum);
		} else {
			return null;
		}
	}
	
	public List<OrderVO> selectOrderById(String loginId){
		
		return dao.selectOrderById(loginId);
	}
	
	public boolean createOrder(OrderVO order, String loginId) {
		
		boolean result;
		int insert = dao.insertOrder(order);
		if(loginId!=null && insert==1) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	public int selectOrderCount(String loginId) {
		return dao.selectOrderCount(loginId);
	}
	private static final int COUNT_PER_PAGE = 10;

	public BoardPageVO makeOrderPage(String loginId, int currentPage){
		int totalCount = dao.selectOrderCount(loginId);
		int totalPage = totalCount / COUNT_PER_PAGE;
		
		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		
		if(totalPage==0) {
			endPage=1;
		} else if(totalPage < endPage) {
			endPage=totalPage;
		}
		
		int startRow=(currentPage-1)*COUNT_PER_PAGE;
		
		List<OrderVO> OrderList;
		OrderList=dao.selectOrderList(loginId, startRow,COUNT_PER_PAGE);
		return new BoardPageVO(OrderList, null, null, currentPage, startPage, endPage, totalPage);
	}
	
	public boolean updateOrderStatus(OrderVO order, String loginId) {
		boolean result=false;
		
		if(order.getiRegister().equals(loginId)) {
			int update = dao.updateOrderStatus(order);
			result = update == 1 ? true:false;
		}	
		return result;
	}
	public BoardPageVO MyOrderPage(String loginId, int currentPage){
		int totalCount = dao.selectOrderCount(loginId);
		int totalPage = totalCount / COUNT_PER_PAGE;
		
		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		
		if(totalPage==0) {
			endPage=1;
		} else if(totalPage < endPage) {
			endPage=totalPage;
		}
		
		int startRow=(currentPage-1)*COUNT_PER_PAGE;
		
		List<OrderVO> OrderList;
		OrderList=dao.selectMyOrderList(loginId, startRow,COUNT_PER_PAGE);
		return new BoardPageVO(OrderList, null, null, currentPage, startPage, endPage, totalPage);
	}

}
