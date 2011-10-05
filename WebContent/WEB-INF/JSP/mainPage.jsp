<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dom.User"%>
<% User user = (User)request.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../CSS/layout.css" type="text/css"
	media="screen" />

<title>You are now logged in</title>
</head>
<body>
	<h2>
		Welcome
		<%=user.getFirstName() + " " + user.getLastName()%></h2>

		<% if (user.getGroupName() != null && !(user.getGroupName().equals(""))) {%>
	      <h3>You are in the following group:	<%=user.getGroupName()%></h3>
	
	      <!-- If user is admin, give them the option to upload a csv file -->
		
		<% if (user.isAdmin()) {%>
		<form action="Grouper?command=Upload" method="post">
			<p>Upload a csv file containing userName, password, firstName, lastName:</p>
			<p>
				Please specify a file:<br>
				<input type="file" name="datafile" size="40">
			</p>
			<div>
				<input type="submit" value="Upload">
			</div>
		</form>
		<%} %>
	
	<%} %>

	<a href="">Browse Groups</a>
	<br />

	<% if (user.getGroupName() == "") { %>
	<a href="">Create a Group</a>
	<br />
	<a href="">Browse Invitations</a>
	<br />
	<% } else { %>
	<a href="">Edit Group</a>
	<br />
	<a href="">Remove Group</a>
	<br />
	<a href="">Invite a New Member</a>
	<br />
	<% } %>

	
	<br />
	<span id=sidebar> 
	
	some text goes here
	
	</span>


	<span id=mainSection"> 
		
	</span>

</body>
</html>