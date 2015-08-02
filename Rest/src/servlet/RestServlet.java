package servlet;

import exception.RestException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import rest.handler.RestHandler;
import util.HttpMethod;
import util.XmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 16.05.14
 * User: andrey.tkachuk
 */
public class RestServlet extends HttpServlet {

    private Map<String, RestHandler> restHandlers = new HashMap<String, RestHandler>();

    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResourceAsStream("/WEB-INF/rest-handlers.xml")));
        try {
            Document xml = XmlUtil.parseToDocument(new InputSource(reader));
            NodeList handlers = xml.getElementsByTagName("handler");
            for (int i = 0; i < handlers.getLength(); i++) {
                Element handler = (Element) handlers.item(i);
                String path = handler.getAttribute("path");
                if (path == null) {
                    throw new ServletException("Missing path attribute on handler element");
                }
                String clazz = handler.getAttribute("class");
                if (clazz == null) {
                    throw new ServletException("Missing class attribute on handler element");
                }

                Class handlerClass = Class.forName(clazz);
                RestHandler restHandler = (RestHandler) handlerClass.newInstance();
                restHandler.init();
                restHandlers.put(path, restHandler);
                System.out.println("Created handler for path " + path);

            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex);
        } catch (InstantiationException ex) {
            throw new ServletException(ex);
        } catch (IllegalAccessException ex) {
            throw new ServletException(ex);
        } catch (RestException ex) {
            throw new ServletException(ex);
        } catch (SAXException ex) {
            throw new ServletException(ex);
        } catch (IOException ex) {
            throw new ServletException(ex);
        } catch (ParserConfigurationException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(HttpMethod.GET, request, response);
    }

    private void handleRequest(HttpMethod method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parsePath(request);
        System.out.println("Handling request for path: " + path);
        RestHandler handler = restHandlers.get(path);

        if (handler == null) {
            throw new ServletException("Missing handler for path " + path);
        }

        try {
            handler.handleOperation(method, request.getInputStream(), response.getOutputStream());
            System.out.println("Handled request for path: " + path + " OK");
        } catch (RestException ex) {
            throw new ServletException(ex);
        }
    }

    public void destroy() {
        for (RestHandler restHandler : restHandlers.values()) {
            try {
                restHandler.destroy();
            } catch (RestException e) {
                e.printStackTrace();
            }
        }
    }

    private String parsePath(HttpServletRequest request) {
        return request.getPathInfo();
    }
}
