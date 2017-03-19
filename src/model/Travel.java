package model;

import java.time.LocalDateTime;
import java.util.HashSet;

import model.Profile.Gender;

public class Travel implements Comparable<Travel>{
	
public	enum Destination { SOFIA, PLOVDIV, SLIVEN, PERNIK, VARNA, BURGAS};
public	enum Luggage { LARGE, MEDIUM, SMALL};
public	enum Flexibility { NONE, FIFTEEN_MINS_MAX, THIRTY_MINS_MAX, ANYTHING_IS_FINE};
	
	private Profile driver;
	private Car car;
	private Destination pickUp;
	private Destination dropOff;
	private LocalDateTime date;//date and time of leave
	private int price;
	private int freeSeats;
	private boolean ladiesOnly;
	private Luggage maxLuggage;
	private String description;
	private Flexibility pickUpFlexibilty;
	private HashSet<Profile> passengers;
	
	
	public Travel(Profile person, Car car, Destination pickUp, Destination dropOff, LocalDateTime date, int price, 
			int freeSeats, boolean ladiesOnly, Luggage maxLuggage, String description,
			Flexibility pickUpFlexibilty) {
		this.car = car;
		this.pickUp = pickUp;
		this.dropOff = dropOff;
		this.date = date;
		this.price = price;
		this.freeSeats = freeSeats;
		this.ladiesOnly = ladiesOnly;
		this.maxLuggage = maxLuggage;
		this.description = description;
		this.pickUpFlexibilty = pickUpFlexibilty;
		this.passengers = new HashSet<Profile>();
		this.driver = person;
	}
	
	public Destination getPickUp() {
		return pickUp;
	}
	
	public Destination getDropOff() {
		return dropOff;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public boolean addPassengers(Profile p){
		if (this.freeSeats > 0){
			if (ladiesOnly){
				if (p.getGender().equals(Gender.MALE)){
					return false;
				}
			}
			freeSeats--;
			this.passengers.add(p);
			return true;
		}
		return false;
	
		
			
	}
	
	public Car getCar() {
		return car;
	}
	
	@Override
	public String toString() {
		return "Travel [driver=" + driver + ", car=" + car + ", pickUp=" + pickUp + ", dropOff=" + dropOff + ", date="
				+ date + ", price=" + price + ", freeSeats=" + freeSeats + ", ladiesOnly=" + ladiesOnly
				+ ", maxLuggage=" + maxLuggage + ", description=" + description + ", pickUpFlexibilty="
				+ pickUpFlexibilty + "]";
	}

	@Override
	public int compareTo(Travel o) {
		if (this.driver.compareTo(o.driver) == 0){
			return o.date.compareTo(date);
		}
		else return this.driver.compareTo(o.driver);
	}
	

}
