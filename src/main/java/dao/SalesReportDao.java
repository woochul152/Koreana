package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.SalesReport;

public class SalesReportDao {
	
	static String url = "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&autoReconnect=true";
    static String id = "root";
    static String password = "root";
    
	public List<SalesReport> getSalesReport(String month, String year) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get sales report for a particular month must be implemented using month and year passed
		 */
		
		
		List<SalesReport> sales = new ArrayList<SalesReport>();
			

	    String query = 
	        "SELECT R.ResrNo, R.ResrDate, R.TotalFare, R.BookingFee, " +
	        "       R.RepSSN, E.firstName AS FirstName, E.lastName AS LastName " +
	        "FROM FlightReservation R " +
	        "JOIN Employee E ON R.RepSSN = E.SSN " +
	        "WHERE MONTH(R.ResrDate) = ? AND YEAR(R.ResrDate) = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);
	        PreparedStatement ps = con.prepareStatement(query);
	        //Since the data type of ResrDate is DATE, 
	        ps.setInt(1, Integer.parseInt(month));    
	        ps.setInt(2, Integer.parseInt(year));   
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            SalesReport sale = new SalesReport();
	            sale.setResrNo(rs.getInt("ResrNo"));
	            sale.setResrDate(String.valueOf(rs.getDate("ResrDate")));
	            sale.setTotalFare(rs.getDouble("TotalFare"));
	            sale.setBookingFee(rs.getDouble("BookingFee"));
	            sale.setRepSSN(rs.getString("RepSSN"));
	            sale.setFirstName(rs.getString("FirstName"));
	            sale.setLastName(rs.getString("LastName"));
	            sales.add(sale);
	        }

	    } catch (SQLException e) {
	        System.out.println(e);
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver not found: " + e);
	    }
						
		return sales;
		
	}

}