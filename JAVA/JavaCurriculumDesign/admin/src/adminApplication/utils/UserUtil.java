package adminApplication.utils;

import adminApplication.dataBean.UserBean;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        user.setOrLog(rs.getInt("orLog"));
        return user;
    }
}
