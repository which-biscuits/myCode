package MyServlet;

import handle.data.SQLBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import sava.data.WorkerBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AddServlet doPost starting...");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        WorkerBean worker = new WorkerBean();
        try {
            List<FileItem> parseRequest = upload.parseRequest(request);
            if (parseRequest != null) {
                for (FileItem fileItem : parseRequest) {
                    // 判断fileItem是不是普通输入内容
                    if (fileItem.isFormField()) {
                        String fieldName = fileItem.getFieldName();
                        // 获得内容并防止乱码
                        String value = fileItem.getString("utf-8");
                        worker.setParameter(fieldName, value);
                    } else {
                        // 获得上传文件域中的内容
                        // 获得文件名
                        String fileName = fileItem.getName();
                        String type = fileName.substring(fileName.indexOf('.') + 1);
                        File file = new File(getServletContext().getRealPath("/PNG/"), worker.getWorkerCode() + "." + type);
                        if (file.exists()) {
                            file.delete();
                        }
                        fileItem.write(file);
                        worker.setParameter("workerPhoto", "/wzx/PNG/" + file.getName());
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ADD " + worker.getWorkerCode() + " 's data ending...");
        SQLBean.addWorker(worker);
        System.out.println("AddServlet doPost ending...");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
