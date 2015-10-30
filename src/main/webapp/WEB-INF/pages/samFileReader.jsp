<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
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
     
</body>
</html>