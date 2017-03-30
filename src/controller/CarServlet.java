package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import jdk.nashorn.internal.ir.RuntimeNode.Request;
import model.Car;
import model.Car.Brand;
import model.Car.Color;
import model.Car.Comfort;
import model.Car.TypeOfCar;
import model.Profile;
import model.dao.UserDAO;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet("/car")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		Profile user = (Profile) session.getAttribute("user");
		Car c = user.getCar();
		System.out.println(c);
		int id = c.getCarId();
	
		
		
		
		if(UserDAO.getInstance().changeCar(user.getEmail(), id, request.getParameter("brand"), request.getParameter("model"), request.getParameter("comfort"), request.getParameter("color"), request.getParameter("type"), Integer.parseInt(request.getParameter("seats")))){
			System.out.println("car changed");
			c.setBrand(Brand.valueOf(request.getParameter("brand")));
			c.setModel(request.getParameter("model"));
			c.setComfort(Comfort.valueOf(request.getParameter("comfort")));
			c.setColor(Color.valueOf(request.getParameter("color")));
			c.setType(TypeOfCar.valueOf(request.getParameter("type")));
			c.setNumberOfSeats(Integer.parseInt(request.getParameter("seats")));
				
				response.sendRedirect("car.jsp");
		}
	}

}
