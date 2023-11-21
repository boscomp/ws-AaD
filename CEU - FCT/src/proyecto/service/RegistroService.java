package proyecto.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proyecto.dao.RegistroDao;
import proyecto.db.OpenConnection;
import proyecto.modelo.Registro;


@RestController
public class RegistroService {

	private RegistroDao dao;
	private OpenConnection connProvider;

	public RegistroService() {
		dao = new RegistroDao();
		connProvider = new OpenConnection();
	}

	@GetMapping("/registro/{idUsuario}")
	public List<Registro> consultarRegistrosUsuario(@PathVariable Long idUsuario) throws FCTException {
		Connection conn = null;
		try {
			conn = connProvider.getNewConnection();
			return dao.consultarRegistro(conn, idUsuario);
		} catch (SQLException e) {
			System.err.println("Error al consultar registros");
			throw new FCTException("Error al consultar registros en BBDD", e);
		} finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}
	
	@PutMapping("/registro")
	public void crearRegistro(@RequestBody Registro registro) throws FCTException {
		Connection conn = null;
		try {
			conn = connProvider.getNewConnection();
			List<Registro> registrosPrevios = dao.consultarRegistro(conn, registro.getIdUsuario());
			for (Registro previo : registrosPrevios) {
				if (previo.getFecha().equals(registro.getFecha())) {
					throw new FCTException("Ya existe un registro para ese usuario en esa fecha");
				}
			}
			dao.nuevoRegistro(conn, registro);
		} catch (SQLException e) {
			System.err.println("Error al insertar registro");
			throw new FCTException("Error al insertar registro en BBDD", e);
		} finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

}