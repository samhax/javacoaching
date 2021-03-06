<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
<style type="text/css">
.error {
    background: #D13C3C;
    color: white;
    padding: 15px;
    display: inline-block;
    border-radius: 5px;
}

.success{
    background: #60BB35;
    color: white;
    padding: 15px;
    display: inline-block;
    border-radius: 5px;
}
</style>
</head>
<body>
 
    <form method="POST" action="supload" enctype="multipart/form-data">
	    <div>File to upload: <input type="file" name="file"></div>
	    <div>Name: <input type="text" name="name"></div>
		<div><input type="submit" value="Upload"></div>
		<input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
    </form>
     
     
    <c:choose>
 	<c:when test="${success != null}">
 		<div class="success">${success}</div>
 	</c:when>
 	<c:when test="${error != null}">
		<div class="error">${error}</div>
 	</c:when>
 	</c:choose>
	
	<section class="item-list">
	<h3>Documents currently on server</h3>
	<ul>
	<c:forEach var="item" items="${serverItemList}">
		<li><a target="_blank" href="/SpringSecurity/sview/${item}" >${item}</a></li>
	</c:forEach>
	</ul>
	</section>
</body>
</html>