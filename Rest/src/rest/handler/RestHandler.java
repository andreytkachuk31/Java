package rest.handler;

import exception.RestException;
import util.HttpMethod;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Date: 17.05.14
 * User: andrey.tkachuk
 */
public interface RestHandler {

    /*
     * Lifecycle method for initializing the handler instance
     */
    void init() throws RestException;

    /*
     * Handles the access to a resource
     */
    void handleOperation(HttpMethod method, InputStream inputStream, OutputStream outputStream) throws RestException;

    /*
     * Lifecycle method for destroying the handler instance
     */
    void destroy() throws RestException;

}
