package rest.handler;

import exception.RestException;
import util.HttpMethod;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Date: 17.05.14
 * User: andrey.tkachuk
 */
public class TestRestHandler implements RestHandler {
    @Override
    public void init() throws RestException {
        System.out.println("init-ed OK");
    }

    @Override
    public void handleOperation(HttpMethod method, InputStream inputStream, OutputStream outputStream) throws RestException {
        switch (method) {
            case GET:
                String data = createSimpleXmlData();
                try {
                    outputStream.write(data.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new IllegalArgumentException("No that method " + method);
        }
    }

    @Override
    public void destroy() throws RestException {
        System.out.println("destroy-ed OK");
    }

    private String createSimpleXmlData() {
        StringBuilder result = new StringBuilder();
        result.append("<?xml version='1.0' encoding='utf-8'?>");
        result.append("<info>");
        result.append("<firstName>Andrey</firstName>");
        result.append("<lastName>Tkachuk</lastName>");
        result.append("<age>22</age>");
        result.append("</info>");
        return result.toString();
    }
}
