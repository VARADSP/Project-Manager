<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/>


<meta http-equiv="refresh" content="0.5" url='<s:url includeParams="/all" />'">  

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Struts2 Project Management</title>
<style>
.footer {
	position: fixed;
	bottom: 0;
	margin: auto;
	width: 100%;
	height: auto;
}
</style>
</head>
<body class="d-flex flex-column h-100">
	<header>
		<nav class="navbar sticky-top navbar-dark bg-primary">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Project Management</a>
				<form class="d-flex">
					<a class="btn btn-outline-warning" href="register">SignUp</a>
				</form>
			</div>
		</nav>
	</header>

	<!-- Begin page content -->
	<main>
		<div class="container mx-auto my-5 w-50">
			<div class="row">
						<s:if test="hasActionMessages()">
					<h5 class="mx-auto my-5 w-50" style="color: gold">
						<s:actionmessage />
					</h5>
				</s:if>
				<%-- hasActionErrors() method is defined in ActionSupport --%>
				<s:if test="hasActionErrors()">
					<h5 class="mx-auto my-5 w-50" style="color: red;">
						<s:iterator value="actionErrors">
							<s:property />
							<br />
						</s:iterator>
					</h5>
				</s:if>
					
			</div>
		</div>
	</main>

	<footer class="footer mt-auto py-3 bg-dark">
		<div class="container">
			<span class="text-muted">@2021 Copyright</span>
		</div>
	</footer>


	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>