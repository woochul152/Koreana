package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Auctions;

public class AuctionsDao {
	
	public List<Auctions> getLatestBid(int AccountNo, String AirlineID, int FlightNo, String SeatClass) {

		/*
		 * This method fetches the latest auction details and returns it
		 * using method parameters given, find the latest bid
		 * The students code to fetch data from the database will be written here
		 * The Auctions record is required to be encapsulated as a "Auctions" class object
		 */
		List<Auctions> auctions = new ArrayList<Auctions>();
		/*Sample data begins*/
		Auctions auction = new Auctions();
		auction.setAccountNo(AccountNo);
		auction.setAirlineID(AirlineID);
		auction.setFlightNo(FlightNo);
		auction.setSeatClass(SeatClass);
		auction.setAccepted(true);
		auction.setDate("2019-01-01");
		auction.setNYOP(500);
		auctions.add(auction);
		/*Sample data ends*/
		
		return auctions;
	}
	
	public List<Auctions> getAllBids(int AccountNo, String AirlineID, int FlightNo, String SeatClass) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get all bids given the parameters
		 */
		
		List<Auctions> auctions = new ArrayList<Auctions>();
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo                ?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Auctions");
			
			while(rs.next()) {
			    Auctions auction = new Auctions();
			    auction.setAccountNo(rs.getInt("AccountNo"));
			    auction.setAirlineID(rs.getString("AirlineID"));
			    auction.setFlightNo(rs.getInt("FlightNo"));
			    auction.setSeatClass(rs.getString("SeatClass"));
			    auction.setDate(rs.getString("Date"));
			    auction.setNYOP(rs.getDouble("NYOP"));
			    auction.setAccepted(rs.getBoolean("Accepted"));
			    auctions.add(auction);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			Auctions auction = new Auctions();
//			auction.setAccountNo(AccountNo);
//			auction.setAirlineID(AirlineID);
//			auction.setFlightNo(FlightNo);
//			auction.setSeatClass(SeatClass);
//			auction.setAccepted(true);
//			auction.setDate("2019-01-01");
//			auction.setNYOP(500);
//	
//			auctions.add(auction);
//				
//		}
//		/*Sample data ends*/
								
		return auctions;
		
	}
}
