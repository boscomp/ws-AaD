package proyecto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OpenConnection {

	public Connection getNewConnection() throws SQLException {
		String urlConexion = "jdbc:mariadb://localhost:3306/ceu-fct";
		String claseDriver = "org.mariadb.jdbc.Driver";
		String usuario = "fct";
		String password = "fct";

		try {
			Class.forName(claseDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Connection conn = DriverManager.getConnection(urlConexion, usuario, password);
		return DriverManager.getConnection(urlConexion, usuario, password);
	}

}
