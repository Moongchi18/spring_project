package project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.ItemDao;
import vo.BoardPageVO;
import vo.ItemTypeVO;
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

	public BoardPageVO makeItemPage(int currentPage, int iType) {
		int totalCount;
		if (iType==0) {
			totalCount = dao.selectAllItemCount();
		} else {
			totalCount = dao.selectTypeCount(iType);
		}
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
		
		List<ItemVO> itemList;
		if (iType==0) {
			itemList=dao.selectAll(startRow, COUNT_PER_PAGE);			
		} else {
			itemList=dao.selectType(iType, startRow, COUNT_PER_PAGE);
		}
		
//		System.out.println("currentPage : " + currentPage );
//		System.out.println("startPage : " + startPage );
//		System.out.println("endPage : " + endPage );
//		System.out.println("totalPage : " + totalPage );
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
	
	public boolean insert(ItemVO item, String loginId, int m_type) {
		boolean result = false;
		if(loginId != null && m_type>1) {
			result = (dao.insertItem(item)>0) ? true:false; 
		}
		return result; 
	}
	
	public boolean update(ItemVO item, String loginId) {
		if(loginId != null && loginId.equals(item.getiRegister())) {
			int update = dao.updateItem(item);
			System.out.println("update" +update);
			if(update>0) {
				return true;
			} else {
				return false;
			}
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
	
	public List<ItemTypeVO> selectAllTypeString(){
		return dao.selectAllTypeString();
	}
	
	public ItemTypeVO selectTypeString(int iType) {
		return dao.selectTypeString(iType);
	}
	
<<<<<<< HEAD
	public List<ItemVO> mainItempage(int iType) {
		
		int startRow = 0;
		int getNum = 4;
		List<ItemVO> itemList;
		if (iType==0) {
			itemList=dao.selectAll(startRow, getNum);
		}else {
			itemList=dao.selectType(iType, startRow, getNum);
		}
		return itemList;
	}
//	public BoardPageVO mainitempage(int currentPage, int iType) {
//		int totalCount;
//		if(iType==0) {
//			totalCount = dao.selectAllItemCount();
//		}else {
//			totalCount = dao.selectTypeCount(iType);
//		}
//		int totalPage = totalCount / COUNT_PER_PAGE;
//		
//		if(totalCount%COUNT_PER_PAGE!=0) {
//			totalPage++;
//		}
//		
//		int startPage = (currentPage-1)/10*10+1;
//		int endPage = startPage + 3;
//		
//		if(totalPage < endPage) {
//			endPage = totalPage;
//		}
//		
//		int startRow=(currentPage-1)*COUNT_PER_PAGE;
//		
//		List<ItemVO> itemList;
//		if (iType==0) {
//			itemList=dao.selectAll(startRow, COUNT_PER_PAGE);
//		}else {
//			itemList=dao.selectType(iType, startRow, COUNT_PER_PAGE);
//		}
//		return new BoardPageVO(itemList,null,currentPage,startPage,endPage,totalPage);
//	}
=======
	public BoardPageVO searchItem(String search, int currentPage) {
		int totalCount=dao.selectSearchCount(search) ;
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
		
		List<ItemVO> itemList = dao.selectSearch(search, startRow, COUNT_PER_PAGE);
		
		System.out.println("currentPage : " + currentPage );
		System.out.println("startPage : " + startPage );
		System.out.println("endPage : " + endPage );
		System.out.println("totalPage : " + totalPage );
		return new BoardPageVO(itemList,null,currentPage,startPage,endPage,totalPage);
	}
	
	
>>>>>>> 83f2d098e9b4ebaf73cbb42167721fa527a1907d
}
