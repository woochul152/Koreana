package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.Itinerary;

public class ItineraryDao {
	
	//Jungu
	public List<Itinerary> getItineraryForReservation(int resrNo) {
	    List<Itinerary> its = new ArrayList<Itinerary>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
	            "root", "root");
	        
	        String sql = "SELECT * FROM Itinerary WHERE ResrNo = ? ORDER BY DepTime ASC";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, resrNo);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Itinerary it = new Itinerary();
	            it.setResrNo(rs.getInt("ResrNo"));
	            it.setAirlineID(rs.getString("AirlineID"));
	            it.setFlightNo(rs.getInt("FlightNo"));
	            it.setDeparture(rs.getString("Departure"));
	            it.setArrival(rs.getString("Arrival"));
	            it.setDepTime(rs.getString("DepTime"));
	            it.setArrTime(rs.getString("ArrTime"));
	            it.setActualDepTime(rs.getString("ActualDepTime"));
	            it.setActualArrTime(rs.getString("ActualArrTime"));
	            its.add(it);
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    return its;
	}
}
