package vo;

import java.util.Date;

public class MemberVO {
	
	private int m_num;
	private int m_type;
	private String m_id;
	private String m_pw;
	private String m_phone;
	private String m_email;
	private String m_address;
	private Date m_date;
	
	
	
	public MemberVO(int m_num, int m_type, String m_id, String m_pw, String m_phone, String m_email, String m_address,
			Date m_date) {
		super();
		this.m_num = m_num;
		this.m_type = m_type;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_phone = m_phone;
		this.m_email = m_email;
		this.m_address = m_address;
		this.m_date = m_date;
	}
	
	public MemberVO(int m_type, String m_id, String m_pw, String m_phone, String m_email, String m_address,
			Date m_date) {
		super();
		this.m_type = m_type;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_phone = m_phone;
		this.m_email = m_email;
		this.m_address = m_address;
		this.m_date = m_date;
	}


	public MemberVO() {
		
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public int getM_type() {
		return m_type;
	}
	public void setM_type(int m_type) {
		this.m_type = m_type;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_address() {
		return m_address;
	}
	public void setM_address(String m_address) {
		this.m_address = m_address;
	}
	public Date getM_date() {
		return m_date;
	}
	public void setM_date(Date m_date) {
		this.m_date = m_date;
	}
	@Override
	public String toString() {
		return "MemberVO [m_num=" + m_num + ", m_type=" + m_type + ", m_id=" + m_id + ", m_pw=" + m_pw + ", m_phone="
				+ m_phone + ", m_email=" + m_email + ", m_address=" + m_address + ", m_date=" + m_date + "]";
	}
	
}
