package proyecto.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FCTException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FCTException() {
	}

	public FCTException(String message) {
		super(message);

	}

	public FCTException(Throwable cause) {
		super(cause);

	}

	public FCTException(String message, Throwable cause) {
		super(message, cause);

	}

	public FCTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
