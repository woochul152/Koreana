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
	This is the Customer Mailing List page
	This page displays the data for each Customer object in the ArrayList from the getCustomers method in dao.CustomerDao class
	The details are fetched using the "customers" field from the request object
-->


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Customer Mailing List</title>
		<style>
        body {
            background-color: #ffffff;
            min-height: 100vh;
            padding-top: 40px;
            padding-bottom: 40px;
        }
        
        .table-card {
            border: none;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            padding: 30px;
            background-color: white;
            width: 100%;
        }
        
        .thead-primary {
            background-color: #007bff;
            color: white;
        }
        
        .btn-action {
            width: 80px;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
	<div class="container-fluid px-4"> 
		<div class="row justify-content-center">
			<div class="col-12">
				<div class="table-card">
					<div class="d-flex justify-content-between align-items-center mb-4">
						<h2 class="text-primary font-weight-bold">Customer Mailing List</h2>
						<form action="home.jsp">
							<button type="submit" class="btn btn-outline-secondary">Back to Dashboard</button>
						</form>
					</div>
				<c:if test="${empty customers}">
					<div class="alert alert-warning text-center" role="alert">
						<h4>No Customer Records Found</h4>
						<p>There are no customers in the database currently.</p>
					</div>
				</c:if>
				<c:if test="${not empty customers}">
					<div class="table-responsive">
						<table class="table table-hover table-bordered">
		  					<thead class="thread-primary">
								<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Address</th>
								<th>City</th>
								<th>State</th>
								<th>Zip Code</th>
								<th>Email</th>
								</tr>
		  					</thead>
		  				<tbody>
							<c:forEach items="${customers}" var="cd">
							<tr>
								<td>${cd.firstName}</td>
								<td>${cd.lastName}</td>
								<td>${cd.address}</td>
								<td>${cd.city}</td>
								<td>${cd.state}</td>
								<td>${cd.zipCode}</td>
								<td>${cd.email}</td>
							</tr>
							</c:forEach>
		  				</tbody>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	
	<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
</body>
</html>