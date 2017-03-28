package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.UUID;

import emailSender.SendEmail;
import model.Travel;
import model.Travel.Destination;

public class RegisterDAO {
	
	private	Connection conn = DBManager.getInstance().getConnection();
	private static RegisterDAO instance;
	// <pickUp, <dropOff, treeSet<Travel>>>
	private static HashMap<Destination, HashMap<Destination, TreeSet<Travel>>> allTravels;
	
	private RegisterDAO(){
		allTravels = new HashMap<>();
	}
	
	public synchronized static RegisterDAO getInstance(){
		if(instance == null){
			instance = new RegisterDAO();
		}
		return instance;
	}
	
	
	public boolean register(String email, String firstName, String lastName, String gender, String pass, int yearOfBirth){
		
		if (conn != null) {
			
			String sql = "SELECT email FROM users WHERE email=?";
			PreparedStatement s;
			try {
				s = conn.prepareStatement(sql);
				s.setString(1, email);
				ResultSet r = s.executeQuery();
				if (!r.isBeforeFirst()) {
					//verification key
					String uuid = UUID.randomUUID().toString();
					//resp.getWriter().write("account made");
					String user = "INSERT INTO `blabla`.`users` (`email`, `first_name`, `last_name`, `gender`, `password`, `year_of_birth`, `is_verified`, `verification_key`)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";	
					s = conn.prepareStatement(user);
					s.setString(1, email);
					s.setString(2, firstName);
					s.setString(3, lastName);
					s.setString(4, gender);
					s.setString(5, pass);
					s.setInt(6, yearOfBirth);
					s.setInt(7, 0);
					s.setString(8, uuid);
					s.executeUpdate();
					
						SendEmail.sendVerificationMail(email, firstName, uuid);
						
					return true;
				}
			} catch (SQLException e) {
				System.out.println("SQL not responsive: " + e.getMessage());
				return false;
			}
		}
		return false;
	}

}
