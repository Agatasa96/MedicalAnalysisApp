<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>Patient details</title>
</head>
<body>
	<h2>Patient details</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Pesel</th>
				<th scope="col">Name</th>
				<th scope="col">Surname</th>
				<th scope="col">Date</th>
				<th scope="col">Doctor</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${patient.pesel }</td>
				<td>${patient.name }</td>
				<td>${patient.surname }</td>
				<td>${patient.date }</td>
				<td>${patient.doctorDto.surname }</td>
			</tr>
		</tbody>
	</table>

	<div class="container">
		<a href="/MedicalAnalysis/results/showResultsPanel/${patient.pesel}">
			<button type="button" class="btn btn-primary btn-lg btn-block">Show
				medical results</button>
		</a> <a href="/MedicalAnalysis/results/add/${patient.pesel}">
			<button type="button" class="btn btn-secondary btn-lg btn-block">Add
				medical results</button>
		</a> <a href="/MedicalAnalysis/doctor/showMyPatients">
			<button type="button" class="btn btn-primary btn-lg btn-block">Back</button>
		</a>
	</div>
</body>
</html>