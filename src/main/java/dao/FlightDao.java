package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Flight;

public class FlightDao {
	
	public List<Flight> getAllFlights() {
		/* Get list of all flights, code goes here
		 */
		List<Flight> flights = new ArrayList<Flight>();
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String query = "SELECT * FROM Flight";
		String id = "root";
		String password = "root";
		try{
			Connection con = DriverManager.getConnection(url, id, password);
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("NoOfSeats"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("NumReservations"));
				flights.add(flight);
			}
		} catch (SQLException e){
			System.out.println(e);
		}

		/*Sample data ends*/
		
		return flights;
	}


	public List<Flight> mostActiveFlights() {
		
		/* Get list of most active flights, code goes here
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String id = "root";
		String password = "root";

		String query = "SELECT * FROM Flight";

		/*Sample data ends*/
		
		return flights;
	}
	
	public List<Flight> getFlightsForAirport(String airport) {
		
		/*
		 * Code here to get flights given an airport
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
		for (int i = 0; i < 5; i++) {
			Flight flight = new Flight();
			flight.setAirlineID("AA");
			flight.setFlightNo(111);
			flight.setNumOfSeats(100);
			flight.setDaysOperating("1010100");
			flight.setMinLengthOfStay(1);
			flight.setMaxLengthOfStay(30);
			flights.add(flight);			
		}
		/*Sample data ends*/
		
		return flights;
	}
	public List<Flight> getOnTimeFlights() {
		
		/*
		 * Code here to get on time flights
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
		for (int i = 0; i < 5; i++) {
			Flight flight = new Flight();
			flight.setAirlineID("AA");
			flight.setFlightNo(111);
			flight.setNumOfSeats(100);
			flight.setDaysOperating("1010100");
			flight.setMinLengthOfStay(1);
			flight.setMaxLengthOfStay(30);
			flights.add(flight);			
		}
		/*Sample data ends*/
		
		return flights;
	}
	public List<Flight> getDelayedFlights() {
		
		/*
		 * Code here to get delayed flights
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
		for (int i = 0; i < 5; i++) {
			Flight flight = new Flight();
			flight.setAirlineID("AA");
			flight.setFlightNo(111);
			flight.setNumOfSeats(100);
			flight.setDaysOperating("1010100");
			flight.setMinLengthOfStay(1);
			flight.setMaxLengthOfStay(30);
			flights.add(flight);			
		}
		/*Sample data ends*/
		
		return flights;
	}
	
	//준구님
	public List<Flight> getCustomerFlightSuggestions(int accountNo) {
		
		/* Get list of suggested flights depending on customer's accountNo passed
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
		for (int i = 0; i < 5; i++) {
			Flight flight = new Flight();
			flight.setAirlineID("AA");
			flight.setFlightNo(111);
			flight.setNumReservations(30);
			flights.add(flight);			
		}
		/*Sample data ends*/
		
		return flights;
	}

	//준구님
	public List<Flight> getBestSellingFlights() {
		
		/* Get list of best selling flights
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
		for (int i = 0; i < 5; i++) {
			Flight flight = new Flight();
			flight.setAirlineID("AA");
			flight.setFlightNo(111);
			flight.setNumReservations(30);
			flights.add(flight);			
		}
		/*Sample data ends*/
		
		return flights;
	}
}