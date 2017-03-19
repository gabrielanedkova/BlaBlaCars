package controller;
//TODO GabiA
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emailSender.SendEmail;
import model.dao.DBManager;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		String gender = req.getParameter("gender");
		int yearOfBirth = Integer.valueOf(req.getParameter("yearOfBirth"));
		Connection conn = DBManager.getInstance().getConnection();
		
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
					resp.getWriter().write("account made");
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
						
					
				}
				else{
					resp.getWriter().write("User already exists");
				}
			} catch (SQLException e) {
				resp.getWriter().write(("SQL not responsive: " + e.getMessage()));
			}
			
			
		}
		
	}

}
