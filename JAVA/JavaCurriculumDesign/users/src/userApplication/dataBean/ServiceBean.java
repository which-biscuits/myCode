package userApplication.dataBean;

import com.sun.istack.internal.NotNull;

import java.util.Date;

public class ServiceBean implements Comparable<ServiceBean> {

    private String userId;
    private int orSend;
    private Date sendTime;
    private int orRead;
    private String message;
    private String goodsId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getOrSend() {
        return orSend;
    }

    public void setOrSend(int orSend) {
        this.orSend = orSend;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getOrRead() {
        return orRead;
    }

    public void setOrRead(int orRead) {
        this.orRead = orRead;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public int compareTo(@NotNull ServiceBean o) {
            Date b = ((ServiceBean) o).getSendTime();

            // 当目标对象参数小于该对象参数时,返回 正整数
            // 当目标对象参数等于该对象参数时,返回 0
            return Long.compare(sendTime.getTime(), b.getTime());		// 当目标对象参数大于该对象参数时,返回 负整数
    }
}
