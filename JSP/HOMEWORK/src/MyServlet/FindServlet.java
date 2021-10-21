package MyServlet;

import handle.data.SQLBean;
import net.sf.json.JSONObject;
import sava.data.WorkerBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FindServlet doPost starting...");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        String status = request.getParameter("status");
        if (status.equals("find")) {
            System.out.println("do find workers...");
            String siteName = request.getParameter("siteName");
            String workerName = request.getParameter("workerName");
            if (siteName == null || workerName == null)
                return;
            HttpSession session = request.getSession();
            List<String[]> list =  SQLBean.findWorker(siteName, workerName);
            if (list.size() != 0) {
                session.setAttribute("list",list);
                session.setAttribute("findStatus","true");
            } else {
                session.setAttribute("findStatus", "false");
            }
        } else if (status.equals("modify_find")){
            System.out.println("do modify_find workers...");
            response.setContentType("text/html");
            String workerCode = request.getParameter("workerCode");
            WorkerBean worker = SQLBean.findWorkerByCode(workerCode);
            PrintWriter out = response.getWriter();
            JSONObject json=new JSONObject();
            if (worker != null) {
                json.put("status", "true");
                json.put("siteName", worker.getSiteName());
                json.put("workerCode", worker.getWorkerCode());
                json.put("workerName", worker.getWorkerName());
                json.put("workerSex", worker.getWorkerSex());
                json.put("workerAddress", worker.getWorkerAddress());
                json.put("workerPhone", worker.getWorkerPhone());
                json.put("workerJob", worker.getWorkerJob());
                json.put("workerPosition", worker.getWorkerPosition());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                json.put("workerBirthday", dateFormat.format(worker.getWorkerBirthday()));
                json.put("registerTime", dateFormat.format(worker.getRegisterTime()));
                json.put("workerPhoto",worker.getWorkerPhoto());
            } else {
                json.put("status", "false");
            }
            out.println(json);
            out.close();
        } else {
            System.out.println("FindServlet has status error !");
        }
        System.out.println("FindServlet doPost ending...");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
