<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Display Info</title>
</head>
<body>
	<h1>Occupied Room</h1>
	<form action="Manager" method="get">
	<input type="hidden" name="action" value = "displayToManager">
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
	<c:forEach var="occ" items="${occupied}">
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
   			<input type = "radio" name = "checkout" value = "${occ.roomId}" > 
   		</td>

	</tr>
	</c:forEach>
	</table>
	<input type="submit" name="button" value="Checkout">
	<br>
	
	
	
	<h1>All the Booking</h1>
	<table border = "1">
	<tr>
		<th>
			BookingID
		</th>
		<th>
			CustomerID
		</th>
		<th>
			Type
		</th>
		<th>
			Price
		</th>
		<th>
			CityName
		</th>
		<th>
			Check In
		</th>
		<th>
			Check Out
		</th>
	</tr>
	<c:forEach var="order" items="${bookedOrder}">
	<tr>
		<td>
			${order.bookingID}
		</td>
		<td>
			${order.customerID}
		</td>
		<td>
			${order.type}
		</td>
		<td>
			${order.price}
		</td>
		<td>
			${order.cityname}
		</td>
		<td>
			${order.check_IN}
		</td>
		<td>
			${order.check_out}
		</td>
		<td>
   			<input type = "radio" name = "assign" value = "${order.bookingID}" > 
   		</td>
	</tr>
	</c:forEach>
	</table>
	<input type="submit" name="button" value="Assign">
	
	
	</form>
</body>
</html>
