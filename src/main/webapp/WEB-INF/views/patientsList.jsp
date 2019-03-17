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

<title>Patients</title>
</head>
<body>
	<div class="container">
		<h2>All patients list</h2>
		<a href="/MedicalAnalysis/doctor/showMyPatients">
			<button type="button" class="btn btn-primary btn-lg btn-block">Show
				only my patients</button>
		</a>
	</div>
	<h3>Search by pesel</h3>
	<div class="input-group mb-3">
		<form:form action="/MedicalAnalysis/patient/byPesel" method="get">
			<input type="text" class="form-control" placeholder="Pesel"
				aria-describedby="button-addon2" name="pesel">
			<div class="input-group-append">

				<button class="btn btn-secondary btn-block" type="submit"
					id="button-addon2">Search</button>
			</div>
		</form:form>
	</div>

	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Pesel</th>
				<th scope="col">Date</th>
				<th scope="col">Doctor</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${patients }" var="p">
				<tr>
					<td>${p.pesel }</td>
					<td>${p.date }</td>
					<td>${p.doctorDto.surname }</td>
					<td><a href="/MedicalAnalysis/patient/details/${p.pesel }">
							<button type="button" class="btn btn-primary btn-lg btn-block">Details</button>
					</a></td>
					<td><a href="/MedicalAnalysis/doctor/delete/${p.pesel }">
							<button type="button" class="btn btn-primary btn-lg btn-block">Delete</button>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/MedicalAnalysis/doctor/mainPage">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>