package utils;


import dataBean.MessageBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public class MessageUtil {

    public static MessageBean ResultToMessage(ResultSet rs) throws SQLException {
        MessageBean message = new MessageBean();
        message.setSendUserId(rs.getString("sendUserId"));
        message.setReceiveUserId(rs.getString("receiveUserId"));
        message.setGoodsId(rs.getString("goodsId"));
        message.setSendTime(rs.getTimestamp("sendTime"));
        message.setOrRead(rs.getInt("orRead"));
        message.setMessage(rs.getString("message"));

        return message;
    }

    public static void MessageAddToSQL(PreparedStatement st, MessageBean message) throws SQLException {
        st.setString(1, message.getSendUserId());
        st.setString(2, message.getReceiveUserId());
        st.setString(3, message.getGoodsId());
        st.setTimestamp(4, new Timestamp(message.getSendTime().getTime()));
        st.setInt(5, message.getOrRead());
        st.setString(6, message.getMessage());
    }
}
