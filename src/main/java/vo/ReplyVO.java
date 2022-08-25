package vo;

import java.util.Date;

public class ReplyVO {
	
	private int b_num;
	private int r_num;
	private String content;
	private String writer;
	private Date regdate;
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "ReplyVO [b_num=" + b_num + ", r_num=" + r_num + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + "]";
	}
	
	

}
