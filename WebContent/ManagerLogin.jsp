<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Manager Login</title>
</head>
<body>
	<H1>Please Input Password</H1>
	<form action="Manager" method="get">
	<input type="hidden" name="action" value="managerlogin">
	<tr>
		<th>
			Account
		</th>
		
		<th>
			Password
		</th>
	</tr>
	<br>
			
			
			<select name="selectedId">
				<c:forEach items="${managerInfo}" var="info">
				<option value="${info.managerId}">${info.managerAccount}</option>
			
			</c:forEach>
			</select>
			
		<th>
			<input type = "text" name="managerpassword">
		</th>
	</tr>
	<br>
		<c:if test="${not empty invalidpassword}">
			<font color="red">Oops,IncorrectPassword</font>
		</c:if>
	<br>
		<button type="submit">Login</button>
	</form>
</body>
</html>
