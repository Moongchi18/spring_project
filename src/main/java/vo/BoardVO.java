package vo;

import java.util.Date;

public class BoardVO {
	
	private int bNum;
	private int bType;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private Date bWriteDate;
	private int bReadCount;
	
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public int getbType() {
		return bType;
	}
	public void setbType(int bType) {
		this.bType = bType;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public Date getbWriteDate() {
		return bWriteDate;
	}
	public void setbWriteDate(Date bWriteDate) {
		this.bWriteDate = bWriteDate;
	}
	public int getbReadCount() {
		return bReadCount;
	}
	public void setbReadCount(int bReadCount) {
		this.bReadCount = bReadCount;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bNum=" + bNum + ", bType=" + bType + ", bTitle=" + bTitle + ", bContent=" + bContent
				+ ", bWriter=" + bWriter + ", bWriteDate=" + bWriteDate + ", bReadCount=" + bReadCount + "]";
	}
	
	
	
}
