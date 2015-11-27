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
	
	<section class="item-list">
	<c:if test="${error != null}">
	<div class="error">${error}</div>
	</c:if>
	<h3>${docname}</h3>
		<pre>${document}</pre>
	</section>
</body>
</html>