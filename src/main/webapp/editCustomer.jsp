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
	This is the Edit Customer page
	This page displays fields to edit a Customer 
	The details are sent to the UpdateCustomerController class in resources package
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit Customer</title>
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
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">
			<c:if test="${empty editCustomer}">
				<div class="alert alert-danger text-center shadow-sm" role="alert">
					<h4 class="alert-heading">Customers Not Found!</h4>
					<p>The customer details could not be retrieved.</p>
					<hr>
					<a href="customerRepresentativeHome.jsp" class="btn btn-danger btn-sm">Return to Dashboard</a>
				</div>
			</c:if>
			<c:if test="${not empty editCustomer}"> 	
                <div class="form-card">
                    <h2 class="mb-4 text-center">Edit Customer Details</h2>
						<form action="updateCustomer" method="POST">
							<div class="section-title mt-2">Account Information</div>
								<div class="form-row">
                            		<div class="form-group col-md-6">
									<label for="customerEmail">Email address</label>
	    							<input type="email" class="form-control" id="customerEmail" name="customerEmail" placeholder="Enter email" value=${editCustomer.email} required>
	  							</div>
								<div class="form-group col-md-6">
	    							<label for="customerFirstName">First Name</label>
	    							<input type="text" class="form-control" id="customerFirstName" name="customerFirstName" placeholder="First Name" value=${editCustomer.firstName} required>
	  							</div>
  	  							<div class="form-group col-md-6">
									<label for="customerLastName">last Name</label>
									<input type="text" class="form-control" id="customerLastName" name="customerLastName" placeholder="Last Name" value=${editCustomer.lastName} required>
	  							</div>
   	  							<div class="form-group col-md-6">
									<label for="customerAddress">Address</label>
									<input type="text" class="form-control" id="customerAddress" name="customerAddress" placeholder="Address" value=${editCustomer.address} required>
	  							</div>
   	  							<div class="form-group col-md-6">
									<label for="customerCity">City</label>
									<input type="text" class="form-control" id="customerCity" name="customerCity" placeholder="City" value=${editCustomer.city} required>
								</div>
   	  							<div class="form-group col-md-6">
									<label for="customerState">State</label>
									<input type="text" class="form-control" id="customerState" name="customerState" placeholder="State" value=${editCustomer.state} required>
								</div>
   	  							<div class="form-group col-md-6">
									<label for="customerZipcode">Zipcode</label>
									<input type="text" class="form-control" id="customerZipcode" name="customerZipcode" placeholder="Zipcode" value=${editCustomer.zipCode} required>
								</div>
   	  							<div class="form-group col-md-6">
									<label for="accountNo">Account No</label>
									<input type="text" class="form-control" id="accountNo" name="accountNo" placeholder="XXX" value=${editCustomer.accountNo} readonly>
								</div>
   	  							<div class="form-group col-md-6">
									<label for="customerCreditCard">Credit Card Number</label>
									<input type="text" class="form-control" id="customerCreditCard" name="customerCreditCard" placeholder="YYYY-MM-DD" value=${editCustomer.creditCard} required>
								</div>
								<div class="form-group col-md-6">
									<label for="customerRating">Rating</label>
									<input type="text" class="form-control" id="customerRating" name="customerRating" placeholder="Hourly Rate" value=${editCustomer.rating} required>
								</div>
							</div>
						</form>
					</div>
					<div class="d-flex justify-content-between">
						<button type="submit" class="btn btn-primary btn-lg" style="width: 48%">Edit Customer</button>
						<a href="home.jsp" class="btn btn-secondary text-white" style="width: 48%">Cancel</a>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>