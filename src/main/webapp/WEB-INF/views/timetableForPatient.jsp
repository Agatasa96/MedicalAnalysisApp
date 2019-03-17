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

<title>Timetables</title>
</head>
<body>
	<h2>Timetables</h2>
	<div class="container">
		<a href="/MedicalAnalysis/timetable/showAvailable">
			<button type="button" class="btn btn-primary btn-lg btn-block">Show
				available</button>
		</a><a href="/MedicalAnalysis/patient/showReservations">
			<button type="button" class="btn btn-secondary btn-lg btn-block">Show
				my reservation</button>
		</a>

	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Doctor</th>
				<th scope="col">Date</th>
				<th scope="col">Reservation</th>
				<th scope="col">Confirmed</th>
				<th></th>
				<th></th>
				<th></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${timetables }" var="t">
				<tr>
					<td>${t.doctorDto.name }${t.doctorDto.surname }</td>
					<td>${t.date }</td>
					<td>${t.reservation }</td>
					<td>${t.confirmReservation }</td>
					<td><a href="/MedicalAnalysis/patient/addReservation/${t.id }">
							<button type="button" class="btn btn-primary btn-lg btn-block">Add
								reservation</button>
					</a></td>
					<td><a href="/MedicalAnalysis/timetable/confirm/${t.id }">
							<button type="button" class="btn btn-primary btn-lg btn-block">Confirm
								reservation</button>
					</a></td>
					<td><a href="/MedicalAnalysis/timetable/cancel/${t.id }">
							<button type="button" class="btn btn-primary btn-lg btn-block">Cancel
								reservation</button>
					</a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/MedicalAnalysis/patient/mainPage ">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>