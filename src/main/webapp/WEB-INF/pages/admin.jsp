<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet" type="text/css"  href="<c:url value="/resources/css/main.css" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>

<style>
#header {
	background-color: teal;
	border-bottom: 2px solid yellow;
	margin-bottom: 20px;
}

#data {
	margin: 50px;
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
	padding-bottom: 10px;
	background-color: navy;
	color: white;
	font-weight: bold;
	background-color: navy;
	color: white;
	font-weight: bold;
	font-size: large;
}

#selectedIdInput {
	visibility: hidden;
}

.selectUser {
	background-color: fuchsia;
}

tbody {
	display: block;
	height: 200px;
	overflow: auto;
}

tr {
	border: 1px solid white;
	padding: 10px;
	background-color: gray;
	color: white;
	font-weight: bold;
	font-size: medium;
}

#leftContainer {
	float: left;
	width: 50%;
}

#rightContainer {
	float: right;
	width: 50%;
}
</style>
</head>

<html>
<body>

	<div id="header">
		<h1>ADMIN PAGE</h1>
		<h1>Title : ${title}</h1>

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
				Welcome : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
	</div>

	<span id="leftContainer">
		<h3>Search Employees</h3> 
		User Name: 
		<br> 
		<input id="nameInput" type="text" name="name"> 
		<br> Job Title: 
		<br> 
		<input id="jobTitleInput" type="text" name="jobtitle"> 
		<br>
		Location: 
		<br> 
		<input id="locationInput" type="text" name="location"> 
		<br> 
		Email: 
		<br> 
		<input id="emailInput" type="text" name="email"> 
		<br> 
		Mobile Number: 
		<br> 
		<input id="phoneInput" type="text" name="phonenumber"> <br>

		<button type="button" id="processButton">Search</button> <br>

		<h3>To add a user, please:</h3>
		<p>
			1.- include a user role : <b>ROLE_USER</b>, or <b>ROLE_ADMIN</b>
		</p>
		<p>2.- include a password</p>
		 User Role: 
		 <br> 
		 <input id="roleInput" type="text" name="userrole"> 
		 <br> <br>
		Password: 
		<br> 
		<input id="passwordInput" type="password" name="password"> 
		<br>
		<button type="button" id="addButton">Add Employee</button>

		<div id="loadingDiv"></div>

	</span>

	<span id="rightContainer">

		<div>
			<table id="employeesTable">

				<tbody>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Job Title</th>
						<th>Location</th>
						<th>Email</th>
						<th>Mobile#</th>
						<th>User Select</th>
					</tr>
				</tbody>

			</table>
	</span>

	<h1>Selected Employee</h1>
	<div id="selectEmployee">
		<input id="selectedIdInput" type="text" name="id"> <br>
		User Name: 
		<br>
		<input id="selectedNameInput" type="text" name="name">
		<br> 
		Job Title:
		<br> 
		<input id="selectedJobTitleInput" type="text" name="jobtitle"> 
		<br> 
		Location: 
		<br>
		<input id="selectedLocationInput" type="text" name="location">
		 <br>
		Email: 
		<br>
		<input id="selectedEmailInput" type="text" name="email">	
		<br> 
		Mobile Number:
		<br>
		<input id="selectedPhoneInput" type="text" name="phonenumber">
	</div>
	<br>
	<button type="button" id="updateButton">Update</button>
	<button type="button" id="deleteButton">Delete</button>
	</div>






</body>
</html>


<script type="text/javascript">
	$(document).ready(function() {

	});

	var $loading = $('#loadingDiv').hide();
	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});

	function selectEmployee(id, name, jobTitle, location, email, phobeNumber) {
		alert(name + "  " + jobtitle + "  " + location + "  " + email + "  "
				+ phonenumber);
	};

	$("#addButton").click(
			function() {
				var userName = $("#nameInput").val();
				var jobTitle = $("#jobTitleInput").val();
				var location = $("#locationInput").val();
				var email = $("#emailInput").val();
				var phoneNumber = $("#phoneInput").val();
				var userRole = $("#roleInput").val();
				var password = $("#passwordInput").val();

				$.ajax({
					url : '${pageContext.request.contextPath}/addEmployee/'
							+ userName + '/' + jobTitle + '/' + location + '/'
							+ email + '/' + phoneNumber + '/' + userRole + '/' + password,
					type : "GET",
					success : function(result) {
						//Do something here
					}
				});
			});

	$("#deleteButton").click(
			function() {
				var id = $("#selectedIdInput").val();
				var userName = $("#selectedNameInput").val();

				$.ajax({
					url : '${pageContext.request.contextPath}/deleteEmployee/'
							+ id + '/' + userName,
					type : "GET",
					success : function(result) {
						//Do something here
					}
				});
			});

	$("#updateButton").click(
			function() {
				var id = $("#selectedIdInput").val();
				var userName = $("#selectedNameInput").val();
				var jobTitle = $("#selectedJobTitleInput").val();
				var location = $("#selectedLocationInput").val();
				var email = $("#selectedEmailInput").val();
				var phoneNumber = $("#selectedPhoneInput").val();

				$.ajax({
					url : '${pageContext.request.contextPath}/updateEmployee/'
							+ id + '/' + userName + '/' + jobTitle + '/'
							+ location + '/' + email + '/' + phoneNumber,
					type : "GET",
					success : function(result) {
						alert("Update sucessfull");
					}
				});
			});

	$("#processButton")
			.click(
					function() {
						$(".trData").empty();
						var button = $('<button id="selectUserButton" class="selectUser">Select</button>');

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
																				htmlTable += '<tr class="trData"><td>'
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
																						+ '</td><td id="buttonCell'+id+'">'

																						+ '</td></tr>';

																				var button = $('<button id="selectUserButton'+id+'" class="selectUser">Select</button>');

																				$(
																						'#employeesTable')
																						.append(
																								htmlTable);
																				button
																						.appendTo('#buttonCell'
																								+ id);

																				$(
																						'#buttonCell'
																								+ id)
																						.on(
																								'click',
																								'#selectUserButton'
																										+ id,
																								function() {
																									//alert(name + "  " + jobtitle + "  " + location + "  " + email + "  " + phonenumber);

																									$(
																											"#selectedIdInput")
																											.val(
																													id);
																									$(
																											"#selectedNameInput")
																											.val(
																													name);
																									$(
																											"#selectedJobTitleInput")
																											.val(
																													jobtitle);
																									$(
																											"#selectedLocationInput")
																											.val(
																													location);
																									$(
																											"#selectedEmailInput")
																											.val(
																													email);
																									$(
																											"#selectedPhoneInput")
																											.val(
																													phonenumber);

																								});
																			});
														});
									}
								});
					});
</script>