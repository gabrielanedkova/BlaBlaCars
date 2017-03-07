package profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import profile.Rating.Rate;

public class Profile implements Comparable<Profile>{
	
	//comment ... 
	
	private static final int MAX_YEAR_OF_BIRTH = 1999;

	private static final int MIN_YEAR_OF_BIRTH = 1917;

	enum RideType{UPCOMING, PAST};
	enum Gender{ MALE, FEMALE};
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private String firstName;
	private String lastName;
	private final Gender gender;
	private String email;
	private String password;
	private int yearOfBirth;
	private String miniBio;
	private double rate;
	//private (collection || array || var) preferences;
	//private ?? photo
	private ArrayList<Car> cars;
	private HashSet<Profile> rodeWithMe;
	private TreeSet<Rating> givenRatings;
	private TreeSet<Rating> receivedRatings;
	private TreeSet<Travel> bookings;
	private HashMap<RideType, TreeSet<Travel>> ridesOffered;

	public Profile(Gender gender){
		if(gender != null){
			this.gender = gender;
		}
		else this.gender = Gender.FEMALE;
	}
	
	public Profile(String firstName, String lastName, Gender gender, String email, String password, int yearOfBirth) {
		if(gender != null){
			this.gender = gender;
		}
		else this.gender = Gender.FEMALE;
		
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setYearOfBirth(yearOfBirth);
		this.cars = new ArrayList<Car>();
		this.rodeWithMe = new HashSet<Profile>();
		this.bookings = new TreeSet<Travel>();
		this.ridesOffered = new HashMap<RideType, TreeSet<Travel>>();
		this.ridesOffered.put(RideType.PAST, null);
		this.ridesOffered.put(RideType.UPCOMING, null);

	}

	public void setFirstName(String firstName) {
		if(firstName != null && !firstName.isEmpty()){
			this.firstName = firstName;
		}
	}

	public void setLastName(String lastName) {
		if(lastName != null && !lastName.isEmpty()){
			this.lastName = lastName;
		}	
	}

	public void setEmail(String email) {
		if(email != null && !email.isEmpty() && email.matches(EMAIL_PATTERN)){
			this.email = email;
		}
	}

	
	public void setPassword(String password) {
		if(password != null && !password.isEmpty() && password.matches("{8,}")){
			this.password = password;
		}
	}

	public void setYearOfBirth(int yearOfBirth) {
		if(yearOfBirth >= MIN_YEAR_OF_BIRTH && yearOfBirth <= MAX_YEAR_OF_BIRTH){
			this.yearOfBirth = yearOfBirth;
		}
	}

	public void setMiniBio(String miniBio) {
		if(miniBio != null && !miniBio.isEmpty()){
			this.miniBio = miniBio;
		}
		else{
			this.miniBio = "I don't have mini biography yet.";
		}
	}
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		if (rate > 0 && rate <= 5)
			if (this.rate == 0)
				this.rate = rate;
			else {
				this.rate += rate;
				this.rate /= 2;
			}
	}
	
	public Gender getGender(){
		return this.gender;
	}
	
	public void addCar(Car c){
		if (c != null){
			this.cars.add(c);
		}
	}
	
	public void addPassengers(Profile p){
		if (p != null)
			this.rodeWithMe.add(p);
	}
	
	public void giveRating(Profile p, String descripting, Rate rate){
		if (rodeWithMe.contains(p)){
			Rating r = new Rating(this, descripting, LocalDate.now(), rate);
			rodeWithMe.remove(p);
			p.receiveRating(r);
			this.givenRatings.add(r);
			
		}
		else System.out.println("Sorry, you can't give rating!");
	}
	
	public void receiveRating(Rating r){
		this.receivedRatings.add(r);
		this.setRate(r.getRate().getValue());
	}
	

	public void bookRide(){
		//TODO
	}
	
	public boolean acceptRide(){
		//TODO
		return false;
	}
	
	public void addRide(Travel t){
		//TODO
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public int compareTo(Profile o) {
		return this.email.compareTo(o.email);
	}

	
	}
