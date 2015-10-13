<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Template EmployeeApp Home Page</title>
</head>
<body>

<h1>Template EmployeeApp Home Page</h1>
<h3>Please select your site</h3>

<c:if test="${pageContext.request.userPrincipal.name == null}">
			<a href="/SpringSecurity/login">User page</a>
			<a href="/SpringSecurity/admin**">Admin page</a>
	</c:if>
	
</body>
</html>