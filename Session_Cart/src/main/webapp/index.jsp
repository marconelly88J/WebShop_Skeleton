<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.User"%>
<%@page import="model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	User user_session = (User) request.getSession().getAttribute("user_session");				 // 'Login'
	ArrayList<Item> item_list = (ArrayList<Item>)request.getSession().getAttribute("item_list"); // 'Login'
	ArrayList<Item> cartItems = (ArrayList<Item>)request.getSession().getAttribute("cartItems"); // 'CartController'
	
	if(user_session != null) {
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Shop</h1>
	<br>

	<h2>List of items</h2>

	<table border="1" width="40%">

		<tr>
			<th>item id</th>
			<th>item</th>
			<th>price</th>
			<th>action</th>
		</tr>
		<% if(item_list != null ) { for(Item item : item_list) { %>
		<tr>
			<td><%= item.getItem_id() %></td>
			<td><%= item.getItem_name() %></td>
			<td><%= item.getItem_price() %></td>
			<td><a href="CartController?action=addToCart&id=<%= item.getItem_id()%>">Add to Cart</a></td>
		</tr>
		<% } } %>

	</table>

	<br>

	<h2>Shoping Cart preview</h2>

	<table border="1" width="20%">

		<tr>
			<th>item id</th>
			<th>item</th>
			<th>price</th>
			<th>quantity</th>
		</tr>
		<% 
			
			if(cartItems != null){
				for(Item item : cartItems){
		%>
		<tr>
			<td><%= item.getItem_id()%></td>
			<td><%= item.getItem_name() %></td>
			<td><%= item.getItem_price() %></td>
			<!---------------  item_quantity  -------------------> 
			<td><%= item.getItem_quantity() %></td>
		</tr>
		<% } }%>
	</table>
	<br>
	<br>

	<a href="cart.jsp">My Cart</a>
	<br>
	<br>
	<a href="Login?action=logout">Logout</a> <br>
	
	<span style="color: green; font-size: 20px;">${requestScope.confirmShop}</span>



</body>
</html>
<% 
	} else { 
		response.sendRedirect("welcome_page.jsp");	
	}

%>

