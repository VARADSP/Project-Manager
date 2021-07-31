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
	if (!session.getAttribute("usertype").equals("admin")) {
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
	
	$(document).ready(function() {
		console.log( "ready!" );
		var filterInput = document.getElementById("filterInput");
		filterInput.addEventListener('keyup',filterList);
		
		function filterList(e){
			var filterValue = document.getElementById("filterInput").value.toLowerCase();
			
			var ul = document.getElementById("users");
			
			var rows = ul.querySelectorAll("tr.items");
			
			for(var i=0;i<rows.length;i++){
				
				var tds = rows[i].querySelectorAll("td,th");
				
				for(var j = 0;j<tds.length;j++){
					var tdValue = tds[j].innerHTML.toLowerCase();
					if(tdValue.indexOf(filterValue)>-1){
						rows[i].style.display="";
						break;
					}
					else{
						rows[i].style.display="none";
					}
				}
				
			}
			
		}
	});
	
	function enableUser(elem) {
		var id = elem.name;
		window.location = "enable?id=" + id;
	}
	function disableUser(elem) {
		var id = elem.name;
		window.location = "disable?id=" + id;
	}
	function updateUser(elem) {
		var id = elem.name;
		window.location = "getUser?id=" + id;
	}
	function deleteUser(elem) {
		var r = confirm("Do you really want to delete this user ?");
		if (r == true) {
			var id = elem.name;
			window.location = "delete?id=" + id;
		} else {

		}
	}
</script>
</head>
<body class="d-flex flex-column h-100">

	<header>
		<nav class="navbar sticky-top navbar-dark bg-primary">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Project Management Admin Access</a>
				<form class="d-flex">
					<a class="btn btn-outline-info mx-3" href="showAdmin">Back</a> <a
						class="btn btn-danger" href="logout">Logout</a>
				</form>
			</div>
		</nav>
	</header>

	<!-- Begin page content -->
	<main>
		<div class="container my-5">
			<div class="row">
				<p>
					Welcome
					<%=session.getAttribute("username")%></p>
			</div>
			<p>All Users</p>
			<br />
			<div class="input-group">
				<div class="form-outline">
					<input type="search" id="filterInput" class="form-control"
						placeholder="Search Users" />
				</div>
			</div>
			<div class="row table-responsive" style="max-height: 500px;">

				<table class="table table-fixed header-fixed">
					<thead style="position: sticky; top: 0">
						<tr>
							<th scope="col">UserId</th>
							<th scope="col">Username</th>
							<th scope="col">Password</th>
							<th scope="col">Name</th>
							<th scope="col">Category</th>
							<th scope="col">Gender</th>
							<th scope="col">Address</th>
							<th scope="col">EmailId</th>
							<th scope="col">isDisabled</th>
							<th scope="col">Enable/Disable</th>
							<th scope="col">Delete</th>
							<th scope="col">Update</th>
						</tr>
					</thead>
					<tbody id="users">
						<s:iterator var="i" step="1" value="users">

							<tr class="items">
								<th scope="row"><s:property value="userid" /></th>
								<td id="username"><s:property value="username" /></td>
								<td><s:property value="password" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="category" /></td>
								<td><s:property value="sex" /></td>
								<td><s:property value="address" /></td>
								<td><s:property value="emailid" /></td>
								<td><s:property value="isDisabled" /></td>
								<td><s:if test="isDisabled == 'true' ">
										<button type="button" name="<s:property value="userid" />"
											id="enableBtn" onclick="enableUser(this)"
											class="btn btn-success">Enable</button>
									</s:if> <s:else>
										<button type="button" name="<s:property value="userid" />"
											id="disableBtn" onclick="disableUser(this)"
											class="btn btn-warning">Disable</button>
									</s:else></td>
								<td><button type="button"
										name="<s:property value="userid" />" id="deleteBtn"
										onclick="deleteUser(this)" class="btn btn-danger">Delete</button></td>
								<td><button type="button"
										name="<s:property value="userid" />" id="updateBtn"
										onclick="updateUser(this)" class="btn btn-info">Update</button></td>		
							</tr>
						</s:iterator>

					</tbody>
				</table>

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