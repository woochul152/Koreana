package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Auctions;
import model.FlightReservations;

public class FlightReservationsDao {
    // JDBC
	static String url = "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&autoReconnect=true";
    static String id = "root";
    static String password = "root";
    
	//Jungu
    public List<FlightReservations> getReservations(int FlightNum, String airlineID, String CustomerName) {
        List<FlightReservations> reservations = new ArrayList<FlightReservations>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                "root", "root");

            String sql;
            PreparedStatement pstmt;

            if (FlightNum != 0 && airlineID != null && !airlineID.isEmpty()) {
                // Search by flight
                sql = "SELECT fr.*, c.FirstName, c.LastName " +
                      "FROM FlightReservation fr " +
                      "JOIN Itinerary i ON fr.ResrNo = i.ResrNo " +
                      "JOIN Customer c ON fr.AccountNo = c.AccountNo " +
                      "WHERE i.FlightNo = ? AND i.AirlineID = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, FlightNum);
                pstmt.setString(2, airlineID);
            } else {
                // Search by customer name (FirstName + LastName contains)
                sql = "SELECT fr.*, c.FirstName, c.LastName " +
                      "FROM FlightReservation fr " +
                      "JOIN Customer c ON fr.AccountNo = c.AccountNo " +
                      "WHERE CONCAT(c.FirstName, ' ', c.LastName) LIKE ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, "%" + CustomerName + "%");
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FlightReservations fr = new FlightReservations();
                fr.setResrNo(rs.getInt("ResrNo"));
                fr.setResrDate(rs.getString("ResrDate"));
                fr.setTotalFare(rs.getDouble("TotalFare"));
                fr.setBookingFee(rs.getDouble("BookingFee"));
                fr.setRevenue(rs.getDouble("Revenue"));
                fr.setAccountNo(rs.getInt("AccountNo"));
                fr.setFirstName(rs.getString("FirstName"));
                fr.setLastName(rs.getString("LastName"));
                reservations.add(fr);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

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
	    List<FlightReservations> reservations = new ArrayList<FlightReservations>();
	
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
	            "root", "root");
	        
	        String sql = "SELECT fr.*, c.FirstName, c.LastName " +
	                "FROM FlightReservation fr " +
	                "JOIN Customer c ON fr.AccountNo = c.AccountNo " +
	                "JOIN Itinerary i ON fr.ResrNo = i.ResrNo " +
	                "WHERE fr.AccountNo = ? " +
	                "AND i.DepTime > NOW() " +
	                "GROUP BY fr.ResrNo " +
	                "ORDER BY MIN(i.DepTime) ASC";
	        
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, accountNo);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            FlightReservations reservation = new FlightReservations();
	            reservation.setResrNo(rs.getInt("ResrNo"));
	            reservation.setResrDate(rs.getString("ResrDate"));
	            reservation.setTotalFare(rs.getDouble("TotalFare"));
	            reservation.setBookingFee(rs.getDouble("BookingFee"));
	            reservation.setRevenue(rs.getDouble("Revenue"));
	            reservation.setRepSSN(rs.getString("RepSSN"));
	            reservation.setAccountNo(rs.getInt("AccountNo"));
	            reservation.setFirstName(rs.getString("FirstName"));
	            reservation.setLastName(rs.getString("LastName"));
	            reservations.add(reservation);
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    return reservations;
}

	//Jungu
	public List<FlightReservations> getAllReservations(int accountNo) {
	    List<FlightReservations> reservations = new ArrayList<FlightReservations>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
	            "root", "root");
	        
	        String sql = "SELECT fr.*, c.FirstName, c.LastName, e.firstName as RepFirstName, e.lastName as RepLastName " +
	                    "FROM FlightReservation fr " +
	                    "JOIN Customer c ON fr.AccountNo = c.AccountNo " +
	                    "LEFT JOIN Employee e ON fr.RepSSN = e.SSN " +
	                    "WHERE fr.AccountNo = ? " +
	                    "ORDER BY fr.ResrDate DESC";
	        
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, accountNo);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            FlightReservations reservation = new FlightReservations();
	            reservation.setResrNo(rs.getInt("ResrNo"));
	            reservation.setResrDate(rs.getString("ResrDate"));
	            reservation.setTotalFare(rs.getDouble("TotalFare"));
	            reservation.setBookingFee(rs.getDouble("BookingFee"));
	            reservation.setRevenue(rs.getDouble("Revenue"));
	            reservation.setRepSSN(rs.getString("RepSSN"));
	            reservation.setAccountNo(rs.getInt("AccountNo"));
	            reservation.setFirstName(rs.getString("FirstName"));
	            reservation.setLastName(rs.getString("LastName"));
	            // Optional rep name if FlightReservations has fields
	            // reservation.setRepFirstName(rs.getString("RepFirstName"));
	            // reservation.setRepLastName(rs.getString("RepLastName"));
	            reservations.add(reservation);
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    return reservations;
	}
	
	//Jungu
	public String cancelReservation(int resrNo) {
	    Connection con = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
	            "root", "root");
	        con.setAutoCommit(false);
	        
	        // Verify reservation exists first
	        String sqlCheck = "SELECT ResrNo FROM FlightReservation WHERE ResrNo = ?";
	        PreparedStatement pstmtCheck = con.prepareStatement(sqlCheck);
	        pstmtCheck.setInt(1, resrNo);
	        ResultSet rs = pstmtCheck.executeQuery();
	        
	        if (!rs.next()) {
	            con.rollback();
	            return "failure";  // Reservation not found
	        }
	        
	        // 1. DELETE Itinerary (CASCADE will handle FKs)
	        String sql1 = "DELETE FROM Itinerary WHERE ResrNo = ?";
	        PreparedStatement pstmt1 = con.prepareStatement(sql1);
	        pstmt1.setInt(1, resrNo);
	        int deletedItineraries = pstmt1.executeUpdate();
	        
	        // 2. Decrement Flight reservations
	        String sql2 = "UPDATE Flight f " +
	                     "JOIN Itinerary i ON f.AirlineID = i.AirlineID AND f.FlightNo = i.FlightNo " +
	                     "SET f.NumReservations = GREATEST(0, f.NumReservations - 1) " +
	                     "WHERE i.ResrNo = ?";
	        PreparedStatement pstmt2 = con.prepareStatement(sql2);
	        pstmt2.setInt(1, resrNo);
	        pstmt2.executeUpdate();
	        
	        // 3. DELETE FlightReservation (CASCADE deletes rest)
	        String sql3 = "DELETE FROM FlightReservation WHERE ResrNo = ?";
	        PreparedStatement pstmt3 = con.prepareStatement(sql3);
	        pstmt3.setInt(1, resrNo);
	        int deletedRes = pstmt3.executeUpdate();
	        
	        con.commit();
	        return deletedRes > 0 ? "success" : "failure";
	        
	    } catch (Exception e) {
	        try { con.rollback(); } catch (Exception ex) {}
	        System.out.println(e);
	        return "failure";
	    } finally {
	        try { con.setAutoCommit(true); con.close(); } catch (Exception ex) {}
	    }
	}
}