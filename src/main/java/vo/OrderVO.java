package vo;

import java.util.Date;

public class OrderVO {
	
	private int oNum;
	private Date oDate;
	private int iNum;
	private String iRegister;
	private String ioColor;
	private String ioOption;
	private int mNum;
	private String mId;
	private int iPrice;
	private int oCount;
	private int oDeliveryFee;
	private int oPrice;
	private String oAddress;
	private String oRequest;
	private String oStatus;
	private String oDeliveryStatus;


	public String getiRegister() {
		return iRegister;
	}
	public void setiRegister(String iRegister) {
		this.iRegister = iRegister;
	}
	public int getoNum() {
		return oNum;
	}
	public int getoDeliveryFee() {
		return oDeliveryFee;
	}
	public void setoDeliveryFee(int oDeliveryFee) {
		this.oDeliveryFee = oDeliveryFee;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public Date getoDate() {
		return oDate;
	}
	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}
	public int getiNum() {
		return iNum;
	}
	public void setiNum(int iNum) {
		this.iNum = iNum;
	}
	public String getIoColor() {
		return ioColor;
	}
	public void setIoColor(String ioColor) {
		this.ioColor = ioColor;
	}
	public String getIoOption() {
		return ioOption;
	}
	public void setIoOption(String ioOption) {
		this.ioOption = ioOption;
	}
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getiPrice() {
		return iPrice;
	}
	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}
	public int getoCount() {
		return oCount;
	}
	public void setoCount(int oCount) {
		this.oCount = oCount;
	}
	public int getoPrice() {
		return oPrice;
	}
	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}
	public String getoAddress() {
		return oAddress;
	}
	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}
	public String getoRequest() {
		return oRequest;
	}
	public void setoRequest(String oRequest) {
		this.oRequest = oRequest;
	}
	public String getoStatus() {
		return oStatus;
	}
	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}
	public String getoDeliveryStatus() {
		return oDeliveryStatus;
	}
	public void setoDeliveryStatus(String oDeliveryStatus) {
		this.oDeliveryStatus = oDeliveryStatus;
	}
	@Override
	public String toString() {
		return "OrderVO [oNum=" + oNum + ", oDate=" + oDate + ", iNum=" + iNum + ", iRegister=" + iRegister
				+ ", ioColor=" + ioColor + ", ioOption=" + ioOption + ", mNum=" + mNum + ", mId=" + mId + ", iPrice="
				+ iPrice + ", oCount=" + oCount + ", oDeliveryFee=" + oDeliveryFee + ", oPrice=" + oPrice
				+ ", oAddress=" + oAddress + ", oRequest=" + oRequest + ", oStatus=" + oStatus + ", oDeliveryStatus="
				+ oDeliveryStatus + "]";
	}
	
	

}
