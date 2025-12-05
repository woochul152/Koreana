package dao;

import java.lang.classfile.CustomAttribute;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;


public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers() {
		/*
		 * This method fetches one or more customers and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		
		/*Sample data begins*/
		/*
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setAccountNo(111);
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Shiyong");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("shiyong@cs.sunysb.edu");
			customer.setZipCode(11790);
//			customer.setTelephone("5166328959");
			customer.setCreditCard("1234567812345678");
			customer.setRating(1);
			customers.add(customer);			
		}*/

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartshop","root","password");

			String sql = "SELECT * FROM customer";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Customer customer = new Customer();
				customer.setAccountNo(rs.getInt("accountNo"));
				customer.setAddress(rs.getString("address"));
				customer.setLastName(rs.getString("lastName"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setEmail(rs.getString("email"));
				customer.setZipCode(rs.getInt("zipCode"));
				customer.setCreditCard(rs.getString("creditCard"));
				customer.setRating(rs.getInt("rating"));
				customers.add(customer);	
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		/*Sample data ends*/
		
		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */

		Customer customer = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartshop","root","password");

			String sql = "SELECT c.* FROM customer c JOIN orders o ON c.accountNo = o.accountNo GROUP BY c.accountNo ORDER BY SUM(o.totalAmount) DESC LIMIT 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				customer = new Customer();
				customer.setAccountNo(rs.getInt("accountNo"));
				customer.setAddress(rs.getString("address"));
				customer.setLastName(rs.getString("lastName"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setEmail(rs.getString("email"));
				customer.setZipCode(rs.getInt("zipCode"));
				customer.setCreditCard(rs.getString("creditCard"));
				customer.setRating(rs.getInt("rating"));
				return customer;	
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		/*Sample data begins*/
		/*
		Customer customer = new Customer();
		// Customer ID = Account Number
		customer.setAccountNo(111);
		customer.setLastName("Lu");
		customer.setFirstName("Shiyong");
		*/
		/*Sample data ends*/
	
		return customer;
		
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartshop","root","password");

			String sql = "SELECT accountNo, firstName, lastName, address, city, state, zipCode FROM customer";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Customer customer = new Customer();
				customer.setAccountNo(rs.getInt("accountNo"));
				customer.setAddress(rs.getString("address"));
				customer.setLastName(rs.getString("lastName"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setZipCode(rs.getInt("zipCode"));
				customers.add(customer);	
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		/*Sample data begins*/
		/* for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Shiyong");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("shiyong@cs.sunysb.edu");
			customer.setZipCode(11790);
			customers.add(customer);			
		} 
		*/
		/*Sample data ends*/
		
		return customers;
	}

	public Customer getCustomer(int accountNo) {

		/*
		 * This method fetches the customer details and returns it
		 * accountNo, which is the Customer's accountNo who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		
		Customer customer = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartshop","root","password");

			String sql = "SELECT * FROM customer WHERE accountNo = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountNo);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				customer = new Customer();
				customer.setAccountNo(rs.getInt("accountNo"));
				customer.setAddress(rs.getString("address"));
				customer.setLastName(rs.getString("lastName"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setEmail(rs.getString("email"));
				customer.setZipCode(rs.getInt("zipCode"));
				customer.setCreditCard(rs.getString("creditCard"));
				customer.setRating(rs.getInt("rating"));
				return customer;	
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		/*Sample data begins*/
		/* 
		Customer customer = new Customer();
		customer.setAccountNo(111);
		customer.setAddress("123 Success Street");
		customer.setLastName("Lu");
		customer.setFirstName("Shiyong");
		customer.setCity("Stony Brook");
		customer.setState("NY");
		customer.setEmail("shiyong@cs.sunysb.edu");
		customer.setZipCode(11790);
		customer.setCreditCard("1234567812345678");
		customer.setRating(1);
		 */
		/*Sample data ends*/
		
		return customer;
	}
	
	public String deleteCustomer(int accountNo) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * accountNo, which is the Customer's accountNo who's details have to be deleted, is given as method parameter
		 */

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartshop","root","password");

			String sql = "DELETE FROM customer WHERE accountNo = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountNo);
			int rowsAffected = ps.executeUpdate();

			if(rowsAffected > 0) {
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


	public int getCustomerID(String emailaddress) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID(accountNo) is required to be returned as a String
		 */

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartshop","root","password");
			String sql = "SELECT accountNo FROM customer WHERE email = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, emailaddress);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("accountNo");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartshop","root","password");

			String sql = "INSERT INTO customer (firstName, lastName, address, city, state, zipCode, email, creditCard, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getAddress());
			ps.setString(4, customer.getCity());
			ps.setString(5, customer.getState());
			ps.setInt(6, customer.getZipCode());
			ps.setString(7, customer.getEmail());
			ps.setString(8, customer.getCreditCard());
			ps.setInt(9, customer.getRating());
			int rowsAffected = ps.executeUpdate();

			if(rowsAffected > 0) {
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

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartshop","root","password");
			String sql = "UPDATE customer SET firstName = ?, lastName = ?, address = ?, city = ?, state = ?, zipCode = ?, email = ?, creditCard = ?, rating = ? WHERE accountNo = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getAddress());
			ps.setString(4, customer.getCity());
			ps.setString(5, customer.getState());
			ps.setInt(6, customer.getZipCode());
			ps.setString(7, customer.getEmail());
			ps.setString(8, customer.getCreditCard());
			ps.setInt(9, customer.getRating());
			ps.setInt(10, customer.getAccountNo());
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
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
