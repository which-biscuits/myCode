package userApplication.utils;

import userApplication.dataBean.ServiceBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ServiceUtil {

    public static ServiceBean ResultToService(ResultSet rs) throws SQLException {
        ServiceBean service = new ServiceBean();
        service.setUserId(rs.getString("userId"));
        service.setOrSend(rs.getInt("orSend"));
        service.setSendTime(rs.getTimestamp("sendTime"));
        service.setOrRead(rs.getInt("orRead"));
        service.setMessage(rs.getString("message"));
        service.setGoodsId(rs.getString("goodsId"));

        return service;
    }

    public static void ServiceAddToSQL(PreparedStatement st, ServiceBean service) throws SQLException {
        st.setString(1, service.getUserId());
        st.setInt(2, service.getOrSend());
        st.setTimestamp(3, new Timestamp(service.getSendTime().getTime()));
        st.setInt(4, service.getOrRead());
        st.setString(5, service.getMessage());
        st.setString(6, service.getGoodsId());
    }
}
