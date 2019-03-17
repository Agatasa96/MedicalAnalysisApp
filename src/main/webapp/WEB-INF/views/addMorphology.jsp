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

<title>Add morphology test</title>
</head>
<body>
	<h2>Add parameter</h2>
	<form:form method="post" modelAttribute="morphologyDto">


		<div class="form-group">
			<label for="exampleInputEmail1">WBC [tys/ul]</label>
			<form:input path="WBC" type="number" step="0.1" class="form-control"
				id="exampleInputEmail1" />
			<form:errors cssStyle="color:blue" path="WBC"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">LYMPH [tys/ul]</label>
			<form:input path="LYMPH" type="number" step="0.1"
				class="form-control" id="exampleInputEmail1" />
			<form:errors cssStyle="color:blue" path="LYMPH"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">MONO [x109/l]</label>
			<form:input path="MONO" type="number" step="0.1" class="form-control"
				id="exampleInputEmail1" />
			<form:errors cssStyle="color:blue" path="MONO"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">EOS [K/ul]</label>
			<form:input path="EOS" type="number" step="0.1" class="form-control"
				id="exampleInputEmail1" />
			<form:errors cssStyle="color:blue" path="EOS"></form:errors>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">BASO [x109/l]</label>
			<form:input path="BASO" type="number" step="0.01"
				class="form-control" id="exampleInputEmail1" />
			<form:errors cssStyle="color:blue" path="BASO"></form:errors>
		</div>


		<button formaction="/MedicalAnalysis/morphology/add" type="submit"
			class="btn btn-primary btn-lg btn-block">Submit</button>

	</form:form>
	<a href="/MedicalAnalysis/results/add/${patientPesel} ">
		<button type="button" class="btn btn-secondary btn-lg btn-block">Back</button>
	</a>
</body>
</html>