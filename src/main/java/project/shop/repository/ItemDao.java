package project.shop.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.mapper.ItemMapper;
import vo.ItemVO;

@Component
public class ItemDao {

	@Autowired
	private SqlSessionTemplate session;
	public ItemDao(SqlSessionTemplate session) {
		this.session = session;
	}
	///////////////////////////////////////////////
	public int selectAllItemCount() {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectAllItemCount();
	}
	
	public int selectTypeCount(int iType) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectTypeCount(iType);
	}
	
	public int selectBrandCount(String iBrand) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectBrandCount(iBrand);
	}
	
	public List<ItemVO> selectAll(int startRow, int count){
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectAll(startRow, count);
	}
	
	public List<ItemVO> selectType(int iType, int startRow, int count){
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectType(iType, startRow, count);
	}

	public List<ItemVO> selectBrand(String iBrand, int startRow, int count){
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectBrand(iBrand, startRow, count);
	}
	
	public ItemVO selectItem(int iNum) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectItem(iNum);
	}
	
	public int updateItemReadCount(int iNum) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.updateItemReadCount(iNum);
	}
	
	public int updateItem(ItemVO item) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.updateItem(item);
	}
	
	public int deleteItem(int iNum) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.deleteItem(iNum);
	}
	
	public int insertItem(ItemVO item) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.insertItem(item);
	}
	
}
