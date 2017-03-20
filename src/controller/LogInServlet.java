package controller;
//TODO GabiN
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DBManager;
import model.dao.UserDAO;

import java.sql.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.getWriter().write("");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		try {
			if (UserDAO.getInstance().validLogin(email, pass)) {
				if(!UserDAO.getInstance().isVerified(email))
					resp.getWriter().write("Please verify your account!");
				else resp.getWriter().write("Hello " + UserDAO.getInstance().getUserFirstName(email) +"!");
			}
			else resp.getWriter().write("Incorrect password or email!");

		} catch (SQLException e) {
			System.out.println("Oopps something went wrong!");
		}
		/*Connection conn = DBManager.getInstance().getConnection();
		if (conn != null) {
			try {

				String sql = "SELECT password, is_verified, first_name FROM users WHERE email=?";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, email);
				ResultSet r = s.executeQuery();
				if (!r.isBeforeFirst()) {
					resp.getWriter().write("Account does not exist!");
				}
				while (r.next()) {
					if (r.getString("password").compareTo((pass)) == 0) {
						if (r.getString("is_verified").compareTo("1") == 0)
							resp.getWriter().write("Hello " + r.getString("first_name"));
						else{
							resp.getWriter().write("Please verify your account!");
							resp.sendRedirect("index.html");
						}
					} else
						resp.getWriter().write("Incorrect password!");
				

				}
			} catch (SQLException e) {
				resp.getWriter().write("Ooops something went wrong.");
			}
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
*/

	}

}