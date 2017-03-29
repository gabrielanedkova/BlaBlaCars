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

		String email = req.getParameter("email").trim();
		String pass = req.getParameter("pass");
		try {
			if (UserDAO.getInstance().validLogin(email, pass)) {
				if(!UserDAO.getInstance().isVerified(email))
					resp.sendRedirect("loginNotVerified.html");
				else resp.getWriter().write("Hello " + UserDAO.getInstance().getUserFirstName(email) +"!");
			}
			else resp.sendRedirect("invalidLogin.html");

		} catch (SQLException e) {
			System.out.println("Oopps something went wrong!");
		}


	}

}