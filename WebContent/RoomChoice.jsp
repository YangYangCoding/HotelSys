<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Available Rooms</title>
</head>
<body>
	<h1>Available Rooms For This Type</h1>
	<form action="Manager" method="post">
	<input type="hidden" name="action" value = "roomsavailable">
	
	<table border = "1">
	<tr>
		<th>
			Room ID
		</th>
		<th>
			Room Type
		</th>
		<th>
			Number of Beds
		</th>
		<th>
			Price
		</th>
	</tr>
	<c:forEach var="occ" items="${roomschoice}">
	<tr>
		<td>
   			${occ.roomId}
   		</td>
   		<td>
   			${occ.type}
   		</td>
   		<td>
   			${occ.numBeds}
   		</td>
   		<td>
   			${occ.price}
   		</td>
   		<td>
   			<input type = "radio" name = "assign" value = "${occ.roomId}" > 
   		</td>

	</tr>
	</c:forEach>
	
	</table>
	<input type="submit" name="button" value="Confirm">
	</form>
	
	

</body>
</html>
