package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.FlightReservations;

public class FlightReservationsDao {
    // JDBC
	static String url = "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&autoReconnect=true";
    static String id = "root";
    static String password = "root";
    
	//Jungu
	public List<FlightReservations> getReservations(int FlightNum, String airlineID, String CustomerName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get flight reservations based on FlightNum OR CustomerName passed
		 * Only one of the two strings will be set, either (FlightNum = 0 and airlineID="") or CustomerName = "" depending on query
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
			
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			FlightReservations reservation = new FlightReservations();
			
			reservation.setResrNo(111);
			reservation.setResrDate("2011-01-01");
			reservation.setTotalFare(150.22); 
			reservation.setBookingFee(10.12);
			reservation.setRepSSN("198498472");
			reservation.setFirstName("John");
			reservation.setLastName("Wick");
	
			reservations.add(reservation);
				
		}
		/*Sample data ends*/
						
		return reservations;
		
	}
	
	//Woochul
	public List<FlightReservations> getRevenueSummary(int FlightNum, String airlineID, String CustomerName,String destCity) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get flight reservations based on FlightNum OR CustomerName passed
		 * Only one of the two strings will be set, either (FlightNum = 0 and airlineID = "") or CustomerName = ""  
		 * or destCity = "" depending on query
		 */
		String query = "";
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
			
		
		String query_by_FlightNum = "SELECT R.ResrNo, R.Revenue " + 
									"FROM FlightReservation R " + 
									"JOIN Itinerary I ON R.ResrNo = I.ResrNo " + 
									"WHERE I.FlightNo = ? AND I.AirlineID = ?";								
		
		String query_by_CustomerName = "SELECT R.ResrNo, R.Revenue " + 
			    					   "FROM FlightReservation R " +
			    					   "JOIN Customer C ON R.AccountNo = C.AccountNo " +
			    					   "WHERE C.FirstName = ? OR C.LastName = ?";
		
		String query_by_destCity = "SELECT R.ResrNo, R.Revenue " + 
			    				   "FROM FlightReservation R " +
			    				   "JOIN Itinerary I ON R.ResrNo = I.ResrNo " +
			    				   "WHERE I.Arrival = ?";
		
		if(FlightNum != 0 && airlineID.length() != 0){
			query = query_by_FlightNum;
		} else if(CustomerName.length() != 0) {
			query = query_by_CustomerName;
		} else if (destCity.length() != 0) {
			query = query_by_destCity;
		}

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);

	        PreparedStatement ps = con.prepareStatement(query);

	        
	        if (query.length() == query_by_FlightNum.length()) {
	            ps.setInt(1, FlightNum);
	            ps.setString(2, airlineID);
	        }
	        else if (query.length() == query_by_CustomerName.length()) {
	            ps.setString(1, CustomerName);
	            ps.setString(2, CustomerName);
	        }
	        else if (query.length() == query_by_destCity.length()) {
	            ps.setString(1, destCity);
	        } else {
	        	new IllegalArgumentException("Invalid input for revenue summary query.");
	        }

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            FlightReservations reservation = new FlightReservations();
	            reservation.setResrNo(rs.getInt("ResrNo"));
	            reservation.setRevenue(rs.getDouble("Revenue"));
	            reservations.add(reservation);
	        }

	    } catch (SQLException e) {
	        System.out.println(e);
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver not found: " + e);
	    }

	    return reservations;
	}
	
	
	//Woochul
	public List<FlightReservations> getPassengerList(int FlightNum, String AirlineID) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get passenger list given flight number and Airline ID
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
		
		String query = "SELECT R.PassengerID, C.FirstName, C.LastName " + 
					   "FROM FlightReservation R " +
					   "JOIN Customer C ON R.PassengerID = C.AccountNo " + 
					   "JOIN Itinerary I ON R.ResrNo = I.ResrNo " + 
 					   "WHERE I.FlightNo = ? AND I.AirlineID = ? ";
		
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url, id, password);

	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, FlightNum);
	        ps.setString(2, AirlineID);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            FlightReservations reservation = new FlightReservations();

	            reservation.setPassengerID(rs.getInt("PassengerID"));
	            reservation.setFirstName(rs.getString("FirstName"));
	            reservation.setLastName(rs.getString("LastName"));
	            reservations.add(reservation);
	        }

	    } catch (SQLException e) {
	        System.out.println(e);
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver not found: " + e);
	    }
						
		return reservations;
		
	}
	
	//Jungu
	public List<FlightReservations> getCurrentReservations(int accountNo) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get current flight reservations based on accountno 
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
			
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			FlightReservations reservation = new FlightReservations();
			
			reservation.setResrNo(111);
			reservation.setResrDate("2011-01-01");
			reservation.setTotalFare(150.22); 
			reservation.setBookingFee(10.12);
			reservation.setRepSSN("198498472");
			reservation.setAccountNo(accountNo);
	
			reservations.add(reservation);
				
		}
		/*Sample data ends*/
						
		return reservations;
		
	}

	//Jungu
	public List<FlightReservations> getAllReservations(int accountNo) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get all flight reservations based on accountno 
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
			
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			FlightReservations reservation = new FlightReservations();
			
			reservation.setResrNo(111);
			reservation.setResrDate("2011-01-01");
			reservation.setTotalFare(150.22); 
			reservation.setBookingFee(10.12);
			reservation.setRepSSN("198498472");
			reservation.setAccountNo(accountNo);
	
			reservations.add(reservation);
				
		}
		/*Sample data ends*/
						
		return reservations;
		
	}
	
	//Jungu
	public String cancelReservation(int resrNo) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get cancel reservations based on resrNo
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
					
		return "success";
		
	}
}