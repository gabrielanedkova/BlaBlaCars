package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Profile;
import model.dao.UserDAO;

/**
 * Servlet implementation class ProfileSettingsServlet
 */
@WebServlet("/setProfile")
public class ProfileSettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Profile user = (Profile) session.getAttribute("user");
		String email = user.getEmail();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String miniBio = request.getParameter("miniBio");
		String pass = request.getParameter("password");
		Integer yearOfBirth = Integer.valueOf( request.getParameter("yearOfBirth"));
		
		if(user.getPassword().equals(pass)){
			
			if(!firstName.isEmpty()){
				UserDAO.getInstance().setDataHasChanged(true);
				
				if(UserDAO.getInstance().changeFirstName(email, firstName, pass)){
					user.setFirstName(firstName);
				}
			}
			if(!lastName.isEmpty()){
				UserDAO.getInstance().setDataHasChanged(true);
				
				if(UserDAO.getInstance().changeLastName(email, lastName, pass)){
					user.setLastName(lastName);
				}
			}
			if(!miniBio.isEmpty()){
				UserDAO.getInstance().setDataHasChanged(true);
				
				if(UserDAO.getInstance().changeMiniBio(email, miniBio, pass)){
					user.setMiniBio(miniBio);
				}
			}
			if(yearOfBirth!=null){
				UserDAO.getInstance().setDataHasChanged(true);
				
				if(UserDAO.getInstance().changeBirthYear(email, yearOfBirth, pass)){
					user.setYearOfBirth(yearOfBirth);
				}
			}
			session.setAttribute("settingsChange", "");
			System.out.println(user);
			response.sendRedirect("profile.jsp");	
		}
		else{
			session.setAttribute("settingsChange", "Wrong password");
			response.sendRedirect("profileSettings.jsp");
		}
		
	}

}
