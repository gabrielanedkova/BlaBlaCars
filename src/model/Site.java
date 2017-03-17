package model;

import java.security.KeyStore.Entry;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javax.print.attribute.standard.Destination;

import model.Car.Comfort;
import model.Profile.RideType;

public class Site {
	
	private HashMap<String, Profile> allRegistrations;
	private HashMap<RideType, TreeSet<Travel>> allOfferedRides;
	
	public void logIn(String email, String password){
		//TODO everything
	}
	
	public void register(){
		//TODO everything
	}
	
	public void deleteProfile(Profile p){
		//TODO everything
	}
	
	public TreeSet showAllOfferedRides(Travel.Destination pickUp, Travel.Destination dropOff){
		//TODO if pickUp and dropOff are not valid
		TreeSet<Travel> filteredRides = new TreeSet<>();
		if(allOfferedRides.containsKey(RideType.UPCOMING)){
			TreeSet<Travel> allRides = allOfferedRides.get(RideType.UPCOMING);
			for(Iterator<Travel> it = allRides.iterator(); it.hasNext();){
				Travel t = it.next();
				// == never throws NullPointerException
				if( t.getPickUp() == pickUp && t.getDropOff() == dropOff ){
					filteredRides.add(t);
				}
			}	
		}
		return (TreeSet<Travel>) Collections.unmodifiableSet(filteredRides);
	}
	
	
	
	//maybe it will be a timespan, not a hour?
	public TreeSet filterByTime(LocalDateTime time, TreeSet<Travel> rides){
		
		TreeSet<Travel> filteredRides = new TreeSet<>();
	
		for(Iterator<Travel> it = rides.iterator(); it.hasNext();){
			Travel t = it.next();
			//when changed to DateAndTime change getHours to getHour()
			if(t.getDate().getHour() == time.getHour() ){
				filteredRides.add(t);
			}
		}
		
		return (TreeSet<Travel>) Collections.unmodifiableSet(filteredRides);		
		
	}
	
	public TreeSet filterByDate(LocalDateTime date, TreeSet<Travel> rides){
		
		TreeSet<Travel> filteredRides = new TreeSet<>();

		for(Iterator<Travel> it = rides.iterator(); it.hasNext();){
			Travel t = it.next();
			//when changed to DateAndTime change getDate to getDayOfMonth()
			if(t.getDate().getDayOfMonth() == date.getDayOfMonth() && t.getDate().getMonthValue() == date.getMonthValue() && t.getDate().getYear() == date.getYear()){
				filteredRides.add(t);
			}
		}
		
		return (TreeSet<Travel>) Collections.unmodifiableSet(filteredRides);		
		
	}
	
	public TreeSet filterByComfort(Comfort comfort, TreeSet<Travel> rides){
		
		TreeSet<Travel> filteredRides = new TreeSet<>();
	
		for(Iterator<Travel> it = rides.iterator(); it.hasNext();){
			Travel t = it.next();
			//when changed to DateAndTime change getHours to getHour()
			if(t.getCar().getComfort() == comfort ){
				filteredRides.add(t);
			}
		}
		
		return (TreeSet<Travel>) Collections.unmodifiableSet(filteredRides);		
		
	}
	
	

	public void addRideToSite(Travel t){
		//TODO when a person offers a ride, the ride goes
		//into his collection, but also in this one allOfferedRides.
	}
}
