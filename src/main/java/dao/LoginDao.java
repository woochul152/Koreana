package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Auctions;
import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	
	public Login login(String username, String password) {
	    Login login = new Login();
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", 
	            "root", "root");
	        
	        String sql = "SELECT username, password, role FROM Login WHERE username = ? AND password = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            login.setUsername(rs.getString("username"));
	            login.setPassword(rs.getString("password"));
	            login.setRole(rs.getString("role"));
	            con.close();
	            return login;
	        }
	        con.close();
	        return null;
	        
	    } catch (Exception e) {
	        System.out.println(e);
	        return null;
	    }
		
////		/*
////		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
////		 * Else, return null
////		 * The role depends on the type of the user, which has to be handled in the database
////		 * username, which is the email address of the user, is given as method parameter
////		 * password, which is the password of the user, is given as method parameter
////		 * Query to verify the username and password and fetch the role of the user, must be implemented
////		 */
////		
////		/*Sample data begins*/
//		Login login = new Login();
////		login.setRole("customerRepresentative");
////		login.setRole("manager");
//		login.setRole("customer");
//		return login;
////		/*Sample data ends*/
	}
	
	public String addUser(Login login) {
		
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", 
	            "root", "root");
	        
	        String sql = "INSERT INTO Login (username, password, role) VALUES (?, ?, ?)";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, login.getUsername());
	        pstmt.setString(2, login.getPassword());
	        pstmt.setString(3, login.getRole());
	        
	        int result = pstmt.executeUpdate();
	        con.close();
	        
	        if (result > 0) {
	            return "success";
	        } else {
	            return "failure";
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e);
	        return "failure";
	    }

//		/*
//		 * Query to insert a new record for user login must be implemented
//		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
//		 * The username and password from login can get accessed using getter methods in the "Login" model
//		 * e.g. getUsername() method will return the username encapsulated in login object
//		 * Return "success" on successful insertion of a new user
//		 * Return "failure" for an unsuccessful database operation
//		 */
//		
//		/*Sample data begins*/
//		return "success";
//		/*Sample data ends*/
	}

}
