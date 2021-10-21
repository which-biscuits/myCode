package userApplication.dataBean;

import com.sun.istack.internal.NotNull;

import java.util.Date;

public class MessageBean implements Comparable<MessageBean> {

    private String sendUserId;
    private String receiveUserId;
    private String goodsId;
    private Date sendTime;
    private int orRead;
    private String message;

    public MessageBean() {}
    public MessageBean(String sendUserId, String receiveUserId, String goodsId, Date sendTime, int orRead, String message) {
        this.sendUserId = sendUserId;
        this.receiveUserId = receiveUserId;
        this.goodsId = goodsId;
        this.sendTime = sendTime;
        this.orRead = orRead;
        this.message = message;
    }

    @Override
    public int compareTo(@NotNull MessageBean o) {
        Date b = ((MessageBean) o).getSendTime();

        // 当目标对象参数小于该对象参数时,返回 正整数
        // 当目标对象参数等于该对象参数时,返回 0
        return Long.compare(sendTime.getTime(), b.getTime());		// 当目标对象参数大于该对象参数时,返回 负整数
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
}
