<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Double Check</title>
</head>
<body>
	<h1>Double Check Booking Details</h1>
	<form action="PinLogin" method = "post">
	<input type="hidden" name="action" value="doubleCheck">
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
	<c:forEach var="order" items="${doubleCheckItem}">
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
	</tr>
	</c:forEach>
	</table>
	Do you want to add one extra room?
	<input type="submit" name="button" value="Add Room"/><br>
	<input type="submit" name="button" value="cancel booking"/>
	<input type="submit" name="button" value="finish booking"/>
	</form>
	
</body>
</html>
