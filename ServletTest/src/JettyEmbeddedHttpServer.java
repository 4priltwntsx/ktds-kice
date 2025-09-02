import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JettyEmbeddedHttpServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(HelloServlet.class, "/hello");

        server.setHandler(handler);

        server.start();
        server.join();
    }

    public static class HelloServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String requestURL = req.getRequestURL().toString();
            String requestURI = req.getRequestURI();
            String contextPath = req.getContextPath();
            String servletPath = req.getServletPath();
            String queryString = req.getQueryString();

            System.out.println("requestURL: " + requestURL);
            System.out.println("requestURI: " + requestURI);
            System.out.println("contextPath: " + contextPath);
            System.out.println("servletPath: " + servletPath);
            System.out.println("queryString: " + queryString);

            resp.setContentType("text/plain; charset=utf-8");
            resp.getWriter().write("[GET]Hello, Jetty Embedded Http Server");
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String requestURL = req.getRequestURL().toString();
            String requestURI = req.getRequestURI();
            String contextPath = req.getContextPath();
            String servletPath = req.getServletPath();
            String queryString = req.getQueryString();
            System.out.println("requestURL: " + requestURL);
            System.out.println("requestURI: " + requestURI);
            System.out.println("contextPath: " + contextPath);
            System.out.println("servletPath: " + servletPath);
            System.out.println("queryString: " + queryString);
            resp.setContentType("text/plain; charset=utf-8");
            resp.getWriter().write("[POST]Hello, Jetty Embedded!!!!");
        }
    }
}
