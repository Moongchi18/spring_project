package vo;

public class RecommendVO {

	private String b_num;
	private String m_num;
	private int recommend;
	
	
	public int getCount() {
		return recommend;
	}
	public void setCount(int count) {
		this.recommend = count;
	}
	public String getB_num() {
		return b_num;
	}
	public void setB_num(String b_num) {
		this.b_num = b_num;
	}
	public String getM_num() {
		return m_num;
	}
	public void setM_num(String m_num) {
		this.m_num = m_num;
	}
	@Override
	public String toString() {
		return "RecommendVO [b_num=" + b_num + ", m_num=" + m_num + ", count=" + recommend + "]";
	}
	
	
}
