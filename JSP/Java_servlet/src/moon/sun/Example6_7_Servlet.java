package moon.sun;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Example6_7_Servlet extends HttpServlet{
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        request.setCharacterEncoding("utf-8");
        dispatcher.forward(request, response);
    }
}
