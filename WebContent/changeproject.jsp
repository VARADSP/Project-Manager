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


function validateForm(e){
	const projectnameEl = document.querySelector('#projectname');
	const projectlocationEl = document.querySelector('#projectlocation');
	const projectmanagerEl = document.querySelector('#projectmanager');


	const form = document.querySelector('#projectaddin');

	const debounce = (fn, delay = 500) => {
	    let timeoutId;
	    return (...args) => {
	        // cancel the previous timer
	        if (timeoutId) {
	            clearTimeout(timeoutId);
	        }
	        // setup a new timer
	        timeoutId = setTimeout(() => {
	            fn.apply(null, args)
	        }, delay);
	    };
	};
	
	form.addEventListener('input', debounce(function (e) {
		console.log("input event called");
	    switch (e.target.id) {
	        case 'projectname':
	            checkProjectName();
	            break;
	        case 'projectlocation':
	            checkProjectLocation();
	            break;
	        case 'projectmanager':
	            checkProjectManager();
	            break;
	            
	    }
	}));
	
	
	const isRequired = value => value === '' ? false : true;

	const isBetween = (length, min, max) => length < min || length > max ? false : true;

	const isEmailValid = (email) => {
	    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(email);
	};

	const isPasswordSecure = (password) => {
	    const re = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	    return re.test(password);
	};

	const showError = (input, message) => {
	    // get the form-field element
	    const formField = input.parentElement;
	    // add the error class
	    formField.classList.remove('success');
	    formField.classList.add('error');
	    input.style.borderColor="red";
	    // show the error message
	    const error = formField.querySelector('small');
	    error.textContent = message;
	    error.style.color="red";
	};

	const showSuccess = (input) => {
	    // get the form-field element
	    const formField = input.parentElement;
		
	    // remove the error class
	    formField.classList.remove('error');
	    formField.classList.add('success');
	    input.style.borderColor="green";
		    

	    // hide the error message
	    const error = formField.querySelector('small');
	    error.textContent = '';
	}
	const checkProjectName = () => {

	    let valid = false;
	    const min = 3,
	        max = 25;
	    const projectname = projectnameEl.value.trim();

	    if (!isRequired(projectname)) {
	        showError(projectnameEl, 'Project Name cannot be blank.');
	    } else {
	        showSuccess(projectnameEl);
	        valid = true;
	    }
	    return valid;
	}

	const checkProjectManager = () => {

	    let valid = false;
	    
	    const managerid = projectmanagerEl.value.trim();

	    if (!isRequired(managerid)) {
	        showError(projectmanagerEl, 'Project Manager needs to be selected !');
	    } else {
	        showSuccess(projectmanagerEl);
	        valid = true;
	    }
	    return valid;
	}
	
	const checkProjectLocation = () => {

	    let valid = false;

	    const projectlocation = projectlocationEl.value.trim();

	    if (!isRequired(projectlocation)) {
	        showError(projectlocationEl, 'Project Location cannot be blank.');
	    }  else {
	        showSuccess(projectlocationEl);
	        valid = true;
	    }

	    return valid;
	};
	
	
	// prevent the form from submitting
    e.preventDefault();
    
    // validate forms
    var isProjectNameValid = checkProjectName(),
        isProjectLocationValid = checkProjectLocation(),
        isManagerValid = checkProjectManager();

    var isFormValid = isProjectNameValid &&
        isProjectLocationValid && isManagerValid;

    // submit to the server if the form is valid
    if (isFormValid) {
    	console.log('form valid');
		form.submit();
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
					<a class="btn btn-outline-info mx-3" href="showAdmin">Back</a> <a
						class="btn btn-danger" href="logout">Logout</a>
				</form>
			</div>
			
		</nav>
	</header>

	<!-- Begin page content -->
	<main>
		<div class="container mx-auto my-5 w-50"
			style="position: relative; top: 150px">
			<div class="row">
				<s:if test="hasActionMessages()">
					<h5 class="mx-auto my-5 w-50" style="color: gold">
						<s:iterator value="actionMessages">
							<s:property />
							<br />
						</s:iterator>
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

				<form action="changeProject" id="projectaddin" method="POST">
					<div class="form-group col-6 mx-auto">
					<input
							type="text" class="form-control" id="projectid"
							aria-describedby="emailHelp" name="projectid"
						    value="<s:property value="projectBean.projectid"/>" hidden>
						<label for="exampleInputEmail1">Project Name</label> <input
							type="text" class="form-control" id="projectname"
							aria-describedby="emailHelp" name="projectname"
							placeholder="Enter Project Name" value="<s:property value="projectBean.projectname"/>"> <small></small>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputPassword1">Project Location</label> <input
							type="text" class="form-control" id="projectlocation"
							placeholder="Enter Project Location" name="projectlocation" value="<s:property value="projectBean.projectlocation"/>"> <small></small>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputName1">Choose Manager</label> 
						<select
							class="form-select" aria-label="Default select example" id="projectmanager" name="managerid">
							<option selected value="">---</option>
								<option value="<s:property value="projectBean.managerid"/>" selected><s:property value="projectBean.managername"/></option>
							<s:iterator var="i" step="1" value="managers">
							<option value="<s:property value="managerid"/>"><s:property value="managername"/></option>
							</s:iterator>
						</select>
						<small></small>
					</div>
					<div class="row my-2 col-6 mx-auto">
						<button type="submit" onclick="return validateForm(event);"
							class="btn btn-primary">Change Project</button>
					</div>
				</form>
			</div>
		</div>
	</main>

	<footer class="footer mt-auto py-3 bg-dark">
		<div class="container">
			<span class="text-muted">@2021 Copyright</span>
		</div>
	</footer>


	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>