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

<title>Log in</title>
</head>
<body>
	<h2 id="id">Log in</h2>
	<form:form action="/MedicalAnalysis/doctor/logIn" method="post"
		modelAttribute="doctorDto">

		<div class="form-group">
			<label for="exampleInputEmail1">PESEL</label>
			<form:input path="pesel" type="text" class="form-control"
				id="exampleInputEmail1" placeholder="Enter pesel" />
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label>
			<form:input path="password" type="password" class="form-control"
				id="exampleInputPassword1" placeholder="Password" />
		</div>
		<button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
	</form:form>
	<a href="/MedicalAnalysis/doctor/panel">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>