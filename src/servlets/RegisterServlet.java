package servlets;

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



@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		String gender = req.getParameter("gender");
		String yearOfBirth = req.getParameter("yearOfBirth");
		Connection conn = DBManager.getConnection();
		
		if (conn != null) {
			
			String sql = "select email from users where email=?";
			PreparedStatement s;
			try {
				s = conn.prepareStatement(sql);
				s.setString(1, email);
				ResultSet r = s.executeQuery();
				if (!r.isBeforeFirst()) {
					String uuid = UUID.randomUUID().toString();
					resp.getWriter().write("account made");
					String user = "INSERT INTO `blabla`.`users` (`email`, `first_name`, `last_name`, `gender`, `password`, `year_of_birth`, `is_verified`, `verification_key`)"
							+ " VALUES ('" + email+ "', '" +firstName+ "', '"+lastName+"', '"+gender+"', '"+pass+"', '"+yearOfBirth+"', '0', '" + uuid +"');";		
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
