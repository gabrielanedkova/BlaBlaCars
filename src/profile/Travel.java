package profile;

import java.util.Date;
import java.util.HashSet;

import profile.Profile.Gender;

public class Travel implements Comparable<Travel>{
	
	enum Destination { SOFIA, PLOVDIV, SLIVEN, PERNIK, VARNA, BURGAS};
	enum Luggage { LARGE, MEDIUM, SMALL};
	enum Flexibility { NONE, FIFTEEN_MINS_MAX, THIRTY_MINS_MAX, ANYTHING_IS_FINE};
	
	private Profile person;
	private Car car;
	private Destination pickUp;
	private Destination dropOff;
	private Date date;//date and time of leave
	private int price;
	private int freeSeats;
	private boolean ladiesOnly;
	private Luggage maxLuggage;
	private String description;
	private Flexibility pickUpFlexibilty;
	private HashSet<Profile> passengers;
	
	
	public Travel(Profile person, Car car, Destination pickUp, Destination dropOff, Date date, int price, 
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
	}
	
	public Destination getPickUp() {
		return pickUp;
	}
	
	public Destination getDropOff() {
		return dropOff;
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
		}
		return false;
			
	}
	
	@Override
	public int compareTo(Travel o) {
		//Za gabi n.:
		//spored men trqbva da se sravnqvat i po tova koi gi e predlojil, zashtoto taka v saita
		//ne mojem da vkarame dve pytuvaniq s edna i systa data, dori i da sa ot razlichni hora predlojeni
		
		return o.date.compareTo(date);
	}
	

}
