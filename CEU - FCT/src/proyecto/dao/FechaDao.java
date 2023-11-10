package proyecto.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import proyecto.modelo.Fecha;

public class FechaDao {

	public List <Fecha> consultarFechas (Connection conn, Integer anho, Integer evaluacion) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		List <Fecha> listaFechas = new ArrayList <Fecha>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from fechas where año = "+anho+" and evaluacion = "+evaluacion);
			while (rs.next()) {
				Fecha fechaDisponible = new Fecha ();
				fechaDisponible.setFecha(rs.getDate("fecha").toLocalDate());
				fechaDisponible.setanho(anho);
				fechaDisponible.setEvaluación(rs.getInt("evaluacion"));
				fechaDisponible.setdisponible(rs.getBoolean("disponibilidad"));
				listaFechas.add(fechaDisponible);
			}
			return listaFechas;
			
		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
				
			}
		
		}
		
		
		
		
	}
}
