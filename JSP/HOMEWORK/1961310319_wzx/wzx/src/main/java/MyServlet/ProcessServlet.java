package MyServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "processServlet")
public class ProcessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ProcessServlet doPost starting...");
        request.setCharacterEncoding("utf-8");
        String status = request.getParameter("status");
        switch (status) {
            case "find":
                status = "findServlet";
                break;
            case "add":
                status = "addServlet";
                break;
            case "judgeCode":
                status = "judgeServlet";
                break;
            case "delete":
                status = "deleteServlet";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(status);
        dispatcher.forward(request, response);
        System.out.println("ProcessServlet doPost ending...");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
