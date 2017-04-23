package connection;

import java.sql.*;

public class DBConnection {

	// Parametros de conexion
	static String bd = "colegio";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost:3306/"+bd;

	Connection connection = null;

	// Constructor de DBConnection
	public DBConnection() {
		try{
			//obtenemos el driver de para mysql
			Class.forName("com.mysql.jdbc.Driver");
			//obtenemos la conexión
			connection = DriverManager.getConnection(url,login,password);

			if (connection!=null){
				System.out.println("Conexión a base de datos "+bd+" OK\n");
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(Exception e){
			System.out.println(e);
		}
	}

	// Devuelve la conexión
	public Connection getConnection(){
		return connection;
	}

	public void desconectar(){
		connection = null;
	}
}