package project.shop.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vo.ItemVO;


public interface ItemMapper {
	
	public int selectAllItemCount();
	public int selectTypeCount(int iType);
	public int selectBrandCount(String iBrand);
	public List<ItemVO> selectAll(@Param("startRow")int startRow, @Param("count")int count);
	public List<ItemVO> selectType(@Param("iType")int iType, @Param("startRow")int startRow, @Param("count")int count);
	public List<ItemVO> selectBrand(@Param("iBrand")String iBrand, @Param("startRow")int startRow, @Param("count")int count);
	
	public ItemVO selectItem(int iNum);

	public int insertItem(ItemVO item);

	public int updateItemReadCount(int iNum);
	public int updateItem(ItemVO item);
	
	public int deleteItem(int iNum);
//	public List<ItemVO> selectSearch(int iType, String search1, String search2, int startRow, int count);
	

}
