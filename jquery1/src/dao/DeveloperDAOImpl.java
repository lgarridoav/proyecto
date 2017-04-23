package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DBConnection;
import model.DeveloperImpl;

public class DeveloperDAOImpl {
	
	public ArrayList<DeveloperImpl> getDeveloper(int id) {
		ArrayList<DeveloperImpl> myDevelopers = new ArrayList<DeveloperImpl>();
		DBConnection conex= new DBConnection();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM developer where id = ? ");
			consulta.setInt(1, id);
			ResultSet res = consulta.executeQuery();

			if(res.next()){
				DeveloperImpl developer= new DeveloperImpl();
				developer.setId(Integer.parseInt(res.getString("id")));
				developer.setFirstName(res.getString("firstName"));
				developer.setLastName(res.getString("lastName"));
				developer.setVelocity(Integer.parseInt(res.getString("velocity")));
				myDevelopers.add(developer);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
		}
		return myDevelopers;
	}
	
	public void saveDeveloper(DeveloperImpl developer) 
	{
		DBConnection conex= new DBConnection();
		try {
			Statement statement = conex.getConnection().createStatement();
			statement.executeUpdate("INSERT INTO developer (firstName, lastName, velocity) VALUES ('"
					+developer.getFirstName()+"', '"+developer.getLastName()+"', "
					+developer.getVelocity()+")");
			statement.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateDeveloper(DeveloperImpl developer) 
	{
		DBConnection conex= new DBConnection();
		try {
			Statement statement = conex.getConnection().createStatement();
			statement.executeUpdate("UPDATE developer SET firstName='"+developer.getFirstName()+"', lastname='"
					+developer.getLastName()+"', velocity="+developer.getVelocity()+" WHERE id="+developer.getId()+" ");
			statement.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteDeveloper(DeveloperImpl developer) 
	{
		DBConnection conex= new DBConnection();
		try {
			Statement statement = conex.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM developer WHERE id ="+developer.getId()+"");
			statement.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	 
	public ArrayList<DeveloperImpl> listDevelopers() {
		ArrayList<DeveloperImpl> myDevelopers = new ArrayList<DeveloperImpl>();
		DBConnection conex= new DBConnection();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM developer");
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				DeveloperImpl developer= new DeveloperImpl();
				developer.setId(Integer.parseInt(res.getString("id")));
				developer.setFirstName(res.getString("firstName"));
				developer.setLastName(res.getString("lastName"));
				developer.setVelocity(Integer.parseInt(res.getString("velocity")));
				myDevelopers.add(developer);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
		}
		return myDevelopers;
	}
	
}
