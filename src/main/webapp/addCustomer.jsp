<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--
	This is the Add Customer page
	This page displays fields to add a Customer 
	The details are sent to the AddCustomerController class in resources package
-->


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Customer</title>
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />	
	<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		 <style>
        body {
            background-color: #ffffff;
        }
        .form-card {
            border: none;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            padding: 30px;
            background-color: white;
            margin-top: 20px;
            margin-bottom: 40px;
        }
        .section-title {
            color: #007bff;
            font-size: 1.1rem;
            font-weight: bold;
            margin-bottom: 15px;
            border-bottom: 2px solid #e9ecef;
            padding-bottom: 5px;
        }
        .custom-control-label {
            font-weight: bold;
            color: #495057;
        }
	</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-white">
        <div class="container">
			
        </div>
    </nav>

	<div class="container">
	
        <div class="row justify-content-center">
            <div class="col-lg-8">
				<div class="form-card">
                    <h2 class="mb-4 text-center">Register New Employee</h2>
					<form action="addCustomer" method="POST">
					<div class="form-group">
						<label for="customerEmail">Email address</label>
						<input type="email" class="form-control" id="customerEmail" name="customerEmail" placeholder="Enter email" required>
					</div>
					<div class="form-group">
						<label for="customerPassword">Password</label>
						<input type="password" class="form-control" id="customerPassword" name="customerPassword" placeholder="Password" required>
					</div>
					<div class="form-group">
						<label for="customerFirstName">First Name</label>
						<input type="text" class="form-control" id="customerFirstName" name="customerFirstName" placeholder="First Name" required>
					</div>
					<div class="form-group">
						<label for="customerLastName">last Name</label>
						<input type="text" class="form-control" id="customerLastName" name="customerLastName" placeholder="Last Name" required>
					</div>
					<div class="form-group">
						<label for="customerAddress">Address</label>
						<input type="text" class="form-control" id="customerAddress" name="customerAddress" placeholder="Address" required>
					</div>
					<div class="form-group">
						<label for="customerCity">City</label>
						<input type="text" class="form-control" id="customerCity" name="customerCity" placeholder="City" required>
					</div>
					<div class="form-group">
						<label for="customerState">State</label>
						<input type="text" class="form-control" id="customerState" name="customerState" placeholder="State" required>
					</div>
					<div class="form-group">
						<label for="customerZipcode">Zipcode</label>
						<input type="text" class="form-control" id="customerZipcode" name="customerZipcode" placeholder="Zipcode" required>
					</div>
					<div class="form-group">
						<label for="customerCreditCard">Credit Card Number</label>
						<input type="text" class="form-control" id="customerCreditCard" name="customerCreditCard" placeholder="XXXX-XXXX-XXXX-XXXX" required>
					</div>
					<div class="form-group">
						<label for="customerRating">Rating</label>
						<input type="text" class="form-control" id="customerRating" name="customerRating" placeholder="Rating" required>
					</div>
					<div class="d-flex justify-content-between">
						<button type="submit" class="btn btn-primary btn-lg" style="width: 48%">Create Customer</button>
						<a href="home.jsp" class="btn btn-secondary text-white" style="width: 48%">Cancel</a>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>