package project.shop.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vo.BoardVO;

public interface BoardMapper {
	
	public int insert(BoardVO board);
	public int selectTotalCount(int bType);
	public List<BoardVO> selectList(@Param("bType")int bType, @Param("startRow")int startRow, @Param("count")int count);
	public BoardVO select(int bNum);
	public int updateReadCount(int bNum);
	public int update(BoardVO board);
	public int delete(int bNum);
	public List<BoardVO> selectSearch(int bType, String bTitle, String bContent, int startRow, int count);

}
