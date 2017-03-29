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
import model.dao.RegisterDAO;



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
		String passConfirm = req.getParameter("passConfirm");
		int yearOfBirth = Integer.valueOf(req.getParameter("yearOfBirth"));
		
		if(!pass.equals(passConfirm)){
			resp.sendRedirect("registerFailed.html");
			return;
		}
		
		if(RegisterDAO.getInstance().register(email, firstName, lastName, gender, pass, yearOfBirth)){
			resp.getWriter().append("account made");
		}
		else{
			resp.getWriter().append("User already exists");
		}
		
	
		
	}

}
