package project.shop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.BoardDao;
import vo.BoardPageVO;
import vo.BoardVO;

@Component
public class BoardService {
	
	@Autowired
	private BoardDao dao;
	public BoardService(BoardDao dao) {
		this.dao = dao;
	}
	/////////////////////////////
	private static final int COUNT_PER_PAGE = 10;
	public int selectSearchTotalCount(int bType, String search) {
		
		return dao.selectSearchTotalCount(bType, search);
	}
	
	public BoardPageVO makeBoardPage(int bType, int currentPage) {
		int totalCount = dao.selectTotalCount(bType);
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
		
		List<BoardVO> boardList=dao.selectList(bType, startRow, COUNT_PER_PAGE);
//		System.out.println(bType + ", " +  startRow + ", " +  totalCount);
		return new BoardPageVO(null,boardList,currentPage,startPage,endPage,totalPage);
	}
	
	public BoardPageVO boardSearch(int bType, String search, int currentPage) {
		int totalCount = dao.selectSearchTotalCount(bType, search);
		int totalPage = totalCount / COUNT_PER_PAGE;

		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		System.out.println(totalCount);
		
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		
		if(totalPage < endPage) {
			endPage=totalPage;
		}
		
		int startRow=(currentPage-1)*COUNT_PER_PAGE;
		
		List<BoardVO> boardList=dao.selectSearch(bType, search, startRow, COUNT_PER_PAGE);
		System.out.println(bType + ", " +  startRow + ", " +  search);

		return new BoardPageVO(null,boardList,currentPage,startPage,endPage,totalPage);
	}
	

	public int write(BoardVO board, String loginId, int bType) {
		board.setbWriteDate(new Date());
		board.setbWriter(loginId);
		board.setbType(bType);
		return dao.insert(board);
	}
	
	public BoardVO read(int bNum, String loginId) {
		BoardVO board = dao.select(bNum);
		
		if(loginId != null && loginId.equals(board.getbWriter())) {
			return board;
		} else {
			dao.updateReadCount(bNum);
			return dao.select(bNum);
		}
	}
	
	public BoardVO readNoCount(int bNum) {
		return dao.select(bNum);
	}
	
	public boolean update(BoardVO board, String loginId) {
		BoardVO original = dao.select(board.getbNum());
		
		if(loginId != null && loginId.equals(original.getbWriter())) {
			board.setbWriteDate(new Date());
			dao.update(board);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delete(int bNum, String loginId) {
		BoardVO board = dao.select(bNum);
		
		if(loginId != null && loginId.equals(board.getbWriter())) {
			dao.delete(bNum);
			return true;
		} else {
			return false;
		}
	}
	
}
