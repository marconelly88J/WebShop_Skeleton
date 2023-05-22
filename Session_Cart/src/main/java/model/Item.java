package model;

public class Item {

	private int item_id;
	private String item_name;
	private float item_price;
	private int item_quantity;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(int item_id, String item_name, float item_price, int item_quantity) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_quantity = item_quantity;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public float getItem_price() {
		return item_price;
	}

	public void setItem_price(float item_price) {
		this.item_price = item_price;
	}
	
	public int getItem_quantity() {
		return item_quantity;
	}
	
	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name + ", item_price=" + item_price + ", item_quantity="+item_quantity+"]";
	}
	
	
	
}
