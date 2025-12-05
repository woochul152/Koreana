package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import model.Flight;

public class FlightDao {

	static String url = "jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&autoReconnect=true";
	static String id = "root";
	static String password = "root";

	public List<Flight> getAllFlights() {
		/*
		 * Get list of all flights, code goes here
		 */
		List<Flight> flights = new ArrayList<Flight>();

		String query = "SELECT * FROM Flight";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, id, password);

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();

				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("NumOfSeats"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flight.setNumReservations(rs.getInt("NumReservations"));

				flights.add(flight);
			}

		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver not found: " + e.getMessage());
		}

		return flights;
	}

	public List<Flight> mostActiveFlights() {

		/*
		 * Get list of most active flights, code goes here
		 */

		List<Flight> flights = new ArrayList<Flight>();

		String query = "SELECT I.AirlineID, I.FlightNo, COUNT(*) AS NumReservations " + "FROM Itinerary I "
				+ "JOIN FlightReservation R ON I.ResrNo = R.ResrNo " + "GROUP BY I.AirlineID, I.FlightNo "
				+ "HAVING COUNT(*) = ( " + "   SELECT MAX(cnt) " + "   FROM ( " + "       SELECT COUNT(*) AS cnt "
				+ "       FROM Itinerary I2 " + "       JOIN FlightReservation R2 ON I2.ResrNo = R2.ResrNo "
				+ "       GROUP BY I2.AirlineID, I2.FlightNo " + "   ) AS sub " + ");";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, id, password);

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumReservations(rs.getInt("NumReservations"));
				flights.add(flight);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver not found: " + e);
		}

		return flights;
	}

	public List<Flight> getFlightsForAirport(String airport) {

		/*
		 * Code here to get flights given an airport
		 */

		List<Flight> flights = new ArrayList<Flight>();

		String query = "SELECT F.* " + "FROM Flight F "
				+ "JOIN Itinerary I ON F.FlightNo = I.FlightNo AND F.AirlineID = I.AirlineID "
				+ "WHERE I.Departure = ? OR I.Arrival = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, id, password);

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, airport);
			ps.setString(2, airport);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("NumOfSeats"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flight.setNumReservations(rs.getInt("NumReservations"));
				flights.add(flight);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver not found: " + e);
		}

		return flights;
	}

	// Returns the flights departed and arrived on time.
	public List<Flight> getOnTimeFlights() {

		/*
		 * Code here to get on time flights
		 */

		List<Flight> flights = new ArrayList<Flight>();

		String query = "SELECT DISTINCT F.* " + "FROM Flight F "
				+ "JOIN Itinerary I ON F.AirlineID = I.AirlineID AND F.FlightNo = I.FlightNo "
				+ "WHERE I.ActualArrTime <= I.ArrTime " + "AND I.ActualDepTime <= I.DepTime";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, id, password);

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("NumOfSeats"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flight.setNumReservations(rs.getInt("NumReservations"));
				flights.add(flight);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver not found: " + e);
		}

		return flights;
	}

	public List<Flight> getDelayedFlights() {

		/*
		 * Code here to get delayed flights
		 */

		List<Flight> flights = new ArrayList<Flight>();

		String query = "SELECT DISTINCT F.* " + "FROM Flight F "
				+ "JOIN Itinerary I ON F.AirlineID = I.AirlineID AND F.FlightNo = I.FlightNo "
				+ "WHERE I.ActualArrTime > I.ArrTime " + "OR I.ActualDepTime > I.DepTime";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, id, password);

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("NumOfSeats"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flight.setNumReservations(rs.getInt("NumReservations"));
				flights.add(flight);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver not found: " + e);
		}

		return flights;
	}

	// Jungu

	public List<Flight> getCustomerFlightSuggestions(int accountNo) {
		List<Flight> flights = new ArrayList<Flight>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
					"root", "root");

			// ONE SQL: Score flights by customer's frequent airports
			String sql = "SELECT f.*, " + "CASE WHEN i.AirlineID IS NOT NULL THEN 1 ELSE 0 END as suggestionScore "
					+ "FROM Flight f "
					+ "LEFT JOIN Itinerary i ON f.AirlineID = i.AirlineID AND f.FlightNo = i.FlightNo "
					+ "AND i.ResrNo IN (SELECT ResrNo FROM FlightReservation WHERE AccountNo = " + accountNo + ") "
					+ "ORDER BY suggestionScore DESC, f.NumReservations ASC " + "LIMIT 5";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("NumOfSeats"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flight.setNumReservations(rs.getInt("NumReservations"));
				flights.add(flight);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		// for (int i = 0; i < 5; i++) {
		// Flight flight = new Flight();
		// flight.setAirlineID("AA");
		// flight.setFlightNo(111);
		// flight.setNumReservations(30);
		// flights.add(flight);
		// }
		/// *Sample data ends*/

		return flights;
	}

	// Jungu
	public List<Flight> getBestSellingFlights() {

		/*
		 * Get list of best selling flights
		 */

		List<Flight> flights = new ArrayList<Flight>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/travel_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
					"root", "root");

			// ONE SQL: Score flights by customer's frequent airports
			String sql = "SELECT * FROM Flight ORDER BY NumReservations DESC";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("NumOfSeats"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flight.setNumReservations(rs.getInt("NumReservations"));
				flights.add(flight);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

//		for (int i = 0; i < 5; i++) {
//			Flight flight = new Flight();
//			flight.setAirlineID("AA");
//			flight.setFlightNo(111);
//			flight.setNumReservations(30);
//			flights.add(flight);			
//		}
//		/*Sample data ends*/

		return flights;
	}
}