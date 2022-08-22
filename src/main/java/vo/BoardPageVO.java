package vo;

import java.util.List;

// 페이징 처리
public class BoardPageVO {
	
	private List<BoardVO> boardList;
	private List<ItemVO> itemList;
	private List<OrderVO> orderList;
	
	private int currentPage;
	private int startPage;
	private int endPage;
	private int totalPage;
	
	public BoardPageVO(List<OrderVO> orderList, List<ItemVO> itemList, List<BoardVO> boardList, int currentPage, int startPage, int endPage, int totalPage) {
		this.orderList = orderList;
		this.itemList = itemList;
		this.boardList = boardList;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalPage = totalPage;
	}
	




	public List<OrderVO> getOrderList() {
		return orderList;
	}





	public void setOrderList(List<OrderVO> orderList) {
		this.orderList = orderList;
	}





	public List<ItemVO> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemVO> itemList) {
		this.itemList = itemList;
	}

	public List<BoardVO> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<BoardVO> boardList) {
		this.boardList = boardList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "BoardPageVO [boardList=" + boardList + ", itemList=" + itemList + ", currentPage=" + currentPage
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", totalPage=" + totalPage + "]";
	}
	
	
}
