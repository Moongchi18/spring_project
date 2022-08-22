package project.shop.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.mapper.ItemMapper;
import vo.ItemOptionVO;
import vo.ItemTypeVO;
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
	
	public ItemVO selectItemById(String iRegister) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectItemById(iRegister);
	}
	
	public ItemOptionVO selectItemOption(int iNum) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectItemOption(iNum);
	}
	
	public int updateItemReadCount(int iNum) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.updateItemReadCount(iNum);
	}
	
	public int updateItem(ItemVO item) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.updateItem(item);
	}
	
	public int updateItemOption(ItemOptionVO io) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.updateItemOption(io);
	}
	
	public int deleteItem(int iNum) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.deleteItem(iNum);
	}
	
	public int insertItem(ItemVO item) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.insertItem(item);
	}
	
	public int insertItemOption(ItemOptionVO io) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.insertItemOption(io);
	}
	
	public List<ItemTypeVO> selectAllTypeString(){
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectAllTypeString();
	}
	public ItemTypeVO selectTypeString(int iType){
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectTypeString(iType);
	}
	public int selectSearchCount(String search) {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectSearchCount(search, search);
	}
	public List<ItemVO> selectSearch(String search, int startRow, int count){
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectSearch(search, search, startRow, count);
	}
	public ItemVO selectReadCount() {
		ItemMapper mapper = session.getMapper(ItemMapper.class);
		return mapper.selectReadCount();
	}

}
