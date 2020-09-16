// To save as "<CATALINA_HOME>\webapps\helloservlet\WEB-INF\src\mypkg\EchoServlet.java"
package mypkg;
 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
 
public class HyperledgerServlet extends HttpServlet {
 
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws IOException, ServletException {
      // Set the response message's MIME type
      response.setContentType("text/html; charset=UTF-8");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
   }
}
