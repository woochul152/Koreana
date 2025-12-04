package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Employee;


public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
    static String url = "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&autoReconnect=true";
    static String id = "root";
    static String password = "root";
    
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */

	    String query = "INSERT INTO Employee (FirstName, LastName, Address, City, State, ZipCode, Email, SSN, IsManager, StartDate, HourlyRate) "
	                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);
	        PreparedStatement ps = con.prepareStatement(query);
	        
	        ps.setString(1, employee.getFirstName());
	        ps.setString(2, employee.getLastName());
	        ps.setString(3, employee.getAddress());
	        ps.setString(4, employee.getCity());
	        ps.setString(5, employee.getState());
	        ps.setInt(6, employee.getZipCode());
	        ps.setString(7, employee.getEmail());
	        ps.setString(8, employee.getSSN());
	        ps.setBoolean(9, employee.getIsManager());
	        ps.setDate(10, java.sql.Date.valueOf(employee.getStartDate()));
	        ps.setFloat(11, employee.getHourlyRate());

	        int update = ps.executeUpdate();

	        if (update == 1) {
	            return "success";
	        } else {
	            return "failure";
	        }

	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	        return "failure";
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver not found: " + e.getMessage());
	        return "failure";
	    }
	}

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */

	    String query = "UPDATE Employee SET "
	                 + "FirstName = ?, "
	                 + "LastName = ?, "
	                 + "Address = ?, "
	                 + "City = ?, "
	                 + "State = ?, "
	                 + "ZipCode = ?, "
	                 + "Email = ?, "
	                 + "IsManager = ?, "
	                 + "StartDate = ?, "
	                 + "HourlyRate = ? "
	                 + "WHERE SSN = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);

	        PreparedStatement ps = con.prepareStatement(query);

	        // Overwrite the previous info
	        ps.setString(1, employee.getFirstName());
	        ps.setString(2, employee.getLastName());
	        ps.setString(3, employee.getAddress());
	        ps.setString(4, employee.getCity());
	        ps.setString(5, employee.getState());
	        ps.setInt(6, employee.getZipCode());
	        ps.setString(7, employee.getEmail());
	        ps.setBoolean(8, employee.getIsManager());
	        ps.setDate(9, java.sql.Date.valueOf(employee.getStartDate()));
	        ps.setFloat(10, employee.getHourlyRate());
	        
			//Search employee by SSN
	        ps.setString(11, employee.getSSN());

	        int update = ps.executeUpdate();

	        if (update == 1) return "success";
	        else return "failure";

	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	        return "failure";
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver not found: " + e.getMessage());
	        return "failure";
	    }
	}

	public String deleteEmployee(String SSN) {
		/*
		 * SSN, which is the Employee's SSN which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
	    String query = "DELETE FROM Employee WHERE SSN = ?";
	    
	    // return a string SSN, if not exist, then return null. 

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, SSN);

	        int update = ps.executeUpdate();
	        
	        if (update == 1) {
	            return "success";
	        } else {
	            return "failure";
	        }

	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	        return "failure";
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver not found: " + e.getMessage());
	        return "failure";
	    }	    
	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		
		String query = "SELECT * FROM Employee";
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Employee employee = new Employee();
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("Address"));
				employee.setCity(rs.getString("City"));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setEmail(rs.getString("Email"));
				employee.setSSN(rs.getString("SSN"));
				employee.setIsManager(rs.getBoolean("IsManager"));
				employee.setStartDate(String.valueOf(rs.getDate("StartDate")));
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
				employees.add(employee);
			}
		} catch (SQLException e){
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver not found: " + e);
		}
	return employees;
	
	}

	public Employee getEmployee(String SSN) {

		/*
		 * The students code to fetch data from the database based on "SSN" will be written here
		 * SSN, which is the Employee's SSN who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

	    Employee employee = new Employee();

	    String query = "SELECT * FROM Employee WHERE SSN = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);

	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, SSN);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            employee.setFirstName(rs.getString("FirstName"));
	            employee.setLastName(rs.getString("LastName"));
	            employee.setAddress(rs.getString("Address"));
	            employee.setCity(rs.getString("City"));
	            employee.setState(rs.getString("State"));
	            employee.setZipCode(rs.getInt("ZipCode"));
	            employee.setEmail(rs.getString("Email"));
	            employee.setSSN(rs.getString("SSN"));
	            employee.setIsManager(rs.getBoolean("IsManager"));
	            employee.setStartDate(String.valueOf(rs.getDate("StartDate")));
	            employee.setHourlyRate(rs.getFloat("HourlyRate"));
	        }

	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver not found: " + e.getMessage());
	    }

	    return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		
	    String query =
	        "SELECT E.* " +
	        "FROM Employee E " +
	        "JOIN Reservation R ON E.SSN = R.RepSSN " +
	        "GROUP BY E.SSN " +
	        "HAVING SUM(R.TotalFare) = ( " +
	        "   SELECT MAX(total) " +
	        "   FROM ( " +
	        "       SELECT SUM(R2.TotalFare) AS total " +
	        "       FROM Reservation R2 " +
	        "       GROUP BY R2.RepSSN " +
	        "   ) AS t " +
	        ");";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);

	        PreparedStatement ps = con.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            employee.setFirstName(rs.getString("FirstName"));
	            employee.setLastName(rs.getString("LastName"));
	            employee.setAddress(rs.getString("Address"));
	            employee.setCity(rs.getString("City"));
	            employee.setState(rs.getString("State"));
	            employee.setZipCode(rs.getInt("ZipCode"));
	            employee.setEmail(rs.getString("Email"));
	            employee.setSSN(rs.getString("SSN"));
	            employee.setIsManager(rs.getBoolean("IsManager"));
	            employee.setStartDate(String.valueOf(rs.getDate("StartDate")));
	            employee.setHourlyRate(rs.getFloat("HourlyRate"));
	        }

	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver Not Found: " + e.getMessage());
	    }

	    return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username"(email) will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID(SSN) is required to be returned as a String
		 */

	    String query = "SELECT SSN FROM Employee WHERE Email = ?";
	    
	    // return a string SSN, if not exist, then return null. 
	    String ssn = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, username);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            ssn = rs.getString("SSN");
	        }

	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver not found: " + e.getMessage());
	    }

	    return ssn;
	}

}