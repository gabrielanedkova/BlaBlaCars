package profile;

import java.util.Date;

public class Travel {
	
	enum Destination { SOFIA, PLOVDIV, SLIVEN, PERNIK, VARNA, BURGAS};
	enum Luggage { LARGE, MEDIUM, SMALL};
	enum Flexibility { NONE, FIFTEEN_MINS_MAX, THIRTY_MINS_MAX, ANYTHING_IS_FINE};
	
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
	

}
