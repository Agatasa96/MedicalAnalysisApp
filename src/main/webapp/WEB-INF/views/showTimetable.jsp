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

<title>Timetables</title>
</head>
<body>

	<h2>Search by date:</h2>
	<div class="input-group mb-3">
		<form:form action="/MedicalAnalysis/timetable/byDate" method="get">
			<h3>From</h3>
			<input type="text" class="form-control"
				placeholder="yyyy-mm-dd hh:mm" aria-describedby="button-addon2"
				name="startDate">
			<h3>To:</h3>
			<input type="text" class="form-control"
				placeholder="yyyy-mm-dd hh:mm" aria-describedby="button-addon2"
				name="endDate">
			<div class="input-group-append">
				<button class="btn btn-secondary btn-block" type="submit"
					id="button-addon2">Search</button>
			</div>
		</form:form>
	</div>

	<h2>Timetables</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Date</th>
				<th scope="col">Reservation</th>
				<th scope="col">Confirmed</th>
				<th scope="col">Patient</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${timetables }" var="t">
				<tr>
					<td>${t.date }</td>
					<td>${t.reservation }</td>
					<td>${t.confirmReservation }</td>
					<td>${t.patientDto.pesel }</td>
					<td><a href="/MedicalAnalysis/timetable/edit/${t.id }">
							<button type="button" class="btn btn-primary btn-lg btn-block">Edit</button>
					</a></td>
					<td><a href="/MedicalAnalysis/timetable/delete/${t.id }">
							<button type="button" class="btn btn-primary btn-lg btn-block">Delete</button>
					</a></td>
					<td><a
						href="/MedicalAnalysis/timetable/addReservationByDoctor/${t.id }">
							<button type="button" class="btn btn-primary btn-lg btn-block">Add
								reservation</button>
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