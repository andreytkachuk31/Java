package exception;

/**
 * Date: 17.05.14
 * User: andrey.tkachuk
 */
public class RestException extends Exception {

    public RestException() {
        super();
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

}
