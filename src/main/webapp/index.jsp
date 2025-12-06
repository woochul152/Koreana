<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login | Online Travel Reservation System</title>
    
    <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
    
    <style>
        body {
            background-color: #f0f2f5;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-card {
            width: 100%;
            max-width: 400px; 
            border: none; 
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            border-radius: 10px;
        }
        .btn-custom {
            width: 100%; 
            padding: 10px;
            font-weight: bold;
        }
        .brand-title {
            color: #007bff;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="card login-card p-4">
        <div class="card-body">
            <h3 class="brand-title">Koreana Online Travel Reservation System</h3>
            <p class="text-center text-muted mb-4">Please login to continue</p>

			<%
				String email = (String)session.getAttribute("email");
				String role = (String)session.getAttribute("role");
				
				//redirect to home page if already logged in
				if(email != null) {
					if(role.equals("manager")) {
						response.sendRedirect("managerHome.jsp");
					}
					else if(role.equals("customerRepresentative")) {
						response.sendRedirect("customerRepresentativeHome.jsp");
					}
					else {
						response.sendRedirect("home.jsp");	
					}
				}
				
				String status = request.getParameter("status");
				if(status != null) {
					if(status.equals("false")) {
						out.print("Incorrect Login credentials!");
					}
					else {
						out.print("Some error occurred! Please try again.");
					}
				}
			%>
			<form action="login">
				<div class="form-group">
					<input type="email" class="form-control" name="username" placeholder="Username" required>
				</div>
				<div class="form-group">
	            	<input type="password" class="form-control" name="password" placeholder="Password" required>
	        	</div>
				<div class="mt-4">
                    <input type="submit" value="Login" class="btn btn-primary btn-custom"/>
                </div>
			</form>
		</div>

    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
</body>
</html>