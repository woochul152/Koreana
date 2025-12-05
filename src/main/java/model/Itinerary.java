package model;

public class Itinerary {
	private int ResrNo;
	private String AirlineID;
	private int FlightNo;
	private String Departure;
	private String Arrival;
	private String DepTime;
	private String ArrTime;
	// added two field variables for getOnTimeFlights/ getDelaydFlights
	private String ActualDepTime;
	private String ActualArrTime;
	
	public int getResrNo() {
		return ResrNo;
	}
	public void setResrNo(int resrNo) {
		ResrNo = resrNo;
	}
	public String getAirlineID() {
		return AirlineID;
	}
	public void setAirlineID(String airlineID) {
		AirlineID = airlineID;
	}
	public int getFlightNo() {
		return FlightNo;
	}
	public void setFlightNo(int flightNo) {
		FlightNo = flightNo;
	}
	public String getDeparture() {
		return Departure;
	}
	public void setDeparture(String departure) {
		Departure = departure;
	}
	public String getArrival() {
		return Arrival;
	}
	public void setArrival(String arrival) {
		Arrival = arrival;
	}
	public String getDepTime() {
		return DepTime;
	}
	public void setDepTime(String depTime) {
		DepTime = depTime;
	}
	public String getArrTime() {
		return ArrTime;
	}
	public void setArrTime(String arrTime) {
		ArrTime = arrTime;
	}
	public String getActualArrTime() {
		return ActualArrTime;
	}
	public void setActualArrTime(String actualArrTime) {
		ActualArrTime = actualArrTime;
	}
	public String getActualDepTime() {
		return ActualDepTime;
	}
	public void setActualDepTime(String actualDepTime) {
		ActualDepTime = actualDepTime;
	}
}
