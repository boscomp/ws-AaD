package proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proyecto.modelo.Usuario;

public class UsuarioDao {

	public Long insertarUsuario(Connection conn, Usuario user) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "insert into usuarios (email, password, nombre, apellidos, ciclo, activo) values (?,?,?,?,?,?)";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getContraseña());
			stmt.setString(3, user.getNombre());
			stmt.setString(4, user.getApellidos());
			stmt.setString(5, user.getCiclo());
			stmt.setBoolean(6, user.getActivo());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getLong(1);
		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}

	public Usuario consultarUsuario(Connection conn, String email) throws SQLException {
		Usuario user = new Usuario();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from usuarios where email = '" + email + "'");

			if (rs.next()) {
				user.setIdUsuario(rs.getLong("id_usuario"));
				user.setEmail(rs.getString("email"));
				user.setContraseña(rs.getString("password"));
				user.setNombre(rs.getString("nombre"));
				user.setApellidos(rs.getString("apellidos"));
				user.setCiclo(rs.getString("ciclo"));
				user.setActivo(rs.getBoolean("activo"));
				return user;
			} else {
				return null;
			}
		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {

			}

		}
	}

}
