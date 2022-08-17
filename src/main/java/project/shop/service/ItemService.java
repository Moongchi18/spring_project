package project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.ItemDao;
import vo.BoardPageVO;
import vo.ItemVO;

@Component
public class ItemService {
	
	@Autowired
	private ItemDao dao;

	public ItemService(ItemDao dao) {
		this.dao = dao;
	}
	//////////////////////////////////////
	private static final int COUNT_PER_PAGE = 20;

	public BoardPageVO makeItemPage(int currentPage) {
		int totalCount = dao.selectAllItemCount();
		int totalPage = totalCount / COUNT_PER_PAGE;

		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		
		if(totalPage < endPage) {
			endPage=totalPage;
		}
		
		int startRow=(currentPage-1)*COUNT_PER_PAGE;
		
		List<ItemVO> itemList=dao.selectAll(startRow, COUNT_PER_PAGE);
		return new BoardPageVO(itemList,null,currentPage,startPage,endPage,totalPage);
	}
	
	public BoardPageVO makeItemTypePage(int iType, int currentPage) {
		int totalCount = dao.selectTypeCount(iType);
		int totalPage = totalCount / COUNT_PER_PAGE;
		
		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		
		if(totalPage < endPage) {
			endPage=totalPage;
		}
		
		int startRow=(currentPage-1)*COUNT_PER_PAGE;
		
		List<ItemVO> itemList=dao.selectType(iType, startRow, COUNT_PER_PAGE);
		return new BoardPageVO(itemList,null,currentPage,startPage,endPage,totalPage);
	}
	
	public BoardPageVO makeItemBrandPage(String iBrand, int currentPage) {
		int totalCount = dao.selectBrandCount(iBrand);
		int totalPage = totalCount / COUNT_PER_PAGE;
		
		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		
		if(totalPage < endPage) {
			endPage=totalPage;
		}
		
		int startRow=(currentPage-1)*COUNT_PER_PAGE;
		
		List<ItemVO> itemList=dao.selectBrand(iBrand, startRow, COUNT_PER_PAGE);
		return new BoardPageVO(itemList,null,currentPage,startPage,endPage,totalPage);
	}
	
	public ItemVO read(int iNum, String loginId) {
		ItemVO item = dao.selectItem(iNum);
		if(loginId != null && !item.getiRegister().equals(loginId)) {
			dao.updateItemReadCount(iNum);
		} 
		return item;
	}
	
	public ItemVO readNoCount(int iNum) {
		return dao.selectItem(iNum);
	}
	
	public boolean update(ItemVO item, String loginId) {
		if(loginId != null && loginId.equals(item.getiRegister())) {
			dao.updateItem(item);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delete(int iNum, String loginId) {
		ItemVO item = dao.selectItem(iNum);
		if(loginId != null && loginId.equals(item.getiRegister())) {
			dao.deleteItem(iNum);
			return true;
		} else {
			return false;
		}
	}
}
