<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<!--
	This is the Add Employee page
	This page displays fields to add an Employee 
	The details are sent to the AddEmployeeController class in resources package
-->
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Employee</title>
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
            <div class="col-lg-8"> <div class="form-card">
                    <h2 class="mb-4 text-center">Register New Employee</h2>
                    
                    <form action="addEmployee" method="POST">
                        
                        <div class="section-title mt-2">Account Information</div>
                        <div class="form-row"> <div class="form-group col-md-6"> <label for="employeeEmail">Email Address</label>
                                <input type="email" class="form-control" id="employeeEmail" name="employeeEmail" placeholder="Enter email" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="employeePassword">Password</label>
                                <input type="password" class="form-control" id="employeePassword" name="employeePassword" placeholder="Password" required>
                            </div>
                        </div>

                        <div class="section-title mt-3">Personal Details</div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="employeeFirstName">First Name</label>
                                <input type="text" class="form-control" id="employeeFirstName" name="employeeFirstName" placeholder="First Name" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="employeeLastName">Last Name</label>
                                <input type="text" class="form-control" id="employeeLastName" name="employeeLastName" placeholder="Last Name" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="employeeSSN">SSN</label>
                            <input type="text" class="form-control" id="employeeSSN" name="employeeSSN" placeholder="XXXXXXXXX" required>
                        </div>

                        <div class="section-title mt-3">Address</div>
                        <div class="form-group">
                            <label for="employeeAddress">Street Address</label>
                            <input type="text" class="form-control" id="employeeAddress" name="employeeAddress" placeholder="Address" required>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="employeeCity">City</label>
                                <input type="text" class="form-control" id="employeeCity" name="employeeCity" placeholder="City" required>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="employeeState">State</label>
                                <input type="text" class="form-control" id="employeeState" name="employeeState" placeholder="State" required>
                            </div>
                            <div class="form-group col-md-2">
                                <label for="employeeZipcode">Zip</label>
                                <input type="text" class="form-control" id="employeeZipcode" name="employeeZipcode" placeholder="Zipcode" required>
                            </div>
                        </div>

                        <div class="section-title mt-3">Job Details</div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="employeeStartDate">Start Date</label>
                                <input type="text" class="form-control" id="employeeStartDate" name="employeeStartDate" placeholder="YYYY-MM-DD" required> 
                                </div>
                            <div class="form-group col-md-6">
                                <label for="employeeHourlyRate">Hourly Rate ($)</label>
                                <input type="text" step="0.01" class="form-control" id="employeeHourlyRate" name="employeeHourlyRate" placeholder="Hourly Rate" required>
                            </div>
                        </div>

                        <div class="form-group mt-3">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="isManager" name="isManager">
                                <label class="custom-control-label" for="isManager">Assign as Manager Role</label>
                            </div>
                        </div>

                        <hr class="my-4">

                        <div class="d-flex justify-content-between">
							<button type="submit" class="btn btn-primary btn-lg" style="width: 48%">Create Employee</button>
                            <a href="home.jsp" class="btn btn-secondary text-white" style="width: 48%">Cancel</a>
                        </div>

                    </form>
                </div> </div>
        </div>
    </div>


</body>
</html>