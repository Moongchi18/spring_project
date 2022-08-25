package vo;

import java.util.Date;

public class CartVO {

	private int cNum;
	private int mNum;
	private String mId; 
	private int iNum;
	private Date cDate;
	
	public CartVO(int mNum, String mId, int iNum) {
		this.mNum = mNum;
		this.mId = mId;
		this.iNum = iNum;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public int getiNum() {
		return iNum;
	}
	public void setiNum(int iNum) {
		this.iNum = iNum;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	@Override
	public String toString() {
		return "CartVO [cNum=" + cNum + ", mNum=" + mNum + ", mId=" + mId + ", iNum=" + iNum + ", cDate=" + cDate + "]";
	}
	
	
	
}
