package proyecto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import proyecto.modelo.Registro;

public class RegistroDao {

	public List <Registro> consultarRegistro (Connection conn, Long idUsuario) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		List <Registro> listaRegistros = new ArrayList <Registro>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from registros where id_usuario = "+idUsuario);
			while (rs.next()) {
				Registro registroDisponible = new Registro ();
				registroDisponible.setIdRegistro(rs.getLong("id_registro"));
				registroDisponible.setIdUsuario(rs.getLong("id_usuario"));
				registroDisponible.setFecha(rs.getDate("fecha").toLocalDate());
				registroDisponible.setNumHoras(rs.getBigDecimal("num_horas"));
				registroDisponible.setDescripcion(rs.getString("descripcion"));
				
				listaRegistros.add(registroDisponible);
			}
			return listaRegistros;
			
		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
				
			}
		
		}
	}
	
	public void nuevoRegistro (Connection conn, Registro registro) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "insert into registros (id_usuario, fecha, num_horas, descripcion) values (?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, registro.getIdUsuario());
			
			stmt.setDate(2, Date.valueOf(registro.getFecha()));
			stmt.setBigDecimal(3, registro.getNumHoras());
			stmt.setString(4, registro.getDescripcion());
			stmt.execute();
		}
		finally {
			try {
				stmt.close();
			}catch(Exception ignore) {}
		}
	}
}
