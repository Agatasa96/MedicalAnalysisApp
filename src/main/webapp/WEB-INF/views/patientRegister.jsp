<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="/MedicalAnalysis/resources/style/formStyle.css" type="text/css">

<title>Patient register</title>
</head>
<body>
	<h2>Register</h2>
	<form:form action="/MedicalAnalysis/patient/register" method="post"
		modelAttribute="patientDto">
		<div class="form-group">
			<label for="exampleInputEmail1">Name</label>
			<form:input path="name" type="text" class="form-control"
				id="exampleInputEmail1" placeholder="Enter name" />
			<form:errors path="name" cssStyle="color:blue"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Surname</label>
			<form:input path="surname" type="text" class="form-control"
				id="exampleInputEmail1" placeholder="Enter surname" />
			<form:errors path="surname" cssStyle="color:blue"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">PESEL</label>
			<form:input path="pesel" type="text" class="form-control"
				id="exampleInputEmail1" placeholder="Enter pesel" />
			<form:errors path="pesel" cssStyle="color:blue"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Password</label>
			<form:input path="password" type="password" class="form-control"
				id="exampleInputEmail1" placeholder="Enter password" />
		</div>
		<button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
	</form:form>
	<a href="/MedicalAnalysis/doctor/mainPage">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>