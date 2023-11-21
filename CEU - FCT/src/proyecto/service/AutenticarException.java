package proyecto.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AutenticarException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutenticarException() {
	}

	public AutenticarException(String message) {
		super(message);

	}

	public AutenticarException(Throwable cause) {
		super(cause);

	}

	public AutenticarException(String message, Throwable cause) {
		super(message, cause);

	}

	public AutenticarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
