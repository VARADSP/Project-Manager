<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	if (session.getAttribute("username") == null) {
		response.sendRedirect("index.jsp");
	}
	if (session.getAttribute("usertype") == null) {
		response.sendRedirect("index.jsp");
	}
	if (session.getAttribute("usertype").equals("admin")) {
		response.sendRedirect("index.jsp");
	}
	if (session.getAttribute("usertype").equals("employee")) {
		response.sendRedirect("index.jsp");
	}
%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

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
<script>
	function updateProject(elem) {
		var id = elem.name;
		window.location = "getProject?projectid=" + id;
	}
	function deleteApplication(elem) {
		var id = elem.name;
		window.location = "deleteApplication?applicationid=" + id;
	}
	function allocateEmployee(elem) {
		var r = confirm("Do you really want to allocate this person to your project ?");
		if (r == true) {
			var id = elem.name;
			window.location = "allocateEmployee?userid=" + id;
		} else {

		}
	}
</script>
</head>

<body class="d-flex flex-column h-100">

	<header>
		<nav class="navbar sticky-top navbar-dark bg-primary">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Project Management</a>
				<form class="d-flex">
					<a class="btn btn-outline-info mx-3"
						href="showManager">Back</a> <a class="btn btn-danger"
						href="logout">Logout</a>
				</form>
			</div>
		</nav>
	</header>

	<!-- Begin page content -->
	<main>
		<div class="container mx-auto my-5">
			<div class="row">
				<p>
					Welcome
					<%=session.getAttribute("username")%></p>
			</div>
			<div class="row w-25">
				<a class="btn btn-success">Employee Applications</a>
			</div>
			<div class="row my-5">

				<div class="row table-responsive" style="max-height: 500px;">

					<table class="table table-fixed header-fixed">
						<thead style="position: sticky; top: 0">
							<tr>
								<th scope="col">Application Id</th>
								<th scope="col">User ID</th>
								<th scope="col">Project ID</th>
								<th scope="col">Manager ID</th>
								<th scope="col">Is Approved</th>
								<th scope="col">Request</th>
								<th scope="col">Request Type</th>
								<th scope="col">Delete Application</th>	
							</tr>
						</thead>
						<tbody id="users">
							<s:iterator var="i" step="1" value="applicationslist">
								<tr class="items">
									<th scope="row"><s:property value="id" /></th>
									<td><s:property value="userid" /></td>
									<td><s:property value="projectid" /></td>
									<td><s:property value="managerid" /></td>
									<td><s:property value="isapproved" /></td>
									<td><s:property value="request" /></td>
									<td><s:property value="requesttype" /></td>
									<td>
										<button type="button" name="<s:property value="id" />"
											id="deleteApplicationBtn" onclick="deleteApplication(this)"
											class="btn btn-warning">Delete</button>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

				</div>
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