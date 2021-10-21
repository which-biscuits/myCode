package userApplication.utils;

import userApplication.dataBean.UserBean;

import java.sql.*;

public class UserUtil {


    public static UserBean ResultToUser(ResultSet rs) throws SQLException {
        UserBean user = new UserBean();
        user.setUserId(rs.getString("userId"));
        user.setPassword(rs.getString("password"));
        user.setUserName(rs.getString("userName"));
        user.setUserSex(rs.getInt("userSex"));
        user.setUserEmail(rs.getString("userEmail"));
        user.setUserPhone(rs.getString("userPhone"));
        user.setUserBirthday(rs.getTimestamp("userBirthday"));
        user.setRegisterTime(rs.getTimestamp("registerTime"));
        user.setMoney(rs.getDouble("money"));
        return user;
    }

    public static void UserAddToSQL(PreparedStatement st, UserBean user) throws SQLException {
        st.setString(1, user.getUserId());
        st.setString(2, user.getPassword());
        st.setString(3, user.getUserName());
        st.setInt(4, user.getUserSex());
        st.setString(5, user.getUserEmail());
        st.setString(6, user.getUserPhone());
        st.setTimestamp(7, new Timestamp(user.getUserBirthday().getTime()));
        st.setTimestamp(8, new Timestamp(user.getRegisterTime().getTime()));
    }
}
