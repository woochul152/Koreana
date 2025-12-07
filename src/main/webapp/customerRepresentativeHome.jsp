<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
	This is the Home page for Customer Representative
	This page contains various buttons to navigate the online auction house
	This page contains customer representative specific accessible buttons
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Customer Representative Home</title>
		<style>
			body {
				background-color: #ffffff;
			}
			.card {
				border: none;
				border-radius: 8px;
				transition: transform 0.2s;
			}
			.card:hover {
				transform: translateY(-5px);
				box-shadow: 0 10px 20px rgba(0,0,0,0.1) !important;
			}
			.navbar {
				box-shadow: 0 2px 4px rgba(0,0,0,0.1);
			}
			.btn-wrapper {
				margin-bottom: 8px;
			}
    	</style>
	</head>
	<body>

		<%
			String email = (String)session.getAttribute("email");
			String role = (String)session.getAttribute("role");
			
			//redirect to appropriate home page if already logged in
			if(email != null) {
				if(role.equals("manager")) {
					response.sendRedirect("managerHome.jsp");
				}
				else if(!role.equals("customerRepresentative")) {
					response.sendRedirect("home.jsp");
				}
			}
			else {
				// redirect to log in if not alreaddy logged in
				response.sendRedirect("index.jsp");
			}
		%>
		
		<nav class="navbar navbar-expand-lg navbar-white bg-dark mb-4">
			<div class="container">
			</div>
		</nav>

		<div class="container">

			<div class="jumbotron p-4 p-md-5 text-white rounded bg-info mb-4">
				<h1 class="display-4">Welcome</h1>
			</div>

		<div class="row">
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-100 shadow-sm">
					<div class="card-body">
					<h5 class="card-title">Record a Reservation</h5>
						<form action="bookFlightReservationsCustomer">
							<button type="submit" class="btn btn-primary btn-block">Book Reservation</button>
						</form>
					</div>
				</div>
			</div>

			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-100 shadow-sm">
					<div class="card-body">
					<h5 class="card-title">Manage Customer</h5>

					
					<div class="btn-wrapper">
						<form action="addCustomer.jsp">
							<button type="submit" class="btn btn-primary btn-block">Add Cusomter</button>
						</form>
					</div>

					<div class="btn-wrapper">
						<form action="getCustomers">
							<button type="submit" class="btn btn-outline-primary btn-block">View / Edit Customers</button>
						</form>
					</div>
					</div>	
				</div>
			</div>

			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-100 shadow-sm">
					<div class="card-body">
						<h5 class="card-title">View Customer Mailing List</h5>
						<div class="container">
							<form action="getCustomerMailingList">
								<button type="submit" class="btn btn-outline-primary btn-block">Customer Mailing List</button>
							</form>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-100 shadow-sm">
					<div class="card-body">
						<h5 class="card-title">View Suggestions for Customers</h5>
						<div class="btn-wrapper">
							<form action="getCustomers">
								<button type="submit" class="btn btn-outline-primary btn-block">View Suggestions</button>
							</form>
						</div>
					</div>
				</div>
			</div>			
		</div>
	
		<div class="container">
			<form action="logout">
				<input type="submit" value="Logout" class="btn btn-danger"/>
			</form>
		</div>
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>