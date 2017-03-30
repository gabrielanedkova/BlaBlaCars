package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import emailSender.SendEmail;
import model.Car;
import model.Profile;
import model.Car.Brand;
import model.Car.Color;
import model.Car.Comfort;
import model.Car.TypeOfCar;
import model.Profile.Gender;

//TODO GabiN
public class UserDAO {
	
		
	
		private boolean dataHasChanged = false;
		private static UserDAO instance;
		private static final HashMap<String, Profile> allUsers = new HashMap<>();

		private UserDAO(){
		}
		
		
		public static synchronized UserDAO getInstance(){
			if(instance == null){
				instance = new UserDAO();
			}
			return instance;
		}
		
		public void setDataHasChanged(boolean dataHasChanged) {
			this.dataHasChanged = dataHasChanged;
		}

		public synchronized boolean addUser(String email, String firstName, String lastName,
				String gender, String pass, int yearOfBirth) throws SQLException{

			if (getAllUsers().containsKey(email))
				return false;
			try {
					//verification key
					String uuid = UUID.randomUUID().toString();
					String sql = "INSERT INTO `blabla`.`users` (`email`, `first_name`, `last_name`, `gender`, `password`, `year_of_birth`, `is_verified`, `verification_key`)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";	
					PreparedStatement st= DBManager.getInstance().getConnection().prepareStatement(sql);
					st.setString(1, email);
					st.setString(2, firstName);
					st.setString(3, lastName);
					st.setString(4, gender);
					st.setString(5, pass);
					st.setInt(6, yearOfBirth);
					st.setInt(7, 0);
					st.setString(8, uuid);
					st.execute();
					ResultSet res = st.getGeneratedKeys();
					res.next();
					long id = res.getLong(1);
					boolean isVerified = false;
					Profile p = new Profile(firstName, lastName, Gender.valueOf(gender), email, pass, yearOfBirth, 
							id, uuid, isVerified);
					
						SendEmail.sendVerificationMail(email, firstName, uuid);
						allUsers.put(email, p);
						
			} catch (SQLException e) {
				System.out.println(("SQL not responsive: " + e.getMessage()));
			}
			return true;	
		}
		
		public synchronized HashMap<String, Profile> getAllUsers() throws SQLException{
			if(allUsers.isEmpty() || dataHasChanged == true){
//				String sql = "SELECT email, first_name, last_name, gender, password, "
//						+ "year_of_birth, mini_bio, photo, rate, id, is_verified,"
//						+ "verification_key FROM blabla.users";
				String sql = "SELECT u.email, u.first_name, u.last_name, u.gender, u.password, u.year_of_birth, u.mini_bio, u.photo, "
						+ "u.rate, u.id, u.is_verified, u.verification_key, c.brand, c.model, c.comfort, c.number_of_seats, c.color, "
						+ "c.type_of_car, c.id as carId FROM users u left join cars c on (u.id = c.user_id);";
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
				ResultSet res = st.executeQuery();
				while(res.next()){
					Gender g;
					String gender = res.getString("gender");
					if(gender.compareTo("female") == 0)
						g = Gender.FEMALE;
					else g = Gender.MALE;
					int isVerified = res.getInt("is_verified");
					boolean v;
					if (isVerified == 0)
						v = false;
					else v = true;
					Profile p = new Profile(res.getString("first_name"), res.getString("last_name"),
							g, res.getString("email"),res.getString("password"), res.getInt("year_of_birth"), 
							res.getLong("id"), res.getString("verification_key"), v);	
					p.setMiniBio(res.getString("mini_bio"));
					Car car = null;
					if(res.getString("brand")==null){
						car = new Car();
					}
					else { car = new Car(Brand.valueOf(res.getString("brand")),res.getString("model"),TypeOfCar.valueOf(res.getString("type_of_car")),Comfort.valueOf(res.getString("comfort")), res.getInt("number_of_seats"), Color.valueOf(res.getString("color")), res.getInt("carId"));
					}
					p.setCar(car);
					allUsers.put(p.getEmail(), p);
				}
			}
			dataHasChanged = false;
			return allUsers;
		}
		
		
		public synchronized Profile getUser(String email) throws SQLException{
			
			if(getAllUsers().containsKey(email)){
				return getAllUsers().get(email);
			}
			return null;
		}
		
		public synchronized boolean changePassword(String email, String oldPass, String newPass) {
			try {
				if(getUser(email)!=null){
					Profile user = getUser(email);
					if(user.getPassword().equals(oldPass)){
						user.setPassword(newPass);
						String sql = "UPDATE `blabla`.`users` SET `password`=? WHERE `email`=?;";
						PreparedStatement st =  DBManager.getInstance().getConnection().prepareStatement(sql);
						st.setString(1, newPass);
						st.setString(2, email);
						st.executeUpdate();
						return true;
					}
				}
			} catch (SQLException e) {

				System.out.println("Couldn't update password.. "+ e.getMessage());
			}
			return false;
		}
		
		
		public synchronized long getCarId(String email){
			 long id = -1;
			try {
				Profile user = getUser(email);
				int userId = (int) user.getId();
				String sql = "select id from cars where user_id = ?;";
				PreparedStatement st =  DBManager.getInstance().getConnection().prepareStatement(sql);
				st.setInt(1, userId);
				ResultSet res = st.executeQuery();
				res.next();
				 id = res.getLong(1);
				
			} catch (SQLException e) {
				System.out.println("Couldn't get car id .. "+ e.getMessage());
			}
			return id;
			
			
		}
		
		public synchronized boolean addCar(String email, String brand, String model, String comfort, String color, String type, int seats) {
			try {
				
					Profile user = getUser(email);
					int userId = (int) user.getId();
							String sql = "INSERT INTO `blabla`.`cars` (`brand`, `model`, `comfort`, `number_of_seats`, `color`, `type_of_car`,`user_id`) VALUES (?,?,?,?,?,?,?);";
							PreparedStatement st =  DBManager.getInstance().getConnection().prepareStatement(sql);
							st.setString(1, brand);
							st.setString(2, model);
							st.setString(3, comfort);
							st.setInt(4, seats);
							st.setString(5, color);
							st.setString(6, type);
							st.setInt(7, userId);
							st.executeUpdate();
							return true;
						
					
				}
			 catch (SQLException e) {

				System.out.println("Couldn't add car.. "+ e.getMessage());
			}
			return false;
		}
		
		
		public synchronized boolean changeCar(String email, int id, String brand, String model, String comfort, String color, String type, int seats) {
			try {
				
					Profile user = getUser(email);
					Car c = user.getCar();	
							String sql = "UPDATE `blabla`.`cars` SET `brand`=?, `model`=?, `comfort`=?, `number_of_seats`=?, `color`=?, `type_of_car`=? WHERE `id`=?;";
							PreparedStatement st =  DBManager.getInstance().getConnection().prepareStatement(sql);
							st.setString(1, brand);
							st.setString(2, model);
							st.setString(3, comfort);
							st.setInt(4, seats);
							st.setString(5, color);
							st.setString(6, type);
							st.setInt(7, id);
							st.executeUpdate();
							return true;
						
					
				}
			 catch (SQLException e) {

				System.out.println("Couldn't update car.. "+ e.getMessage());
			}
			return false;
		}
		
		public synchronized boolean changeFirstName(String email, String newName, String pass) {
			try {
				if(getUser(email)!=null){
					Profile user = getUser(email);
					if(user.getPassword().equals(pass)){
						if(!newName.isEmpty()){
							user.setFirstName(newName);
							String sql = "UPDATE `blabla`.`users` SET `first_name`=? WHERE `email`=?;";
							PreparedStatement st =  DBManager.getInstance().getConnection().prepareStatement(sql);
							st.setString(1, newName);
							st.setString(2, email);
							st.executeUpdate();
							return true;
						}
					}
				}
			} catch (SQLException e) {

				System.out.println("Couldn't update password.. "+ e.getMessage());
			}
			return false;
		}
		
		
		
		public synchronized boolean changeLastName(String email, String newName, String pass) {
			try {
				if(getUser(email)!=null){
					Profile user = getUser(email);
					if(user.getPassword().equals(pass)){
						if(!newName.isEmpty()){
							user.setFirstName(newName);
							String sql = "UPDATE `blabla`.`users` SET `last_name`=? WHERE `email`=?;";
							PreparedStatement st =  DBManager.getInstance().getConnection().prepareStatement(sql);
							st.setString(1, newName);
							st.setString(2, email);
							st.executeUpdate();
							return true;
						}
					}
				}
			} catch (SQLException e) {

				System.out.println("Couldn't update password.. "+ e.getMessage());
			}
			return false;
		}
		
		public synchronized boolean changeBirthYear(String email, int yearOfBirth, String pass) {
			try {
				if(getUser(email)!=null){
					Profile user = getUser(email);
					if(user.getPassword().equals(pass)){
						if(yearOfBirth>=1917 && yearOfBirth <= 1999){
							user.setYearOfBirth(yearOfBirth);
							String sql = "UPDATE `blabla`.`users` SET `year_of_birth`=? WHERE `email`=?;";
							PreparedStatement st =  DBManager.getInstance().getConnection().prepareStatement(sql);
							st.setInt(1, yearOfBirth);
							st.setString(2, email);
							st.executeUpdate();
							return true;
						}
					}
				}
			} catch (SQLException e) {

				System.out.println("Couldn't update password.. "+ e.getMessage());
			}
			return false;
		}
		
		public synchronized boolean changeMiniBio(String email, String newBio, String pass) {
			try {
				if(getUser(email)!=null){
					Profile user = getUser(email);
					if(user.getPassword().equals(pass)){
						if(!newBio.isEmpty()){
							user.setMiniBio(newBio);
							String sql = "UPDATE `blabla`.`users` SET `mini_bio`=? WHERE `email`=?;";
							PreparedStatement st =  DBManager.getInstance().getConnection().prepareStatement(sql);
							st.setString(1, newBio);
							st.setString(2, email);
							st.executeUpdate();
							return true;
						}
					}
				}
			} catch (SQLException e) {

				System.out.println("Couldn't update password.. "+ e.getMessage());
			}
			return false;
		}

		public synchronized boolean validLogin(String email, String pass) throws SQLException {
			if(exists(email)){
				return getAllUsers().get(email).getPassword().equals(pass);
			}
			return false;
		}


		public synchronized boolean isVerified(String email) throws SQLException {
			if(exists(email)){
				return getAllUsers().get(email).isVerified();
			}
			return false;
		}

		public synchronized boolean verify(String email, String verificationKey) throws SQLException {
			if(exists(email)){
				Profile p = getAllUsers().get(email);
				p.verify(verificationKey);
				String sql = "UPDATE users SET is_verified='1' WHERE email=?";
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
				st.setString(1, email);
				st.executeUpdate();
				return getAllUsers().get(email).isVerified();
			}
			return false;
		}
		
		public Profile getUserById(long id) throws SQLException {
			for(Entry<String, Profile> entry : getAllUsers().entrySet()){
				if(entry.getValue().getId() == id)
					return entry.getValue();
			}
			return null;
		}

		public String getUserFirstName(String email) throws SQLException {
			if(getAllUsers().containsKey(email))
				return getAllUsers().get(email).getFirstName();
			return "";
		}
		
		public boolean exists(String email) throws SQLException {
			return getAllUsers().containsKey(email);
		}
	}



