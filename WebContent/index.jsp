<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

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

function showModal(){
	console.log("modal called");
	var spinner = '<div class="spinner-grow text-success" role="status"><span class="visually-hidden">Loading...</span></div>';
	const url = 'https://picsum.photos/300/300';

	 $.ajax({
	       url,
	       xhrFields:{
	          responseType: 'blob'
	       },
	       beforeSend: function() {
	           $('#modalImage').hide();
	    	   $("#imageLoader").show();
	       },
	       success (data) {
	    	  $("#imageLoader").hide();
	          const url = window.URL || window.webkitURL;
	          const src = url.createObjectURL(data);
	          var img = $('#modalImage');
	          img.attr('src', src);
	          img.show();
	       }
	   });
	

	
	const xhr = new XMLHttpRequest();
	
	xhr.open('GET','info.txt',true);
	
	xhr.onprogress = function(){
		console.log("On progress");
		document.getElementById('modalContent').innerHTML = spinner;
	}
	
	xhr.onload = function(){
		console.log(this.responseText);
		if(this.status == 200){	
			document.getElementById('modalContent').innerHTML = this.responseText + " <br> You can login as admin,manager or employee";
		}
		else{
			document.getElementById('modalContent').innerHTML = spinner;
		}
	}
	
	xhr.send();
	
}


function validateForm(e){
	const usernameEl = document.querySelector('#username');
	const passwordEl = document.querySelector('#password');


	const form = document.querySelector('#signin');

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
	        case 'username':
	            checkUsername();
	            break;
	        case 'password':
	            checkPassword();
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
	const checkUsername = () => {

	    let valid = false;
	    const min = 3,
	        max = 25;
	    const username = usernameEl.value.trim();

	    if (!isRequired(username)) {
	        showError(usernameEl, 'Username cannot be blank.');
	    } else if (!isBetween(username.length, min, max)) {
	        showError(usernameEl, `Username must be between ${min} and ${max} characters.`)
	    } else {
	        showSuccess(usernameEl);
	        valid = true;
	    }
	    return valid;
	}

	const checkPassword = () => {

	    let valid = false;

	    const password = passwordEl.value.trim();

	    if (!isRequired(password)) {
	        showError(passwordEl, 'Password cannot be blank.');
	    } else if (!isPasswordSecure(password)) {
	        showError(passwordEl, 'Password must has at least 8 characters that include at least 1 lowercase character, 1 uppercase characters, 1 number, and 1 special character in (!@#$%^&*)');
	    } else {
	        showSuccess(passwordEl);
	        valid = true;
	    }

	    return valid;
	};
	
	
	// prevent the form from submitting
    e.preventDefault();
    
    // validate forms
    var isUsernameValid = checkUsername(),
        isPasswordValid = checkPassword();

    var isFormValid = isUsernameValid &&
        isPasswordValid;

    // submit to the server if the form is valid
    if (isFormValid) {
		form.submit();
    }

}

</script>

</head>
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalTitle">Manual</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="modalBody">
        <div class="row">
        <div id="imageLoader" class="spinner-grow text-danger text-center mx-auto" role="status"><span class="visually-hidden">Loading...</span></div>
        	<img id="modalImage" height="200" width="200"></img>
        </div>
        <div class="row">
        	<div id="modalContent">
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary"  data-bs-dismiss="modal">Close</button>
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
					<a class="btn btn-outline-info mx-3" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="showModal()">Show Manual</a>
					<a class="btn btn-outline-warning" href="register">SignUp</a>	
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

				<form action="login" id="signin" method="POST">
					<div class="form-group col-6 mx-auto">
						<label for="exampleInputEmail1">Username</label> <input
							type="email" class="form-control" id="username"
							aria-describedby="emailHelp" name="username"
							placeholder="Enter username"> <small></small>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" id="password"
							placeholder="Enter Password" name="password"> <small></small>
					</div>
					<div class="row my-2 col-2 mx-auto">
						<button type="submit" onclick="return validateForm(event);"
							class="btn btn-primary">Login</button>
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