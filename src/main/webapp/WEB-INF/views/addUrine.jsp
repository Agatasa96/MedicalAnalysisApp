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

<title>Add urine test</title>
</head>
<body>
	<h2>Add parameter</h2>
	<form:form method="post" modelAttribute="urineDto">
		<div class="form-group">
			<label for="exampleInputEmail1">Correct color</label>
			<form:select path="correctColor" type="text" class="form-control"
				id="exampleInputEmail1">
				<form:option value="true"> Yes</form:option>
				<form:option value="false">No</form:option>
			</form:select>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Correct clarity</label>
			<form:select path="correctClarity" type="text" class="form-control"
				id="exampleInputEmail1">
				<form:option value="true"> Yes</form:option>
				<form:option value="false">No</form:option>
			</form:select>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Weight</label>
			<form:input path="weight" type="number" step="0.001"
				class="form-control" id="exampleInputEmail1" />
			<form:errors cssStyle="color:blue" path="weight"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">pH</label>
			<form:input path="ph" type="number" step="0.1" class="form-control"
				id="exampleInputEmail1" />
			<form:errors cssStyle="color:blue" path="ph"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Including glucose</label>
			<form:select path="glucose" type="text" class="form-control"
				id="exampleInputEmail1">
				<form:option value="true"> Yes</form:option>
				<form:option value="false">No</form:option>
			</form:select>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Including protein</label>
			<form:select path="protein" type="text" class="form-control"
				id="exampleInputEmail1">
				<form:option value="true"> Yes</form:option>
				<form:option value="false">No</form:option>
			</form:select>
		</div>

		<button formaction="/MedicalAnalysis/urine/add" type="submit"
			class="btn btn-primary btn-lg btn-block">Submit</button>

	</form:form>
	<a href="/MedicalAnalysis/results/add/${patientPesel} ">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>