package MyServlet;

import handle.data.SQLBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sava.data.WorkerBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Modify_servlet doPost starting...");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解决上传文件名的中文乱码
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
        System.out.println("update" + worker.getWorkerCode() + " 's data starting...");
        SQLBean.updateWorker(worker);
        System.out.println("Modify_servlet doPost finished...");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
