package MyServlet;

import handle.data.SQLBean;
import net.sf.json.JSONObject;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class JudgeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("JudgeServlet doPost starting...");
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        String workerCode = request.getParameter("workerCode");
        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        if (SQLBean.findWorkerByCode(workerCode) != null) {
            json.put("CodeStatus", "false");
        } else {
            json.put("CodeStatus", "true");
        }
        out.println(json);
        out.close();
        System.out.println("JudgeServlet doPost ending...");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
