package adminApplication.utils;

import adminApplication.dataBean.AdminBean;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUtil {

    public static void AdminAddToSQL(PreparedStatement st, AdminBean admin) throws SQLException {
        st.setString(1, admin.getAdminId());
        st.setString(2, admin.getPassword());
        st.setString(3, admin.getAdminName());
        st.setInt(4, admin.getAdminSex());
        st.setString(5, admin.getAdminEmail());
        st.setString(6, admin.getAdminPhone());
        st.setDate(7, new Date(admin.getRegisterTime().getTime()));
    }

    public static AdminBean ResultToAdmin(ResultSet rs) throws SQLException {
        AdminBean admin = new AdminBean();
        admin.setAdminId(rs.getString("adminId"));
        admin.setPassword(rs.getString("password"));
        admin.setAdminName(rs.getString("adminName"));
        admin.setAdminSex(rs.getInt("adminSex"));
        admin.setAdminEmail(rs.getString("adminEmail"));
        admin.setAdminPhone(rs.getString("adminPhone"));
        admin.setRegisterTime(rs.getTimestamp("registerTime"));
        return admin;
    }
}
