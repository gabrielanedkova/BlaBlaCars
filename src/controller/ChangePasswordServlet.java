package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Profile;
import model.dao.UserDAO;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/changePass")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entered do post in changing pass");
		HttpSession session = request.getSession();
		Profile user = (Profile) session.getAttribute("user");
		String email = user.getEmail();
		String oldPass = request.getParameter("oldPassword");
		String newPass = request.getParameter("password");
		String confirmPass = request.getParameter("passConfirm");
		
		if(!oldPass.equals(user.getPassword())){
			System.out.println("wrrong pass");
			session.setAttribute("passChange", "Wrong password");
			response.sendRedirect("profileSettings.jsp");
			return;
		}
		
		if(!newPass.equals(confirmPass)){
			System.out.println("new pass did not match");
			session.setAttribute("passChange", "Passwords did not match");
			
			response.sendRedirect("profileSettings.jsp");
			return;
		}
		
		if(UserDAO.getInstance().changePassword(email, oldPass, newPass)){
			System.out.println("pass changed");
			user.setPassword(newPass);
				session.setAttribute("passChange", "");
				response.sendRedirect("profile.jsp");
		}
		
		
	}

}
