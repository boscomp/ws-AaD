package proyecto.cliente;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import proyecto.modelo.Fecha;
import proyecto.modelo.Registro;
import proyecto.modelo.Usuario;
import proyecto.service.AutenticarException;
import proyecto.service.FCTException;

public class ProyectoCliente {
	private String urlBase;
	private RestTemplate restTemplate;
	
	public ProyectoCliente(String urlBase, Integer msTimeout) {
		this.urlBase = urlBase;
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(msTimeout);
		this.restTemplate = new RestTemplate(requestFactory);
	}
	
	public List<Registro> consultarRegistrosUsuario(Long idUsuario) throws ServidorErrorException, RegistroNoEncontradoException{
		
		try {
			String url = urlBase + "/registro/{idUsuario}";
			
			Registro[] reg = restTemplate.getForObject(url, Registro[].class, idUsuario);
			return Arrays.asList(reg);
			
		}
		catch (HttpStatusCodeException e){
			if (e.getStatusCode()== HttpStatus.NOT_FOUND) {
				throw new RegistroNoEncontradoException("No existen registros para ese cliente");
			}
			if (e.getStatusCode()== HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new ServidorErrorException("Error en el servidor");
			}
			throw e;
		}
	
	}
	public void crearRegistro(Registro registro) throws ServidorErrorException {
		try {
			String url = urlBase + "/registro";
			restTemplate.postForObject(url, registro, Registro.class);
	
			} catch (HttpStatusCodeException e){
				
				throw new ServidorErrorException("Error en el servidor");
			}
		}
	
	public Usuario login(String email, String pass) throws ServidorErrorException, AutenticarException {
		Usuario user = null;
		try {
		String url = urlBase + "/login?email={email}&pass={pass}";
		return restTemplate.getForObject(url,  Usuario.class, email, pass);
	} catch (HttpStatusCodeException e){
		if (e.getStatusCode()== HttpStatus.FORBIDDEN) {
			throw new AutenticarException("Daos incorrectos en el login");
		}
		if (e.getStatusCode()== HttpStatus.INTERNAL_SERVER_ERROR) {
			throw new ServidorErrorException("Error en el servidor");
		}
		throw e;
	}
		

	}
	
	public void altaUsuario(Usuario user ) throws ServidorErrorException {
		try {
		String url = urlBase + "/usuario";
		restTemplate.postForObject(url, user, Usuario.class);
		} catch (HttpStatusCodeException e){
			if (e.getStatusCode()== HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new ServidorErrorException("Error en el servidor");
			}
		}
	}
	
	public List<Fecha> consultarFechas() throws ServidorErrorException {
		try {
			String url=urlBase+"/fechas";
			Fecha[] fecha = restTemplate.getForObject(url, Fecha[].class);
			return Arrays.asList(fecha);
		} catch (HttpStatusCodeException e){
			if (e.getStatusCode()== HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new ServidorErrorException("Error en el servidor");
			}
			throw e;
		}
	}
}
