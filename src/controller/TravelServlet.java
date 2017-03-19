package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Travel;
import model.dao.TravelDAO;

/**
 * Servlet implementation class TravelServlet
 */
@WebServlet("/travel")
public class TravelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TreeSet<Travel> travels = new TreeSet<>();
		
		String pickUp = request.getParameter("pickUp");
		String dropOff = request.getParameter("dropOff");
		
		try {
			travels = TravelDAO.getInstance().filterTravels(pickUp, dropOff);
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
		}
		
		response.getWriter().append(travels.toString());
		
		
		
		
	}

}
