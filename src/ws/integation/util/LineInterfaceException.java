package ws.integation.util;

import java.io.PrintWriter;
import java.io.StringWriter;


public class LineInterfaceException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LineInterfaceException(Throwable cause) {
		super(cause);
		StringWriter sw = new StringWriter();
		cause.printStackTrace(new PrintWriter(sw));
	}

	public LineInterfaceException(String errorMessage, Exception cause) {
		super(errorMessage, cause);
	}

	public LineInterfaceException(String message) {
		super(message);
	}

}
