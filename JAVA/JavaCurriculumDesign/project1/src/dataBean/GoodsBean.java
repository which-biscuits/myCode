package dataBean;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class GoodsBean {

    private String goodsId;
    private Date registerTime;
    private String userId;
    private String goodsName;
    private String introduction;
    private Double goodsPrice;
    private int saleOut;
    private Date outTime;
    private String purchaserId;
    private String imageName;
    private File image;
    private String[] keyWords;
    private String receiveAddress;
    private int orCheck;
    private String FailReason;

    public GoodsBean(String goodsId, Date registerTime, String userId, String goodsName, String introduction,
                     Double goodsPrice, String imageName, File image, String[] keyWords) {
        this.goodsId = goodsId;
        this.registerTime = registerTime;
        this.userId = userId;
        this.goodsName = goodsName;
        this.introduction = introduction;
        this.goodsPrice = goodsPrice;
        this.imageName = imageName;
        this.image = image;
        this.keyWords = keyWords;
    }
    public GoodsBean() {}

    public String getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getSaleOut() {
        return saleOut;
    }
    public void setSaleOut(int saleOut) {
        this.saleOut = saleOut;
    }

    public Date getOutTime() {
        return outTime;
    }
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getPurchaserId() {
        return purchaserId;
    }
    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }

    public File getImage() {
        return image;
    }
    public void setImage(File image) {
        this.image = image;
    }

    public String[] getKeyWords() {
        return keyWords;
    }
    public void setKeyWords(String[] keyWords) {
        this.keyWords = keyWords;
    }

    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                ", registerTime=" + registerTime +
                ", userId='" + userId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", purchaserId='" + purchaserId + '\'' +
                ", keyWords=" + Arrays.toString(keyWords) +
                '}';
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public int getOrCheck() {
        return orCheck;
    }

    public void setOrCheck(int orCheck) {
        this.orCheck = orCheck;
    }

    public String getFailReason() {
        return FailReason;
    }

    public void setFailReason(String failReason) {
        FailReason = failReason;
    }
}
