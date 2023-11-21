package proyecto.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)

public class ServidorErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServidorErrorException() {
		// TODO Auto-generated constructor stub
	}

	public ServidorErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServidorErrorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ServidorErrorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServidorErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
