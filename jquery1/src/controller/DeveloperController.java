package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.DeveloperImpl;
import dao.DeveloperDAOImpl;

@WebServlet("/DeveloperController")
public class DeveloperController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public DeveloperController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String developerId = request.getParameter("id");
		
		DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		ArrayList<DeveloperImpl> developers = developerDAO.getDeveloper(Integer.parseInt(developerId));
		developerDAO.deleteDeveloper(developers.get(0));
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String json = new Gson().toJson(developers);
		out.println(json);
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost1();
		doPost2(request, response);
	}
	protected void doPost1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String developerId = request.getParameter("fullname");

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		ArrayList<DeveloperImpl> developers = developerDAO.getDeveloper(Integer.parseInt(developerId));
		if (!developers.isEmpty())
			out.println(developers.get(0).getEntityDescription());
		else
			out.println(" Developer no encontrado");
		out.close();
	}
	
	protected void doPost2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		ArrayList<DeveloperImpl> developers = developerDAO.listDevelopers();
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String json = new Gson().toJson(developers);
		out.println(json);
		out.close();

	}


}
