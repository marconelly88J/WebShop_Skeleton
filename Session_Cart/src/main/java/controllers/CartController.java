package controllers;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import model.Item;


@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public CartController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getParameter("action");

		DAO dao = new DAO();

//		ArrayList<Item> item_list =  dao.selectAllItems();
//		request.getSession().setAttribute("item_list", item_list);
//		response.sendRedirect("index.jsp");

		if(action != null) {

			switch (action) {
			// ADD TO CART
			case "addToCart": {
	            Item item = dao.selectItemById(Integer.parseInt(request.getParameter("id")));
	            if (request.getSession().getAttribute("cartItems") == null) {
	                request.getSession().setAttribute("cartItems", new ArrayList<Item>());
	            }

	            boolean itemExists = false;
	            ArrayList<Item> cartItems = (ArrayList<Item>) request.getSession().getAttribute("cartItems");
	            for (Item cartItem : cartItems) {
	                if (cartItem.getItem_id() == item.getItem_id()) {
	                    cartItem.setItem_quantity(cartItem.getItem_quantity() + 1);
	                    itemExists = true;
	                    break;
	                }
	            }
	            if (!itemExists) {
	                item.setItem_quantity(1);
	                cartItems.add(item);
	            }
	            response.sendRedirect("index.jsp");
	            break;
	        }
			// EMPTY CART
			case "emptyCart": {
				request.getSession().removeAttribute("cartItems");
				response.sendRedirect("index.jsp");
				break;
			}
			// REMOVE FROM CART
			case "removeFromCart": {
				ArrayList<Item> cartItems = (ArrayList<Item>) request.getSession().getAttribute("cartItems");
				for(Item item : cartItems) {
					if(item.getItem_quantity() > 1) 
						item.setItem_quantity(item.getItem_quantity()-1);
					else if(item.getItem_quantity() == 1)
						((ArrayList<Item>)request.getSession().getAttribute("cartItems")).remove(Integer.parseInt(request.getParameter("id")));
						break;
				}
				response.sendRedirect("cart.jsp");
				break;
			}
			// CONFIRM SHOPPING /////////////////////////////////////////////////////////////////////////////////////
			case "confirmShopping": {
				String confirmShop = "";
				ArrayList<Item> cartItems = (ArrayList<Item>) request.getSession().getAttribute("cartItems");
				
				if(cartItems != null) {
					//dao.insertCartItems((Array) cartItems);
					confirmShop += "Thank you for your purchase on our website!";
					request.getSession().removeAttribute("cartItems");
					request.setAttribute("confirmShop", confirmShop);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else {
					confirmShop += "No items in cart to confirm shopping...";
					request.setAttribute("confirmShop", confirmShop);
					request.getRequestDispatcher("cart.jsp").forward(request, response);
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);



	}
	
	/*
	 * 
	 * case "addToCart": {
				Item item = dao.selectItemById(Integer.parseInt(request.getParameter("id")));
				if(request.getSession().getAttribute("cartItems") == null) 
					request.getSession().setAttribute("cartItems", new ArrayList<Item>());
				// quantity
				
				int item_quantity = 1;
				for( Item items : ((ArrayList<Item>)request.getSession().getAttribute("cartItems")) ) {
					for(int i = 0; i<( ((ArrayList<Item>)request.getSession().getAttribute("cartItems")).size() )-1; i++ ) {
						Item x = ((ArrayList<Item>)request.getSession().getAttribute("cartItems")).get(i);
						for (int j = i + 1; j < ((ArrayList<Item>)request.getSession().getAttribute("cartItems")).size(); j++) {
				            Item y = ((ArrayList<Item>)request.getSession().getAttribute("cartItems")).get(j);
				            
				            if (x.getItem_id() == y.getItem_id()) {
								item_quantity++;
								item.setItem_quantity(item_quantity);
				            }else {
				            	item.setItem_quantity(1);
				            	((ArrayList<Item>)request.getSession().getAttribute("cartItems")).add(item);
				            }
						}
					}
				}
				
				response.sendRedirect("index.jsp");
				break;
			}
	 * 
	 */
	

}
