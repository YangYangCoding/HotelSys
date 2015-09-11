<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Welcome</title>
</head>
<body>
	<H1>Please Search Hotels Below</H1>
	<form action="controller">
	<input type="hidden" name="action" value="searchhotel">
	<br>
		<c:if test="${not empty invalidinput}">
			<font color ="red">Oops the ${input} is not valid</font>
		
		</c:if>
	<br>
	Check in date(Please follow this format:yyyy-mm-dd)
	<input type="text" name="checkin"/> <br>
	Check out date(Please follow this format:yyyy-mm-dd)
	<input type="text" name="checkout"/> <br>
	
	City you would like to go
	<select name="cityoption">
	<option value="sydney">Sydney</option>
	<option value="brisbane">Brisbane</option>
	<option value="melbourne">Melbourne</option>
	<option value="adelaide">Adelaide</option>
	<option value="hobart">Hobart</option>	
	</select> <br>
	
	number of rooms
	<input type="text" name="nbroom"/> <br>
	maximum price per room per night you would like to pay
	<input type="text" name="price"/><br>
	
	<input type='submit' name='search' value='Search'/>
	</form>
</body>
</html>
