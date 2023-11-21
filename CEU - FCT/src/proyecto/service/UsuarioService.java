package proyecto.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proyecto.dao.UsuarioDao;
import proyecto.db.OpenConnection;
import proyecto.modelo.Usuario;

@RestController
public class UsuarioService {
	private OpenConnection connProvider;

	public UsuarioService() {
		connProvider = new OpenConnection();
	}

	@GetMapping("/login")
	public Usuario login(@RequestParam String email, @RequestParam String pass) throws AutenticarException, FCTException {
		Usuario user = new Usuario();
		UsuarioDao userD = new UsuarioDao();
		Connection conn = null;

		try {
			conn = connProvider.getNewConnection();
			user = userD.consultarUsuario(conn, email);
			if (user == null) {
				throw new AutenticarException("El email no es válido");
			}
			if (!user.getContraseña().equals(pass)) {
				throw new AutenticarException("La contraseña no es válida");
			} else {
				return user;
			}
		} catch (SQLException e) {

			throw new FCTException("Error en la base de datos", e);
		}

		finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}

	}
@PostMapping("/usuario")
	public void altaUsuario(@RequestBody Usuario usuario) throws FCTException {
		Connection conn = null;
		try {
			UsuarioDao userD = new UsuarioDao();
			Usuario user = new Usuario();
			conn = connProvider.getNewConnection();
			user = userD.consultarUsuario(conn, usuario.getEmail());
			if (user == null) {
				userD.insertarUsuario(conn, usuario);
			}
			
		} catch (SQLException e) {

			throw new FCTException("Error en la base de datos", e);
		}

		finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

}
