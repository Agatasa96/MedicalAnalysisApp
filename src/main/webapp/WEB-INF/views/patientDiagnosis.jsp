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

<title>Diagnosis:</title>
</head>
<body>
	<h2>All results:</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Test</th>
				<th scope="col">Health status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${results }" var="r">
				<tr>
					<td>${r.testName }</td>
					<td>${r.healthStatus }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/MedicalAnalysis/results/showResultsPanel/${patientPesel } ">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>