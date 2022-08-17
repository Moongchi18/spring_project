package project.shop.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.mapper.BoardMapper;
import vo.BoardVO;

@Component
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate session;
	public BoardDao(SqlSessionTemplate session) {
		this.session = session;
	}
	///////////////////////////////////////
	public int insert(BoardVO board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.insert(board);
	}
	
	public int selectTotalCount(int bType) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectTotalCount(bType);
	}
	public int selectSearchTotalCount(int bType, String search) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectSearchTotalCount(bType, search, search);
	}
	public List<BoardVO> selectList(int bType, int startRow, int count){
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectList(bType, startRow, count);
	}
	public BoardVO select(int bNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.select(bNum);
	}
	public int updateReadCount(int bNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.updateReviewReadCount(bNum);
	}
	public int update(BoardVO board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.update(board);
	}
	public int delete(int bNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.delete(bNum);
	}
	public List<BoardVO> selectSearch(int bType, String search, int startRow, int count){
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectSearch(bType, search, search, startRow, count);
	}

}
