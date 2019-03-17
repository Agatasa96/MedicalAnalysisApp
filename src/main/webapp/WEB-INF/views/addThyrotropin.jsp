<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<title>Add thyrotropin test</title>
</head>
<body>
	<h2>Add parameter</h2>
	<form:form method="post" modelAttribute="thyrotropinDto">

		<div class="form-group">
			<label for="exampleInputEmail1">TSH [mU/l]</label>
			<form:input path="TSH" type="number" step="0.1" class="form-control"
				id="exampleInputEmail1" />
			<form:errors cssStyle="color:blue" path="TSH"></form:errors>
		</div>

		<button formaction="/MedicalAnalysis/thyrotropin/add" type="submit"
			class="btn btn-primary btn-lg btn-block">Submit</button>

	</form:form>
	<a href="/MedicalAnalysis/results/add/${patientPesel} ">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>