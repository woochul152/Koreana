package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mysql.cj.jdbc.Driver;

import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	
	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */
		
		/*Sample data begins*/
		Login login = new Login();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/koreana","root","password");
			String query  = "select role from users where username = ? and password = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			java.sql.ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				login.setRole(rs.getString("role"));
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

//		login.setRole("customerRepresentative");
//		login.setRole("manager");
		// login.setRole("customer");
		return login;
		/*Sample data ends*/
		
	}
	
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */

		Login newLogin = login;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/koreana","root","password");
			String query  = "insert into users (username, password, role) values (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, newLogin.getUsername());
			ps.setString(2, newLogin.getPassword());
			ps.setString(3, newLogin.getRole());
			int result = ps.executeUpdate();
			if(result > 0) {
				return "success";
			} else {
				return "failure";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "failure";
		}

		
		/*Sample data begins*/
		// return "success";
		/*Sample data ends*/
	}

}
