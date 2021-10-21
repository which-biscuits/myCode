package  moon.sun;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Example6_1 extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置相应的MIME类型
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();	// 获得向用户发送数据的输出流
        out.println("<html><body bgcolor=#ffccff>");
        out.println("<h1>hello servlet!</h1>");
        out.println("</body></html>");
    }
}