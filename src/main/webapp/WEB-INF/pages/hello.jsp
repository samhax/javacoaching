<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>


<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />"
	type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>

<style>
#data {
	overflow: scroll;
	height: 200px;
	margin: 50px;
	width: auto;
}

#employeeTable {
	border: 1px solid blue;
	border-collapse: collapse;
}

th {
	border: 1px solid blue;
	padding-left: 15px;
	padding-right: 15px;
	padding-top: 10px;
	padding-bottom: 10px; background-color : navy;
	color: white;
	font-weight: bold;
	background-color: navy; color : white; font-weight : bold;
	font-size: large;
}

tr {
	border: 1px solid white;
	padding: 10px;
	background-color: gray;
	color: white;
	font-weight: bold;
	font-size: medium;
}
</style>
</head>


<html>
<body>
	<h1>USER PAGE</h1>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>

	</sec:authorize>

	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<a href="/SpringSecurity/login">Login</a>
		<a href="/SpringSecurity/admin**">Login Admin</a>
	</c:if>

	<p>Search</p>
	User Name:
	<br>
	<input id="nameInput" type="text" name="name">
	<br> Job Title:
	<br>
	<input id="jobTitleInput" type="text" name="jobtitle">
	<br> Location:
	<br>
	<input id="locationInput" type="text" name="location">
	<br> Email:
	<br>
	<input id="emailInput" type="text" name="email">
	<br> Mobile Number:
	<br>
	<input id="phoneInput" type="text" name="phonenumber">
	<br>

	<button type="button" id="processButton">Process</button>

	<div id="loadingDiv"></div>

	<div id="data">
		<table id="employeesTable">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Job Title</th>
				<th>Location</th>
				<th>Email</th>
				<th>Mobile#</th>
			</tr>
		</table>

	</div>


</body>
</html>



<script type="text/javascript">
	
	$( document ).ready(function() {
		
	
	});
	
	var $loading = $('#loadingDiv').hide();
	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	
	
	$("#processButton")
			.click(
					function() {
						var userName = $("#nameInput").val();
						var jobTitle = $("#jobTitleInput").val();
						var location = $("#locationInput").val();
						var email = $("#emailInput").val();
						var phoneNumber = $("#phoneInput").val();

						var userNameEmpty = false;
						var jobTitleEmpty = false;
						var locationEmpty = false;
						var emailEmpty = false;
						var phoneNumberEmpty = false;

						if (userName == null || userName == "") {
							userNameEmpty = true;
							userName = "na";
						}

						if (jobTitle == null || jobTitle == "") {
							jobTitleEmpty = true;
							jobTitle = "na";
						}

						if (location == null || location == "") {
							locationEmpty = true;
							location = "na";
						}

						if (email == null || email == "") {
							emailEmpty = true;
							email = "na";
						}

						if (phoneNumber == null || phoneNumber == "") {
							phoneNumberEmpty = true;
							phoneNumber = "na";
						}

						$
								.ajax({
									url : '${pageContext.request.contextPath}/getEmployees/'
											+ userName
											+ '/'
											+ jobTitle
											+ '/'
											+ location
											+ '/'
											+ email
											+ '/'
											+ phoneNumber,
									type : "GET",
									dataType : "xml",
									success : function(xml) {
										$(xml)
												.find('employees')
												.each(
														function() {
															$(this)
																	.find(
																			"employee")
																	.each(
																			function() {
																				//$("employeesTable").find("tr:gt(0)").remove();
																				$(
																						"#employeesTable > tr")
																						.remove();

																				var htmlTable = '';

																				var id = '';
																				var name = '';
																				var jobtitle = '';
																				var location = '';
																				var email = '';
																				var phonenumber = '';

																				//  htmlTable += '<tr><td>' + item.rank + '</td><td>' + item.content + '</td><td>' + item.UID + '</td></tr>';

																				$(
																						this)
																						.find(
																								"id")
																						.each(
																								function() {
																									id = $(
																											this)
																											.text();
																									// alert(name);
																								});
																				$(
																						this)
																						.find(
																								"name")
																						.each(
																								function() {
																									name = $(
																											this)
																											.text();
																									// alert(name);
																								});
																				$(
																						this)
																						.find(
																								"jobTitle")
																						.each(
																								function() {
																									jobtitle = $(
																											this)
																											.text();

																								});
																				$(
																						this)
																						.find(
																								"location")
																						.each(
																								function() {
																									location = $(
																											this)
																											.text();

																								});
																				$(
																						this)
																						.find(
																								"email")
																						.each(
																								function() {
																									email = $(
																											this)
																											.text();

																								});
																				$(
																						this)
																						.find(
																								"phoneNumber")
																						.each(
																								function() {
																									phonenumber = $(
																											this)
																											.text();

																								});
																				//alert(name + "  " + jobtitle + "  " + location + "  " + email + "  " + phonenumber);	
																				htmlTable += '<tr><td>'
																						+ id
																						+ '</td><td>'
																						+ name
																						+ '</td><td>'
																						+ jobtitle
																						+ '</td><td>'
																						+ location
																						+ '</td><td>'
																						+ email
																						+ '</td><td>'
																						+ phonenumber
																						+ '</td></tr>';
																				$(
																						'#employeesTable')
																						.append(
																								htmlTable);
																			});
														});
									}
								});
					});
</script>