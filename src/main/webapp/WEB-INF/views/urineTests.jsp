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

<title>Tests</title>
</head>
<body>
	<h2>Results:</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Correct color</th>
				<th scope="col">Correct clarity</th>
				<th scope="col">Weight</th>
				<th scope="col">pH</th>
				<th>Including protein</th>
				<th>Including glucose</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tests }" var="t">
				<tr>
					<td>${t.correctColor }</td>
					<td>${t.correctClarity }</td>
					<td>${t.weight }</td>
					<td>${t.ph }</td>
					<td>${t.protein }</td>
					<td>${t.glucose }</td>
					<td><a href="/MedicalAnalysis/results/show/${t.id}">
							<button type="button" class="btn btn-primary btn-lg btn-block">Show
								diagnosis</button>
					</a></td>
					<td><a href="/MedicalAnalysis/urine/delete/${t.id}">
							<button type="button" class="btn btn-primary btn-lg btn-block">Delete
								test</button>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/MedicalAnalysis/results/showResultsPanel/${patientPesel} ">
		<button type="button" class="btn btn-primary btn-lg btn-block">Back</button>
	</a>
</body>
</html>