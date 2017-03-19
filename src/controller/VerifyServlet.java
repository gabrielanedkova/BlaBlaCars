package controller;
//TODO GabiN
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DBManager;

/**
 * Servlet implementation class VerifyServlet
 */
@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String key = req.getParameter("verificationKey");
		Connection conn = DBManager.getInstance().getConnection();
		if (conn != null) {
			try {
				String sql = "SELECT is_verified, verification_key FROM users WHERE email=?";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, email);
				ResultSet r = s.executeQuery();
				if (!r.isBeforeFirst()) {
					resp.getWriter().write("Account does not exist!");
				}
				while (r.next()) {
					if (r.getString("is_verified").compareTo("1") == 0)
						resp.getWriter().write("Your account is verified!");
					else if (r.getString("verification_key").compareTo(key) == 0) {
						sql = "UPDATE users SET is_verified='1' WHERE email=?";
						s = conn.prepareStatement(sql);
						s.setString(1, email);
						s.executeUpdate();
						resp.getWriter().write("Thank you for verifying your account!");
					} else {
						resp.getWriter().write("Please try again.");
					}
					break;
				}
			} catch (SQLException e) {
				resp.getWriter().write(e.getMessage());
				resp.getWriter().write("Ooops something went wrong.");
/*			} finally {
				try {
					conn.close();
				} catch (SQLException e) {   
					e.printStackTrace();
				}*/
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
