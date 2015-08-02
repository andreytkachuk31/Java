package servlets;

import ejb.HelloWorldEJB;
import ejb.HelloWorldEJBHome;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date: 23.07.14
 * User: andrey.tkachuk
 */
public class Ejb2Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // EJB 2 session
            HelloWorldEJBHome helloWorldEJBHome = (HelloWorldEJBHome) new InitialContext().lookup("HelloWorldEJBHome");
            HelloWorldEJB helloWorldEjb = helloWorldEJBHome.create();
            response.getWriter().println("EJB session test : " + helloWorldEjb.helloWorld());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
