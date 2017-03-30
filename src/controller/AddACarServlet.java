package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Car;
import model.Profile;
import model.Car.Brand;
import model.Car.Color;
import model.Car.Comfort;
import model.Car.TypeOfCar;
import model.dao.UserDAO;

/**
 * Servlet implementation class AddACarServlet
 */
@WebServlet("/addCar")
public class AddACarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Profile user = (Profile) session.getAttribute("user");
		Car c = user.getCar();
		if(UserDAO.getInstance().addCar(user.getEmail(), request.getParameter("brand"), request.getParameter("model"), request.getParameter("comfort"), request.getParameter("color"), request.getParameter("type"), Integer.parseInt(request.getParameter("seats")))){
			System.out.println("car added");
			c.setBrand(Brand.valueOf(request.getParameter("brand")));
			c.setModel(request.getParameter("model"));
			c.setComfort(Comfort.valueOf(request.getParameter("comfort")));
			c.setColor(Color.valueOf(request.getParameter("color")));
			c.setType(TypeOfCar.valueOf(request.getParameter("type")));
			c.setNumberOfSeats(Integer.parseInt(request.getParameter("seats")));
			c.setCarId((int)UserDAO.getInstance().getCarId(user.getEmail()));
			response.sendRedirect("car.jsp");
		}
		
	}

}
