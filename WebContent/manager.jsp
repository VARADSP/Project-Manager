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
	if (!session.getAttribute("usertype").equals("manager")) {
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
	function deallocateEmployee(elem) {
		var r = confirm("Do you really want to deallocate this person from your project ?");
		if (r == true) {
			var id = elem.name;
			window.location = "deallocateEmployee?userid=" + id;
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
					<a class="btn btn-info mx-3" href="showMyProject?username=<%= session.getAttribute("username") %>">My Project</a>
					<a class="btn btn-outline-warning mx-3" href="showEmployees">Show Employees</a>
					<a class="btn btn-success mx-3" href="showApplications">Show Applications<span class="translate-middle badge rounded-pill bg-danger"><%=session.getAttribute("applicationCount")%></span></a>
					<a class="btn btn-danger" href="logout" >Logout</a>
				</form>
			</div>
		</nav>
	</header>

	<!-- Begin page content -->
	<main>
		<div class="container mx-auto my-5">
			<div class="row">
				<p>Welcome Manager <%=session.getAttribute("username")%></p>
			</div>
			<div class="row">
				<h4>Project Users</h4>
			</div>
					<div class="row my-5">

				<div class="row table-responsive" style="max-height: 500px;">

					<table class="table table-fixed header-fixed">
						<thead style="position: sticky; top: 0">
							<tr>
								<th scope="col">Userid</th>
								<th scope="col">Username</th>
								<th scope="col">Name</th>
								<th scope="col">Category</th>
								<th scope="col">Sex</th>
								<th scope="col">Address</th>
								<th scope="col">Emailid</th>
								<th scope="col">IsDisabled</th>
								<th scope="col">IsAllocated</th>
								<th scope="col">Project</th>
								<th scope="col">Allocate Employee</th>
							</tr>
						</thead>
						<tbody id="users">
							<s:iterator var="i" step="1" value="projectemployeeslist">
								<tr class="items">
									<th scope="row"><s:property value="userid" /></th>
									<td><s:property value="username" /></td>
									<td><s:property value="name" /></td>
									<td><s:property value="category" /></td>
									<td><s:property value="sex" /></td>
									<td><s:property value="address" /></td>
									<td><s:property value="emailid" /></td>
									<td><s:property value="isdisabled" /></td>
									<td><s:property value="isallocated" /></td>
									<td><s:property value="project" /></td>
									<td>
										<button type="button" name="<s:property value="userid" />"
											id="allocateBtn" onclick="deallocateEmployee(this)"
											class="btn btn-warning" <s:if test= 'username == session.get("username")'>disabled</s:if>>Deallocate</button>
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