<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
	This is the Home page for Manager
	This page contains various buttons to navigate the online auction house
	This page contains manager specific accessible buttons
-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width:device-width, initial-scale=1">
    
    <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Manager Home</title>
    
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
            if(role.equals("customerRepresentative")) {
                response.sendRedirect("customerRepresentativeHome.jsp");
            }
            else if(!role.equals("manager")){
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
                        <h5 class="card-title text-primary">Manage Employees</h5>
                        <p class="card-text text-muted small">Add, edit, or remove staff members.</p>
                        
                        <div class="btn-wrapper">
                            <form action="addEmployee.jsp">
                                <button type="submit" class="btn btn-primary btn-block">Add Employee</button>
                            </form>
                        </div>
                        <div class="btn-wrapper">
                            <form action="getEmployees">
                                <button type="submit" class="btn btn-outline-primary btn-block">View / Edit Employees</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title text-info">Flight Operations</h5>
                        <p class="card-text text-muted small">Monitor flight status and schedules.</p>

                        <div class="row">
                            <div class="col-6 p-1">
                                <form action="getFlights"><button class="btn btn-info btn-block btn-sm">View All</button></form>
                            </div>
                            <div class="col-6 p-1">
                                <form action="searchFlightsByAirport"><button class="btn btn-info btn-block btn-sm">Search</button></form>
                            </div>
                            <div class="col-6 p-1">
                                <form action="getOnTimeFlights"><button class="btn btn-outline-info btn-block btn-sm">On-time</button></form>
                            </div>
                            <div class="col-6 p-1">
                                <form action="getDelayedFlights"><button class="btn btn-outline-danger btn-block btn-sm">Delayed</button></form>
                            </div>
                        </div>
                            <div class="mt-2">
                             <form action="getMostActiveFlights">
                                <button class="btn btn-dark btn-block btn-sm">Most Active Flights</button>
                            </form>
                         </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title text-success">Revenue & Analytics</h5>
                        <p class="card-text text-muted small">Financial reports and top performers.</p>

                        <div class="btn-wrapper">
                            <form action="searchSalesReport.jsp">
                                <button class="btn btn-success btn-block">Sales Report</button>
                            </form>
                        </div>
                        <div class="btn-wrapper">
                             <form action="searchRevenueSummary">
                                <button class="btn btn-outline-success btn-block">Revenue Summary</button>
                            </form>
                        </div>
                        
                        <hr>
                        <h6 class="small font-weight-bold">Highest Revenue Generators:</h6>
                        <div class="row">
                             <div class="col-6 p-1">
                                <form action="getHighestRevenue"><button class="btn btn-sm btn-outline-dark btn-block">Rep</button></form>
                             </div>
                             <div class="col-6 p-1">
                                <form action="getHighestRevenueCustomer"><button class="btn btn-sm btn-outline-dark btn-block">Customer</button></form>
                             </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title text-warning">Reservations</h5>
                        <p class="card-text text-muted small">Search bookings and customer lists.</p>

                        <div class="btn-wrapper">
                            <form action="searchFlightReservations">
                                <button class="btn btn-warning btn-block text-white">Search Reservations</button>
                            </form>
                        </div>
                        <div class="btn-wrapper">
                            <form action="listCustomersByFlight">
                                <button class="btn btn-outline-warning btn-block">List Customers by Flight</button>
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