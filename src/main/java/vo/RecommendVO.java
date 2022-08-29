package vo;

public class RecommendVO {

	private int b_num;
	private int m_num;
	private int recommend;
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	@Override
	public String toString() {
		return "RecommendVO [b_num=" + b_num + ", m_num=" + m_num + ", recommend=" + recommend + "]";
	}
	
	
	
	
}
