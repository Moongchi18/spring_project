package vo;

public class ItemTypeVO {
	private int iType;
	private String iTypeString;
	public int getiType() {
		return iType;
	}
	public void setiType(int iType) {
		this.iType = iType;
	}
	public String getiTypeString() {
		return iTypeString;
	}
	public void setiTypeString(String iTypeString) {
		this.iTypeString = iTypeString;
	}
	@Override
	public String toString() {
		return "ItemTypeVO [iType=" + iType + ", iTypeString=" + iTypeString + "]";
	}
	
	
}
