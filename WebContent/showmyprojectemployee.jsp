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
	if (session.getAttribute("usertype").equals("manager")) {
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
	function deleteProject(elem) {
		var r = confirm("Do you really want to delete this project ?");
		if (r == true) {
			var id = elem.name;
			window.location = "deleteProject?projectid=" + id;
		} else {

		}
	}
	function requestDeallocation(elem) {
		var r = confirm("Do you really want to deallocate yourself from your current project ?");
		if (r == true) {
			var id = elem.name;
			window.location = "deallocateMe?managerid=" + id;
		} else {

		}
	}
	
</script>
</head>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalTitle">Project Details</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body" id="modalBody">
				<div class="row">
					<p>Project Id :  <s:property value="projectBean.projectid"></s:property></p>
					<p>Project Name :  <s:property value="projectBean.projectname"></s:property></p>
					<p>Project Location :  <s:property value="projectBean.projectlocation"></s:property></p>
					<p>Project Manager Name :  <s:property value="projectBean.managername"></s:property></p>
					<p>Project Team Size :  <s:property value="projectBean.teamsize"></s:property></p>
				
				</div>
				<div class="row">
						<h4>Project Team</h4>
						<ul class="list-group">
							<s:iterator var="i" step="1" value="projectBean.team">			
							<li class="list-group-item list-group-item-primary"><s:property/></li>
							</s:iterator>
						</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
	<body class="d-flex flex-column h-100">

		<header>
			<nav class="navbar sticky-top navbar-dark bg-primary">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">Project Management</a>
					<form class="d-flex">
						<a class="btn btn-outline-info mx-3"
							onclick="window.history.back()">Back</a> <a
							class="btn btn-danger" href="logout">Logout</a>
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
					<a class="btn btn-success">Your Project</a>
				</div>
				<div class="row">
					<s:if test="hasActionMessages()">
					<h5 class="mx-auto my-5 w-50" style="color: gold">
						<s:iterator value="actionMessages">
							<s:property />
							<br />
						</s:iterator>
					</h5>
				</s:if>
				</div>
				<div class="row my-5">

					<div class="row table-responsive" style="max-height: 500px;">

						<table class="table table-fixed header-fixed">
							<thead style="position: sticky; top: 0">
								<tr>
									<th scope="col">Project Id</th>
									<th scope="col">Project Name</th>
									<th scope="col">Project Location</th>
									<th scope="col">Team Size</th>
									<th scope="col">Manager</th>
									<th scope="col">View</th>
									<th scope="col">Request Deallocation From Project</th>			
								</tr>
							</thead>
							<tbody id="users">
								<tr class="items">
									<th scope="row"><s:property value="projectBean.projectid" /></th>
									<td id="projectname"><s:property
											value="projectBean.projectname" /></td>
									<td><s:property value="projectBean.projectlocation" /></td>
									<td><s:property value="projectBean.teamsize" /></td>
									<td><s:property value="projectBean.managername" /></td>
									<td><a class="btn btn-info" data-bs-toggle="modal"
										data-bs-target="#staticBackdrop">View</a></td>
										<td>
										<button type="button" name="<s:property value="projectBean.managerid" />"
											id="deallocateBtn" onclick="requestDeallocation(this)"
											class="btn btn-warning">Request Deallocation</button>
									</td>
								</tr>
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