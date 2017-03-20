package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.jws.soap.SOAPBinding.Use;

import model.Profile;
import model.Rating;
import model.Rating.Rate;

public class RatingsDAO {

	private static RatingsDAO instance;
	private static final HashSet<Rating> allRatings = new HashSet<>();

	private RatingsDAO() {
	}

	public static synchronized RatingsDAO getInstance() {
		if (instance == null) {
			instance = new RatingsDAO();
		}
		return instance;
	}

	public HashSet<Rating> getAllRatings() throws SQLException{
		if(allRatings.isEmpty()){
			String sql = "SELECT user_giver_id, user_taker_id, description, rate, date, id FROM ratings";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Timestamp date = res.getTimestamp("date");
				Profile giver = UserDAO.getInstance().getUserById(res.getLong("user_giver_id"));
				Profile taker = UserDAO.getInstance().getUserById(res.getLong("user_taker_id"));
				Rating r = new Rating(giver, taker, res.getString("description"), date.toLocalDateTime(),
						Rate.valueOf(res.getString("rate")));
				allRatings.add(r);
			}
		}
		return allRatings;
	}
	
	public TreeSet<Rating> getReceivedRatingsForId(long id) throws SQLException{
		TreeSet<Rating> ratings = new TreeSet<>();
		if(getAllRatings().isEmpty()){
			return null;
		}
		for(Rating r : allRatings){
			if(r.getReceiverId() == id){
				ratings.add(r);
			}
		}
		if (!ratings.isEmpty())
			return ratings;
		return null;
	}
	
	public TreeSet<Rating> getGivenRatingsForId(long id) throws SQLException{
		TreeSet<Rating> ratings = new TreeSet<>();
		if(getAllRatings().isEmpty())
			return null;
		for(Rating r : allRatings){
			if(r.getGiverId() == id){
				ratings.add(r);
			}
		}
		if (!ratings.isEmpty())
			return ratings;
		return null;
	}
}
