<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Owner Login</title>
</head>
<body>
	<H1>Please Input Password</H1>
	<form action="owner" method="post">
	<input type="hidden" name="action" value="ownerlogin">
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
				<c:forEach items="${ownerInfo}" var="info">
				<option value="${info.ownerid}">${info.owneraccount}</option>
			
			</c:forEach>
			</select>
			
		<th>
			<input type = "text" name="ownerpassword">
		</th>
	</tr>
	<br>
		<c:if test="${not empty invalidpassword}">
			<font color="red">Oops,IncorrectPassword</font>
		</c:if>
	<br>
		<input type="submit" name="button" value="login">
	</form>
</body>
</html>
