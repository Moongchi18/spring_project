package vo;
import vo.ItemVO;
import vo.CartVO;

public class JoinCartAndItemVO {
	
	private ItemVO item;
	private CartVO cart;
	
	public ItemVO getItem() {
		return item;
	}
	public void setItem(ItemVO item) {
		this.item = item;
	}
	public CartVO getCart() {
		return cart;
	}
	public void setCart(CartVO cart) {
		this.cart = cart;
	}
	
	
	
	public JoinCartAndItemVO(ItemVO item, CartVO cart) {
		super();
		this.item = item;
		this.cart = cart;
	}
	@Override
	public String toString() {
		return "JoinCartAndItemVO [item=" + item + ", cart=" + cart + "]";
	}
	
	

}
