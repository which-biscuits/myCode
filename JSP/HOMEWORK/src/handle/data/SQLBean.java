package handle.data;
import java.sql.*;
import java.util.*;
import sava.data.*;

// 用于与数据库的链接
public class SQLBean {
    private final static String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
    private final static String URL = "jdbc:jtds:sqlserver://localhost:1433/site";
    private final static String USERID = "test";
    private final static String USERPASSWORD = "123456";
    private SQLBean() {}    // 禁止实例化
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USERID,USERPASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    // 添加工人信息
    public synchronized static void addWorker(WorkerBean worker) {
        String sql = "insert into WorkerInfo(siteName, workerCode, workerName, workerSex, workerBirthday, workerAddress," +
                "workerPhone, workerJob, workerPosition, registerTime, workerPhoto) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = SQLBean.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, worker.getSiteName());
            st.setString(2, worker.getWorkerCode());
            st.setString(3, worker.getWorkerName());
            st.setString(4, worker.getWorkerSex());
            st.setDate(5, worker.getWorkerBirthday());
            st.setString(6, worker.getWorkerAddress());
            st.setString(7, worker.getWorkerPhone());
            st.setString(8, worker.getWorkerJob());
            st.setString(9, worker.getWorkerPosition());
            st.setDate(10, worker.getRegisterTime());
            st.setString(11, worker.getWorkerPhoto());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 删除工人信息
    public synchronized static void removeWorker(String workerCode) {
        String sql = "delete from WorkerInfo where workerCode=?";
        try (Connection conn = SQLBean.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,workerCode);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 更改工人信息
    public synchronized static void updateWorker(WorkerBean worker) {
        if (worker.getWorkerPhoto() != null) {
            String sql = "update WorkerInfo set siteName=?, workerSex=?, workerBirthday=?, workerAddress=?, workerPhone=?, " +
                    "workerJob=?, workerPosition=?, registerTime=?, workerPhoto=? where workerCode=? and workerName=?";
            try (Connection conn = SQLBean.getConnection()) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, worker.getSiteName());
                st.setString(2, worker.getWorkerSex());
                st.setDate(3, worker.getWorkerBirthday());
                st.setString(4, worker.getWorkerAddress());
                st.setString(5, worker.getWorkerPhone());
                st.setString(6, worker.getWorkerJob());
                st.setString(7, worker.getWorkerPosition());
                st.setDate(8, worker.getRegisterTime());
                st.setString(9, worker.getWorkerPhoto());
                st.setString(10, worker.getWorkerCode());
                st.setString(11, worker.getWorkerName());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "update WorkerInfo set siteName=?, workerSex=?, workerBirthday=?, workerAddress=?, workerPhone=?, " +
                    "workerJob=?, workerPosition=?, registerTime=? where workerCode=? and workerName=?";
            try (Connection conn = SQLBean.getConnection()) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, worker.getSiteName());
                st.setString(2, worker.getWorkerSex());
                st.setDate(3, worker.getWorkerBirthday());
                st.setString(4, worker.getWorkerAddress());
                st.setString(5, worker.getWorkerPhone());
                st.setString(6, worker.getWorkerJob());
                st.setString(7, worker.getWorkerPosition());
                st.setDate(8, worker.getRegisterTime());
                st.setString(9, worker.getWorkerCode());
                st.setString(10, worker.getWorkerName());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 查询工人信息
    public static List<String[]> findWorker(String siteName, String workerName) {
        List<String[]> workers = new LinkedList<>();
        String sql = "select * from WorkerInfo where siteName=? and workerName=?";
        try (Connection conn = SQLBean.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,siteName);
            st.setString(2,workerName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next())
                    workers.add(WorkerBean.toWorker(rs).toList());
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }
    // 通过 ID 查询工人信息
    public static WorkerBean findWorkerByCode(String workerCode) {
        String sql = "select * from WorkerInfo where workerCode=?";
        try (Connection conn = SQLBean.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,workerCode);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next())
                    return WorkerBean.toWorker(rs);
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 查询所有工人信息
    public static List<String[]> findWorkers() {
        List<String[]> workers = new LinkedList<>();
        String sql = "select * from WorkerInfo";
        try (Connection conn = SQLBean.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next())
                    workers.add(WorkerBean.toWorker(rs).toList());
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }
}

