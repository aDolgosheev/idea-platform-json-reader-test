package ru.dolgosheev;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

	@JsonProperty("carrier")
	private String carrier;

	@JsonProperty("arrival_time")
	private String arrivalTime;

	@JsonProperty("price")
	private int price;

	@JsonProperty("origin")
	private String origin;

	@JsonProperty("departure_date")
	private String departureDate;

	@JsonProperty("destination")
	private String destination;

	@JsonProperty("destination_name")
	private String destinationName;

	@JsonProperty("stops")
	private int stops;

	@JsonProperty("departure_time")
	private String departureTime;

	@JsonProperty("arrival_date")
	private String arrivalDate;

	@JsonProperty("origin_name")
	private String originName;

	public String getCarrier(){
		return carrier;
	}

	public String getArrivalTime(){
		return arrivalTime;
	}

	public int getPrice(){
		return price;
	}

	public String getOrigin(){
		return origin;
	}

	public String getDepartureDate(){
		return departureDate;
	}

	public String getDestination(){
		return destination;
	}

	public String getDestinationName(){
		return destinationName;
	}

	public int getStops(){
		return stops;
	}

	public String getDepartureTime(){
		return departureTime;
	}

	public String getArrivalDate(){
		return arrivalDate;
	}

	public String getOriginName(){
		return originName;
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"carrier='" + carrier + '\'' +
				", arrivalTime='" + arrivalTime + '\'' +
				", price=" + price +
				", origin='" + origin + '\'' +
				", departureDate='" + departureDate + '\'' +
				", destination='" + destination + '\'' +
				", destinationName='" + destinationName + '\'' +
				", stops=" + stops +
				", departureTime='" + departureTime + '\'' +
				", arrivalDate='" + arrivalDate + '\'' +
				", originName='" + originName + '\'' +
				'}';
	}
}