package profile;

import java.security.KeyStore.Entry;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javax.print.attribute.standard.Destination;

import profile.Profile.RideType;

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
	
	public void filterOfferedRides(){
		//TODO everything
	}
	

	public void addRideToSite(Travel t){
		//TODO when a person offers a ride, the ride goes
		//into his collection, but also in this one allOfferedRides.
	}
}
