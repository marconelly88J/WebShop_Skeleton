<%@page import="model.User"%>
<%@page import="model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	
	// session
	User user_session = (User) request.getSession().getAttribute("user_session");
	ArrayList<Item> cartItems = (ArrayList<Item>)request.getSession().getAttribute("cartItems");
	
	if(user_session != null) {
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<h2><%= user_session.getUser_email() %> Cart</h2>
	
	<table border="1" width="40%">
	
		<tr>
			<th>articles</th>
			<th>item</th>
			<th>price</th>
			<th>quantity</th>
			<th>action</th>
		</tr>
		<% int k=1, p=0; float total_price = 0; if(cartItems != null ) {  for(Item item : cartItems) { %>
		<tr>
			<td><%=k++%></td>
			<td><%= item.getItem_name() %></td>
			<td><%= item.getItem_price() %></td>
			<td><%= item.getItem_quantity() %></td>
			<td><a href="CartController?action=removeFromCart&id=<%=p++%>">Remove from Cart</a></td>
		</tr>
		<% total_price += item.getItem_price() * item.getItem_quantity(); }}%>
	</table> 
	<br>
	
	<% if(total_price > 0 ) { %>
	<table>
		<tr>
			<th>Total Price</th>
		</tr>
		<tr>
			<td><%= total_price %></td>
		</tr>
	</table>
	<% } %>
	<hr>
	
	<br>
	<a href="CartController?action=emptyCart">Empty Cart</a><br><br>
	
	<a href="CartController?action=confirmShopping">Confirm shopping</a><br><br>
	
	<a href="index.jsp">Shop</a> <br><br>
	
	<span style="color: red; font-size: 20px;">${requestScope.confirmShop}</span>
	
</body>
</html>

<% 
	} else { 
		response.sendRedirect("welcome_page.jsp");	
	}

%>

