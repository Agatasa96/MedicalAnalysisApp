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
	href="/MedicalAnalysis/resources/style/btnStyle.css" type="text/css">

<title>Choose result</title>
</head>
<body>
	<h2>Choose result:</h2>
	<div class="container">
		<a href="/MedicalAnalysis/urine/showAll">
			<button type="button" class="btn btn-primary btn-lg btn-block">Urine
				test</button>
		</a> <a href="/MedicalAnalysis/morphology/showAll">
			<button type="button" class="btn btn-secondary btn-lg btn-block">Morphology
				test</button>
		</a> <a href="/MedicalAnalysis/bloodGlucose/showAll">
			<button type="button" class="btn btn-primary btn-lg btn-block">Blood
				glucose test</button>
		</a><a href="/MedicalAnalysis/thyrotropin/showAll">
			<button type="button" class="btn btn-secondary btn-lg btn-block">Thyrotropin
				test</button>
		</a>
	</div>
</body>
</html>