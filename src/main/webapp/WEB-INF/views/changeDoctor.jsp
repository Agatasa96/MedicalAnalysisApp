<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	href="/MedicalAnalysis/resources/style/tableStyle.css" type="text/css">
<link rel="stylesheet"
	href="/MedicalAnalysis/resources/style/btnStyle.css" type="text/css">

<title>Change doctor</title>
</head>
<body>
	<h2>Your doctor</h2>

	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Surname</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td>${yourDoctor.name }</td>
				<td>${yourDoctor.surname }</td>
		</tbody>
	</table>

	<form action="/MedicalAnalysis/patient/changeDoctor" method="post">

		<h2>Change doctor</h2>

		<select name="id" class="custom-select">
			<c:forEach items="${doctorList }" var="d">
				<option value="${d.id }">${d.name } ${d.surname }</option>
			</c:forEach>
		</select>
		<button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
	</form>
	<a href="/MedicalAnalysis/patient/mainPage ">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>