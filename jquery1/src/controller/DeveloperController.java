package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeveloperImpl;
import service.ServiceDeveloperImpl;
import utils.Tools.Operation;

import com.google.gson.Gson;

import dao.DeveloperDAOImpl;

@WebServlet("/DeveloperController")
public class DeveloperController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ServiceDeveloperImpl servicioDeveloper;
	/**
	 * Default constructor. 
	 */
	public DeveloperController() {
		servicioDeveloper = new ServiceDeveloperImpl();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		accion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		accion(request, response);
	}
	
	protected void accion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String oper= request.getParameter("oper");
		
		if (oper== null) oper="search";
		
		switch (Operation.valueOf(oper)) {
		case search:
			buscarDevelopers(request, response);
			break;
		case add:
			agregarDeveloper(request, response);
			break;
		case edit:
			editarDeveloper(request, response);
			break;
		case del:
			eliminarDeveloper(request, response);
			break;
		case export:
			exportarCSV(request, response);
			break;
		default:
			buscarDevelopers(request, response);
			break;
		}
		
	}
	
	protected void buscarDevelopers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		//DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		//ArrayList<DeveloperImpl> developers = developerDAO.listDevelopers();
		
		ArrayList<DeveloperImpl> developers = servicioDeveloper.buscarDevelopers();
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String json = new Gson().toJson(developers);
		out.println(json);
		out.close();

	}

	protected void agregarDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String devId = request.getParameter("id");
		String devFirstName = request.getParameter("firstName");
		String devLastName = request.getParameter("lastName");
		String devVelocity = request.getParameter("velocity");
		
		//DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		//DeveloperImpl dev = new DeveloperImpl();
		//dev.setFirstName(devFirstName);
		//dev.setLastName(devLastName);
		//dev.setVelocity(Integer.parseInt(devVelocity));
		//developerDAO.saveDeveloper(dev);
		
		servicioDeveloper.agregarDeveloper(devFirstName, devLastName, Integer.parseInt(devVelocity));
		
		ArrayList<DeveloperImpl> developers = servicioDeveloper.buscarDevelopers();
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String json = new Gson().toJson(developers);
		out.println(json);
		out.close();
		
	}

	protected void editarDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String devId = request.getParameter("id");
		String devFirstName = request.getParameter("firstName");
		String devLastName = request.getParameter("lastName");
		String devVelocity = request.getParameter("velocity");
		
		//DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		//ArrayList<DeveloperImpl> developers = developerDAO.getDeveloper(Integer.parseInt(devId));
		//DeveloperImpl dev = developers.get(0);
		//dev.setFirstName(devFirstName);
		//dev.setLastName(devLastName);
		//dev.setVelocity(Integer.parseInt(devVelocity));
		//developerDAO.updateDeveloper(dev);
		
		servicioDeveloper.editarDeveloper(Integer.parseInt(devId), devFirstName, devLastName, Integer.parseInt(devVelocity));
		
		ArrayList<DeveloperImpl> developers = servicioDeveloper.buscarDevelopers();
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String json = new Gson().toJson(developers);
		out.println(json);
		out.close();
		
	}

	protected void eliminarDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String developerId = request.getParameter("id");
		
		//DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		//ArrayList<DeveloperImpl> developers = developerDAO.getDeveloper(Integer.parseInt(developerId));
		//developerDAO.deleteDeveloper(developers.get(0));
		servicioDeveloper.eliminarDeveloper(Integer.parseInt(developerId));
		
		ArrayList<DeveloperImpl> developers = servicioDeveloper.buscarDevelopers();
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String json = new Gson().toJson(developers);
		out.println(json);
		out.close();
	}

	protected void exportarCSV(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.println(servicioDeveloper.exportarCSV());
		out.close();
		
	}
}
