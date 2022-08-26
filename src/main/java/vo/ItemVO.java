package vo;

import java.util.Date;

import org.springframework.stereotype.Component;

public class ItemVO {
	private int iNum;
	private String iBrand;
	private String iRegister;
	private int iType;
	private String iName;
	private int iPrice;
	private int iStock;
	private String iImage;
	private String iExplanation;
	private int iReadCount;
	
	public String getiRegister() {
		return iRegister;
	}
	public void setiRegister(String iRegister) {
		this.iRegister = iRegister;
	}
	public int getiReadCount() {
		return iReadCount;
	}
	public void setiReadCount(int iReadCount) {
		this.iReadCount = iReadCount;
	}
	private Date iDate;
	public int getiNum() {
		return iNum;
	}
	public void setiNum(int iNum) {
		this.iNum = iNum;
	}
	public String getiBrand() {
		return iBrand;
	}
	public void setiBrand(String iBrand) {
		this.iBrand = iBrand;
	}
	public int getiType() {
		return iType;
	}
	public void setiType(int iType) {
		this.iType = iType;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
	public int getiPrice() {
		return iPrice;
	}
	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}
	public int getiStock() {
		return iStock;
	}
	public void setiStock(int iStock) {
		this.iStock = iStock;
	}
	public String getiImage() {
		return iImage;
	}
	public void setiImage(String iImage) {
		this.iImage = iImage;
	}
	public String getiExplanation() {
		return iExplanation;
	}
	public void setiExplanation(String iExplanation) {
		this.iExplanation = iExplanation;
	}
	
	public Date getiDate() {
		return iDate;
	}
	public void setiDate(Date iDate) {
		this.iDate = iDate;
	}
	@Override
	public String toString() {
		return "ItemVO [iNum=" + iNum + ", iBrand=" + iBrand + ", iRegister=" + iRegister + ", iType=" + iType
				+ ", iName=" + iName + ", iPrice=" + iPrice + ", iStock=" + iStock + ", iImage=" + iImage
				+ ", iExplanation=" + iExplanation + ", iReadCount=" + iReadCount + ", iDate=" + iDate + "]";
	}
	
	
}
