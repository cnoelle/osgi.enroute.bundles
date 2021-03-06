package osgi.enroute.rest.api;

import java.net.URI;

/**
 * A class to represent a response for a REST request. Instances can be thrown
 * since it extends exceptions.
 * 
 * The intention of this class is to create a ReponseObject in OpenAPI
 * (swagger). All information is available through reflection. For this reason,
 * a subclass should not have any sideeffects when instantiated (and it must
 * have an empty constructor).
 *
 * A subclass can add public fields that are treated as response headers. In the
 * field names the '_' is treated as a dash ('-'). Any other ASCII character
 * (including the underscore) can be created with hex encoding prefixed with a
 * dollar, e.g. foo$5Fbar is foo_bar.
 */
public class RESTResponse extends Exception {
	static final long		serialVersionUID	= 1L;

	private final int		statusCode;
	private final Object	value;
	private final String	contentType;

	public RESTResponse() {
		this(null, 200, null, null);
	}

	public RESTResponse(String message, int statusCode) {
		this(null, statusCode, null, null);
	}

	public RESTResponse(int statusCode, Object value) {
		this(null, 200, value, null);
	}

	public RESTResponse(Object value) {
		this(200, value);
	}

	public RESTResponse(int statusCode) {
		this(null, statusCode, null, null);
	}

	public RESTResponse(int statusCode, Object value, String contentType) {
		this(null, statusCode, value, contentType);
	}

	public RESTResponse(String message, int statusCode, Throwable cause) {
		this(message, statusCode, null, null, cause);
	}

	public RESTResponse(String message, int statusCode, Object value,
			String contentType, Throwable cause) {
		super(message == null ? statusCode + "" : message, cause);
		this.statusCode = statusCode;
		this.value = value;
		this.contentType = contentType;
	}

	public RESTResponse(String message, int statusCode, Object value,
			String contentType) {
		super(message == null ? statusCode + "" : message);
		this.statusCode = statusCode;
		this.value = value;
		this.contentType = contentType;

	}

	public int getStatusCode() {
		return statusCode;
	}

	public Object getValue() {
		return value;
	}

	public String getContentType() {
		return contentType;
	}

	/**
	 * Add public fields in the subclass as headers.
	 */

	// ...

	/**
	 * Redirect
	 *
	 */

	public static class Redirect extends RESTResponse {
		private static final long serialVersionUID = 1L;

		public Redirect(int code, URI uri) {
			super(code);
			assert code /100 == 3;
			this.Location = uri;
		}

		public URI Location;
	}

	/**
	 * Teapot
	 *
	 */

	public static class IamATeapot extends RESTResponse {
		private static final long serialVersionUID = 1L;

		public IamATeapot() {
			super("I am a Teapot!", 418);
		}
	}

}
