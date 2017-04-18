<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.Serializable"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
	class User implements Serializable {
		public String username;
		public String password;
		public User(String _username, String _password) {
			username = _username;
			password = _password;
		}
		
		public String toString() {
			return "username: " + username + " password: " + password;
		}
	}

	User user;
	if(session.getAttribute("user") == null) 
	{
		user = new User(request.getParameter("username"), request.getParameter("password"));
		session.setAttribute("user", user);
	}
	else
	{
		user = (User) session.getAttribute("user");
	}
 
%>

<h1>Home Page</h1>

<p><%= user.username %></p>

<a href="house.jsp">go to house</a>


</body>
</html>