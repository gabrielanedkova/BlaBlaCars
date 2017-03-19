package model.dao;

import java.security.KeyStore.Entry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;



import model.Car;
import model.Profile;
import model.Travel;
import model.Car.Brand;
import model.Car.Color;
import model.Car.Comfort;
import model.Car.TypeOfCar;
import model.Profile.Gender;
import model.Travel.Destination;
import model.Travel.Flexibility;
import model.Travel.Luggage;

//TODO GabiA
public class TravelDAO {
	
	private static TravelDAO instance;
	// <pickUp, <dropOff, treeSet<Travel>>>
	private static HashMap<Destination, HashMap<Destination, TreeSet<Travel>>> allTravels;
	
	private TravelDAO(){
		allTravels = new HashMap<>();
	}
	
	public synchronized static TravelDAO getInstance(){
		if(instance == null){
			instance = new TravelDAO();
		}
		return instance;
	}
	
	
	public void addTravel(Travel t){
		
		
		
	}
	
	private  HashMap<Destination, HashMap<Destination, TreeSet<Travel>>> getAllTravels() throws SQLException{
		System.out.println(" Started getAll TRavels");
		if(allTravels.isEmpty()){
			String sql = "select  u.first_name, u.last_name, u.gender, u.email, u.password, u.year_of_birth, r.pickUp, r.dropOff, r.date, r.price, r.free_seats, r.ladies_only, r.maxLuggage, r.description, r.pick_up_flexibility, c.brand, c.model, c.type_of_car, c.comfort, c.number_of_seats, c.color "
					+ "from users u join rides r "
					+ "on (u.id = r.user_id) "
					+ "join cars c "
					+ "on (r.user_id = c.user_id);";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			
			ResultSet allRides = st.executeQuery();
			while(allRides.next()){
				
				Profile u = new Profile(allRides.getString("first_name"), allRides.getString("last_name"), Gender.valueOf(allRides.getString("gender")), allRides.getString("email"), allRides.getString("password"), allRides.getInt("year_of_birth"));
				
				Car car = new Car(Brand.valueOf(allRides.getString("brand")),allRides.getString("model"),TypeOfCar.valueOf(allRides.getString("type_of_car")),Comfort.valueOf(allRides.getString("comfort")), allRides.getInt("number_of_seats"), Color.valueOf(allRides.getString("color")));
				
				Destination pickUp = Destination.valueOf(allRides.getString("pickUp"));
				Destination dropOff =Destination.valueOf(allRides.getString("dropOff"));
				
				java.sql.Timestamp date = allRides.getTimestamp("date");
				Travel t = new Travel(u, car, pickUp, dropOff, date.toLocalDateTime(),allRides.getInt("price"), allRides.getInt("free_seats"), (allRides.getInt("ladies_only") == 1? true : false),Luggage.valueOf(allRides.getString("maxLuggage")),allRides.getString("description"),Flexibility.valueOf(allRides.getString("pick_up_flexibility")));
				//System.out.println(t.toString());
				if(!allTravels.containsKey(pickUp)){
					allTravels.put(pickUp, new HashMap<>());
				}
				if(!allTravels.get(pickUp).containsKey(dropOff)){
					allTravels.get(pickUp).put(dropOff, new TreeSet<>());
				}
				
				allTravels.get(pickUp).get(dropOff).add(t);	
			}
		}
		
		return allTravels;
		
		
	
	}
	
	public synchronized TreeSet filterTravels(String pickUp1, String dropOff1) throws SQLException{
		
		Destination pickUp = Destination.valueOf(pickUp1);
		Destination dropOff = Destination.valueOf(dropOff1);
		TreeSet<Travel> filteredTravels = new TreeSet<>();
		
		if(getAllTravels().containsKey(pickUp)){
			if(getAllTravels().get(pickUp).containsKey(dropOff)){
				filteredTravels = allTravels.get(pickUp).get(dropOff);
			}
		}
		System.out.println("Filtered the travels");
		System.out.println(filteredTravels.toString());
		return filteredTravels;
		
//		for(Iterator<Map.Entry<Destination, HashMap<Destination, TreeSet<Travel>>>> it = allTravels.entrySet().iterator(); it.hasNext();){
//			Map.Entry<Destination, HashMap<Destination, TreeSet<Travel>>> startDestination = it.next();
//			if(startDestination.getKey() == pickUp){
//				HashMap<Destination, TreeSet<Travel>> tempEndDestination = startDestination.getValue();
//				for(Iterator<Map.Entry<Destination, TreeSet<Travel>>> it2 = tempEndDestination.entrySet().iterator(); it2.hasNext();){
//					Map.Entry<Destination, TreeSet<Travel>> endDestination = it2.next();
//					if(endDestination.getKey() == dropOff){
//						for(Iterator<Travel> it3 = endDestination.getValue().iterator(); it3.hasNext();){
//							
//						}
//					}
//				}
//			}
//		}
		
	}

}
