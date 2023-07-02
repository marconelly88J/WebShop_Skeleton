package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import model.Item;
import model.User;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Login() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		
		if(action.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("welcome_page.jsp");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao = new DAO();
		String action = request.getParameter("action");
		String userEmail = request.getParameter("email");
		
		if(action != null) {
			// validacija email-a
			if(checkEmail(userEmail)) {
				// da li postoji user_email u bazi
				if(!userExists(userEmail)) {
				
				dao.insertUser(userEmail);
				
				ArrayList<Item> item_list =  dao.selectAllItems();
				HttpSession items_render_session = request.getSession();
				items_render_session.setAttribute("item_list", item_list);
				
				User user_email = new User(userEmail);
				HttpSession user_session = request.getSession();
				user_session.setAttribute("user_session", user_email);
				user_session.setMaxInactiveInterval(600);
				
				response.sendRedirect("index.jsp");
				}
			}else {
				String msg = "Must enter valid email!"; 
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("welcome_page.jsp").forward(request, response);
			}
		}
		
	}
	private boolean userExists(String userEmail) {
		
		return false;
	}


	///////////////////////////////////////////////////
	public boolean checkEmail(String email) {
		
		if( email == null || email.isEmpty() || !email.contains("@") ) 
			return false;
		else 
			return true;
			
	}
	

}
