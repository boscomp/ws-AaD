package proyecto.cliente;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import proyecto.modelo.Registro;

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
			String url = urlBase + "/registros?idUsuario={idUsuario}";
			
			Registro[] reg = restTemplate.getForObject(url, Registro[].class);
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
			String url = urlBase + "/registros";
			restTemplate.postForObject(url, registro, Registro.class);
	
			} catch (HttpStatusCodeException e){
				if (e.getStatusCode()== HttpStatus.INTERNAL_SERVER_ERROR) {
					throw new ServidorErrorException("Error en el servidor");
				}
				throw e;
			}
		}
}
