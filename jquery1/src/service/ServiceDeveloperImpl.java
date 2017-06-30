package service;

import java.util.ArrayList;

import model.DeveloperImpl;
import dao.DeveloperDAOImpl;

public class ServiceDeveloperImpl {

	public static String SALTO_LINEA = System.getProperty("line.separator");
	
	public ArrayList<DeveloperImpl> buscarDevelopers() {
		
		DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		ArrayList<DeveloperImpl> developers = developerDAO.listDevelopers();
		
		return developers;
	}

	public DeveloperImpl agregarDeveloper(String firstName, String lastName, Integer velocity) {
		
		DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		DeveloperImpl dev = new DeveloperImpl();
		dev.setFirstName(firstName);
		dev.setLastName(lastName);
		dev.setVelocity(velocity);
		developerDAO.saveDeveloper(dev);
		
		return dev;
	}

	public DeveloperImpl editarDeveloper(Integer id, String firstName, String lastName, Integer velocity) {

		DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		ArrayList<DeveloperImpl> developers = developerDAO.getDeveloper(id);
		DeveloperImpl dev = developers.get(0);
		
		dev.setFirstName(firstName);
		dev.setLastName(lastName);
		dev.setVelocity(velocity);
		developerDAO.updateDeveloper(dev);
		
		return dev;
	}

	public void eliminarDeveloper(Integer id) {
		
		DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
		ArrayList<DeveloperImpl> developers = developerDAO.getDeveloper(id);
		developerDAO.deleteDeveloper(developers.get(0));
	}
	
	public String exportarCSV() {
		
		String fichero ="";
		ArrayList<DeveloperImpl> listaDevelopers = buscarDevelopers();
		 
        for(DeveloperImpl dev : listaDevelopers){
        	fichero+="\""+dev.getId().toString()+"\",";
        	fichero+="\""+dev.getFirstName()+"\",";
        	fichero+="\""+dev.getLastName()+"\",";
        	fichero+="\""+dev.getVelocity().toString()+"\""+SALTO_LINEA;
        }
        
		return fichero;
	}
}
