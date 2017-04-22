<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="airbnbModels.*, java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String ownerUsername = request.getParameter("ownerUsername");
	if(ownerUsername == null)
		response.sendRedirect("errorPage.jsp");
	
	ArrayList<House> houseList = House.GetHousesOwnedBy(ownerUsername);
	
	boolean isOwner = false;
	User user = (User) session.getAttribute("user");
	if(user != null && user.username.equals(ownerUsername))
		isOwner = true;
%>

<%if (isOwner) {%>
<h1>Your houses</h1>
<%}
else { %>
<h1><%=ownerUsername%>'s houses</h1>




</body>
</html>