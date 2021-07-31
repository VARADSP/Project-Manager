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
.error{
	border-color:red;
}
.success{
	border-color:green;
}
</style>
<script>

function validateForm(e){
	const usernameEl = document.querySelector('#username');
	const nameEl = document.querySelector('#name');
	const emailEl = document.querySelector('#emailid');
	const passwordEl = document.querySelector('#password');
	const addressEl = document.querySelector('#address');


	const form = document.querySelector('#signup');
	

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
	        case 'email':
	            checkEmail();
	            break;
	        case 'password':
	            checkPassword();
	            break;
	        case 'address':
	            checkAddress();
	            break;
	        case 'name':
	            checkName();
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
	const checkName = () => {

	    let valid = false;
	    const min = 3,
	        max = 25;
	    const name = nameEl.value.trim();

	    if (!isRequired(name)) {
	        showError(nameEl, 'Name cannot be blank.');
	    } else if (!isBetween(name.length, min, max)) {
	        showError(nameEl, `Name must be between ${min} and ${max} characters.`)
	    } else {
	        showSuccess(nameEl);
	        valid = true;
	    }
	    return valid;
	}
	const checkAddress = () => {

	    let valid = false;
	    const min = 3,
	        max = 45;
	    const address = addressEl.value.trim();

	    if (!isRequired(address)) {
	        showError(addressEl, 'Address cannot be blank.');
	    } else if (!isBetween(address.length, min, max)) {
	        showError(addressEl, `Address must be between ${min} and ${max} characters.`)
	    } else {
	        showSuccess(addressEl);
	        valid = true;
	    }
	    return valid;
	}
	const checkEmail = () => {
	    let valid = false;
	    const email = emailEl.value.trim();
	    if (!isRequired(email)) {
	        showError(emailEl, 'Email cannot be blank.');
	    } else if (!isEmailValid(email)) {
	        showError(emailEl, 'Email is not valid.')
	    } else {
	        showSuccess(emailEl);
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
        isEmailValid2 = checkEmail(),
        isPasswordValid = checkPassword(),
        isNameValid = checkName(),
        isAddressValid = checkAddress();

    var isFormValid = isUsernameValid &&
        isEmailValid2 &&
        isPasswordValid &&
        isNameValid &&
        isAddressValid;

    // submit to the server if the form is valid
    if (isFormValid) {
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
					<a class="btn btn-outline-info mx-3" href="showUsers">Back</a> <a
						class="btn btn-danger" href="logout">Logout</a>
				</form>
			</div>
		</nav>
	</header>


	<!-- Begin page content -->
	<main>
		<div class="container mx-auto my-5 w-50">
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
		
			<div class="row">
				<form action="changeUser" id="signup" method="POST">
					<div class="form-group col-6 mx-auto">
						<label for="exampleInputEmail1">Username</label> <input
							type="text" class="form-control" id="username"
							aria-describedby="usernameHelp" name="username" 
							placeholder="Enter username" value="<s:property value="userListBean.username"/>">
					<small></small>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputPassword1">Password</label> <input
							type="text" class="form-control" name="password" id="password"
							placeholder="Enter Password" value="<s:property value="userListBean.password"/>">
					<small></small>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputName1">Name</label> <input type="text"
							class="form-control" id="name" name="name" placeholder="Enter Name" value="<s:property value="userListBean.name"/>">
					<small></small>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputName1">Category</label> 
						<select
							class="form-select" aria-label="Default select example" name="category">
							<option value="admin" <s:if test= 'userListBean.category == "admin"'>selected</s:if> >Admin</option>
							<option value="manager" <s:if test= 'userListBean.category == "manager"'>selected</s:if>>Manager</option>
							<option value="employee" <s:if test= 'userListBean.category == "employee"'>selected</s:if>>Employee</option>
						</select>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputName1">Sex</label> 
						<select
							class="form-select" aria-label="Default select example" name="sex">
							<option value="male" <s:if test= 'userListBean.sex == "male"'>selected</s:if>>Male</option>
							<option value="female" <s:if test= 'userListBean.sex == "female"'>selected</s:if>>Female</option>
						</select>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputAddress1">Address</label> <input type="text"
							class="form-control" id="address" name="address" placeholder="Enter Address" value="<s:property value="userListBean.address"/>">
					<small></small>
					</div>
					<div class="form-group my-3 col-6 mx-auto">
						<label for="exampleInputEmailId1">EmailId</label> <input type="email"
							class="form-control" id="emailid" name="emailid" placeholder="Enter EmailId" value="<s:property value="userListBean.emailid"/>">
							<small></small>
					</div>
						<input
							type="text" class="form-control" id="userid"
							aria-describedby="emailHelp" name="userid"
						    value="<s:property value="userListBean.userid"/>" hidden>
						 	<input
							type="text" class="form-control" id="isDisabled"
							aria-describedby="emailHelp" name="isDisabled"
						    value="<s:property value="userListBean.isDisabled"/>" hidden>   
					<div class="row my-2 col-3 mx-auto">
						<button type="submit" onclick="return validateForm(event);" class="btn btn-primary">Update</button>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>