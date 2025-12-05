package dao;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import model.Auctions;
import model.BookReservation;

public class BookReservationDao {

	public String bookOneWayRoundTripReservation(BookReservation bookRes) {
		Connection con = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "root");
	        con.setAutoCommit(false);  // Transaction
	        
	        // 0. Get AccountNo from PassEmail
	        String sql0 = "SELECT AccountNo FROM Customer WHERE Email = ?";
	        PreparedStatement pstmt0 = con.prepareStatement(sql0);
	        pstmt0.setString(1, bookRes.getPassEmail());
	        ResultSet rs0 = pstmt0.executeQuery();
	        int accountNo = -1;
	        if (rs0.next()) {
	            accountNo = rs0.getInt("AccountNo");
	        }

	        if (accountNo == -1) {
	            con.rollback();
	            return "failure";  // No customer found
	        }

	        
	        // 1. INSERT FlightReservation - get new ResrNo
	        String sql1 = "INSERT INTO FlightReservation (ResrDate, TotalFare, BookingFee, Revenue, RepSSN, PassengerID, AccountNo) VALUES (CURDATE(), ?, ?, ?, ?, ?, ?)";
	        PreparedStatement pstmt1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
	        pstmt1.setDouble(1, 350.0);  // TotalFare - compute from flights
	        pstmt1.setDouble(2, 20.0);   // BookingFee - fixed or compute
	        pstmt1.setDouble(3, 330.0);  // Revenue
	        if (bookRes.getRepSSN() != null && !bookRes.getRepSSN().isEmpty()) {pstmt1.setString(4, bookRes.getRepSSN());} 
	        else {pstmt1.setNull(4, java.sql.Types.CHAR);}
	        pstmt1.setInt(5, accountNo);
	        pstmt1.setInt(6, accountNo);
	        pstmt1.executeUpdate();
	        
	        ResultSet rs = pstmt1.getGeneratedKeys();
	        int resrNo = 0;
	        if (rs.next()) {
	            resrNo = rs.getInt(1);
	        }
	        
	        // 2. INSERT Itinerary for FlightNum1 (outbound)
	        String sql2 = "INSERT INTO Itinerary (ResrNo, AirlineID, FlightNo, Departure, Arrival, DepTime, ArrTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement pstmt2 = con.prepareStatement(sql2);
	        pstmt2.setInt(1, resrNo);
	        pstmt2.setString(2, bookRes.getAirlineID());
	        pstmt2.setInt(3, bookRes.getFlightNum1());
	        pstmt2.setString(4, bookRes.getDepartureAirport());
	        pstmt2.setString(5, bookRes.getArrivalAirport());
	        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = inputFormat.parse(bookRes.getDepartureDate());
	        String mysqlDate = outputFormat.format(date);

	        pstmt2.setString(6, mysqlDate + " 06:00:00");  // "1999-11-11 06:00:00"
	        pstmt2.setString(7, mysqlDate + " 09:00:00");
	        pstmt2.executeUpdate();
	        
	        // 3. UPDATE Flight NumReservations +1 for FlightNum1
	        String sql3 = "UPDATE Flight SET NumReservations = NumReservations + 1 WHERE AirlineID = ? AND FlightNo = ?";
	        PreparedStatement pstmt3 = con.prepareStatement(sql3);
	        pstmt3.setString(1, bookRes.getAirlineID());
	        pstmt3.setInt(2, bookRes.getFlightNum1());
	        pstmt3.executeUpdate();
	        
	        // 4. If ROUND TRIP - add return flight
	        if ("roundtrip".equalsIgnoreCase(bookRes.getTypeOfTrip())) {
	            PreparedStatement pstmt4 = con.prepareStatement(sql2);  // Reuse Itinerary SQL
	            pstmt4.setInt(1, resrNo);
	            pstmt4.setString(2, bookRes.getAirlineID());
	            pstmt4.setInt(3, bookRes.getFlightNum2());
	            pstmt4.setString(4, bookRes.getArrivalAirport());  // Return: ARR→DEP
	            pstmt4.setString(5, bookRes.getDepartureAirport());
		        Date date2 = inputFormat.parse(bookRes.getReturnDate());
		        String mysqlDate2 = outputFormat.format(date);
	            pstmt4.setString(6, mysqlDate2 + " 10:00:00");
	            pstmt4.setString(7, mysqlDate2 + " 13:00:00");

	            pstmt4.executeUpdate();
	            
	            // Update FlightNum2 reservations
	            PreparedStatement pstmt5 = con.prepareStatement(sql3);
	            pstmt5.setString(1, bookRes.getAirlineID());
	            pstmt5.setInt(2, bookRes.getFlightNum2());
	            pstmt5.executeUpdate();
	        }
	        
	        con.commit();
	        return "success";
	        
	    } catch (Exception e) {
	        try { con.rollback(); } catch (Exception ex) {}
	        System.out.println(e);
	        return "failure";
	    } finally {
	        try { con.setAutoCommit(true); con.close(); } catch (Exception ex) {}
	    }
	}

	
//	public String bookOneWayRoundTripReservation(BookReservation bookRes) {
//		
//		/*
//		 * The students code to fetch data from the database will be written here
//		 * Query to book reservations based on bookRes object passed
//		 * repSSN will be set depending on who booked the reservation
//		 * Use getters to fetch the data from the object
//		 * The sample code returns "success" by default.
//		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
//		 */
//					
//		return "success";
//		
//	}
	
	public String bookMultiCityReservation(BookReservation bookRes) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to book reservations based on bookRes object passed
		 * repSSN will be set depending on who booked the reservation
		 * Use getters to fetch the data from the object
		 * DepartureAirport1, ArrivalAirport1, DepartureAirport2, ArrivalAirport2, Trip1Date, Trip2Date are the attributes to use here
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
					
		return "success";
		
	}
}
