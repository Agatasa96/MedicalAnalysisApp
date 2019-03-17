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
	href="/MedicalAnalysis/resources/style/btnStyle.css" type="text/css">

<title>Main page</title>
</head>
<body>
	<div class="container">
		<h2>Welcome in medical results analysis application</h2>

		<a href="/MedicalAnalysis/patient/register">
			<button type="button" class="btn btn-primary btn-lg btn-block">Add
				patient</button>
		</a> <a href="/MedicalAnalysis/patient/showAllPatients">
			<button type="button" class="btn btn-secondary btn-lg btn-block">Show
				all patients</button>
		</a><a href="/MedicalAnalysis/timetable/add">
			<button type="button" class="btn btn-primary btn-lg btn-block">Add
				timetable</button>
		</a> <a href="/MedicalAnalysis/timetable/show">
			<button type="button" class="btn btn-secondary btn-lg btn-block">Show
				timetable</button>
		</a> <a href="/MedicalAnalysis/">
			<button type="button" class="btn btn-primary btn-lg btn-block">Log
				out</button>
		</a>
	</div>
</body>
</html>