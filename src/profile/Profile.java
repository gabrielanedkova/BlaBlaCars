package profile;

import java.util.ArrayList;
import java.util.HashSet;

public class Profile {
	
	private static final int MAX_YEAR_OF_BIRTH = 1999;

	private static final int MIN_YEAR_OF_BIRTH = 1917;

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
	//private (collection || array || var) preferences;
	//private ?? photo
	private ArrayList<Car> cars;
	private HashSet<Profile> rodeWithMe;
	
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
	
	
	public void addCar(Car c){
	
	}
	
	public void addPassengers(Profile p){
		
	}
	
	public void giveRating(Profile p){
		
	}
	
	
	
	
	
	
	
}
