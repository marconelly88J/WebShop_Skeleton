package dto;

import java.util.ArrayList;

import model.Item;

public class CartDTO {

	private int cart_id;
	private ArrayList<Item> cart_items;
	
	public CartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartDTO(int cart_id, ArrayList<Item> cart_items) {
		super();
		this.cart_id = cart_id;
		this.cart_items = cart_items;
	}
	public CartDTO(ArrayList<Item> cart_items) {
		super();
		this.cart_items = cart_items;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public ArrayList<Item> getCart_items() {
		return cart_items;
	}
	public void setCart_items(ArrayList<Item> cart_items) {
		this.cart_items = cart_items;
	}
	@Override
	public String toString() {
		return "CartDTO [cart_id=" + cart_id + ", cart_items=" + cart_items + "]";
	}
	
}
