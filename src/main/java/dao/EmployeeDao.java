package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Employee;
import model.FlightReservations;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {
		String url = "jdbc:mysql://localhost:3306/demo";
		String query = "INSERT INTO Employee (FirstName, LastName, Address, City, State, ZipCode, Email, SSN, IsManager, StartDate, HourlyRate) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String id = "root";
		String password = "root";

		try{
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
			ps.setDate(10, Date.valueOf(employee.getStartDate())); //이거 확실하지 않음
			ps.setFloat(11, employee.getHourlyRate());

			return "success";
		} catch (SQLException e){
			System.out.println(e);
			return "failure";
		}

		
		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
		// return "success";
		/*Sample data ends*/

	}

	public String editEmployee(Employee employee) {
		String url = "jdbc:mysql://localhost:3306/demo";
		String query = "UPDATE Employee " 
						+ "SET FirstName = ?, LastName = ?, Address = ?, City = ?, State = ?, "
						+ "ZipCode = ?, Email = ?, IsManager = ?, StartDate = ?, HourlyRate = ?, WHERE SSN = ? ";
		String id = "root";
		String password = "root";
		try{
			Connection con = DriverManager.getConnection(url, id, password);
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getAddress());
			ps.setString(4, employee.getCity());
			ps.setString(5, employee.getState());
			ps.setInt(6, employee.getZipCode());
			ps.setString(7, employee.getEmail());
			ps.setBoolean(8, employee.getIsManager());
			ps.setDate(9, Date.valueOf(employee.getStartDate())); //이거 확실하지 않음
			ps.setFloat(10, employee.getHourlyRate());
			ps.setString(11, employee.getSSN());

			return "success";
		} catch (SQLException e){
			System.out.println(e);
			return "failure";
		}
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		// return "success";
		/*Sample data ends*/
	}

	public String deleteEmployee(String SSN) {

		String url = "jdbc:mysql://localhost:3306/demo";
		String query = "DELETE FROM Employee WHERE SSN = ?";
		String id = "root";
		String password = "root";
		try{
			Connection con = DriverManager.getConnection(url, id, password);
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, SSN);

			return "success";
		} catch (SQLException e){
			System.out.println(e);
			return "failure";
		}

		/*
		 * SSN, which is the Employee's SSN which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		/*Sample data begins*/
		// return "success";
		/*Sample data ends*/

	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String query = "SELECT * FROM Employee";
		String id = "root";
		String password = "root";
		try{
			Connection con = DriverManager.getConnection(url, id, password);
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Employee employee = new Employee();
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("getAddress"));
				employee.setCity(rs.getString("City"));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setEmail(rs.getString("Email"));
				employee.setSSN(rs.getString("SSN"));
				employee.setIsManager(rs.getBoolean("IsManager"));
				//이거 확인 필
				employee.setStartDate(String.valueOf(rs.getDate("StartDate"))); // "YYYY-MM-DD"
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
				employees.add(employee);
			}
		} catch (SQLException e){
			System.out.println(e);
		}

		/*Sample data begins*/
		// for (int i = 0; i < 10; i++) {
		// 	Employee employee = new Employee();
		// 	employee.setEmail("shiyong@cs.sunysb.edu");
		// 	employee.setFirstName("Shiyong");
		// 	employee.setLastName("Lu");
		// 	employee.setAddress("123 Success Street");
		// 	employee.setCity("Stony Brook");
		// 	employee.setStartDate("2006-10-17");
		// 	employee.setState("NY");
		// 	employee.setZipCode(11790);
		// 	employee.setSSN("6314135555");
		// 	employee.setHourlyRate(100);
		// 	employee.setIsManager(true);
			
		// 	employees.add(employee);
		// }
		/*Sample data ends*/
		
		return employees;
	}

	public Employee getEmployee(String SSN) {

		/*
		 * The students code to fetch data from the database based on "SSN" will be written here
		 * SSN, which is the Employee's SSN who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		Employee employee = new Employee();
		

		String url = "jdbc:mysql://localhost:3306/demo";
		String query = "SELECT * FROM Employee WHERE ?";
		String id = "root";
		String password = "root";
		try{
			Connection con = DriverManager.getConnection(url, id, password);
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			ps.setString(1, SSN);
			if(rs.next()){
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("getAddress"));
				employee.setCity(rs.getString("City"));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setEmail(rs.getString("Email"));
				employee.setSSN(rs.getString("SSN"));
				employee.setIsManager(rs.getBoolean("IsManager"));
				//이거 확인 필
				employee.setStartDate(String.valueOf(rs.getDate("StartDate"))); // "YYYY-MM-DD"
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
			}
		} catch (SQLException e){
			System.out.println(e);
		}

		/*Sample data begins*/
		// employee.setEmail("shiyong@cs.sunysb.edu");
		// employee.setFirstName("Shiyong");
		// employee.setLastName("Lu");
		// employee.setAddress("123 Success Street");
		// employee.setCity("Stony Brook");
		// employee.setStartDate("2006-10-17");
		// employee.setState("NY");
		// employee.setZipCode(11790);
		// employee.setSSN("6314135555");
		// employee.setHourlyRate(100);
		// employee.setIsManager(true);
		/*Sample data ends*/
		
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String query = "SELECT E.SSN, E.FirstName, E.LastName, E.Address, E.City, E.State, " 
					+ "E.ZipCode, E.Email, E.IsManager, E.StartDate, E.HourlyRate, SUM(FR.TotalFare) AS Revenue "
					+ "FROM Employee E "
					+ "JOIN FlightReservations FR ON FR.RepSSN = E.SSN "
					+ "GROUP BY E.SSN";
		String id = "root";
		String password = "root";

		try{
			Connection con = DriverManager.getConnection(url, id, password);
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("getAddress"));
				employee.setCity(rs.getString("City"));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setEmail(rs.getString("Email"));
				employee.setSSN(rs.getString("SSN"));
				employee.setIsManager(rs.getBoolean("IsManager"));
				//이거 확인 필
				employee.setStartDate(String.valueOf(rs.getDate("StartDate"))); // "YYYY-MM-DD"
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
			}
		} catch (SQLException e){
			System.out.println(e);
		}

		/*Sample data begins*/
		// EmployeeID = SSN
		// employee.setSSN("6314135555");
		/*Sample data ends*/
		
		return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username"(email) will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID(SSN) is required to be returned as a String
		 */
		String url = "jdbc:mysql://localhost:3306/demo";
		String query = "SELECT SSN FROM Employee WHERE Email = ?";
		String id = "root";
		String password = "root";

		try{
			Connection con = DriverManager.getConnection(url, id, password);
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ps.setString(1, username);
			if(rs.next()){
				return rs.getString("SSN");
			}
		} catch (SQLException e){
			System.out.println(e);
		}
		return null;
	}

}
