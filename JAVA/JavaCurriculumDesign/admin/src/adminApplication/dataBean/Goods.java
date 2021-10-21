package adminApplication.dataBean;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Goods {

    private final SimpleStringProperty goodsId;
    private final SimpleStringProperty goodsName;
    private final SimpleStringProperty introduction;
    private final SimpleDoubleProperty goodsPrice;
    private final SimpleStringProperty keyWords;
    private final SimpleStringProperty orCheck;
    private final SimpleStringProperty failReason;
    private final SimpleStringProperty saleOut;
    private final SimpleStringProperty outTime;
    private final SimpleStringProperty purchaserId;
    private final SimpleStringProperty receiveAddress;

    public Goods(GoodsBean goods) {
        this.goodsId = new SimpleStringProperty(goods.getGoodsId());
        this.goodsName = new SimpleStringProperty(goods.getGoodsName());
        this.introduction = new SimpleStringProperty(goods.getIntroduction());
        this.goodsPrice = new SimpleDoubleProperty(goods.getGoodsPrice());
        this.keyWords = new SimpleStringProperty(String.join(",", goods.getKeyWords()));
        this.orCheck = new SimpleStringProperty(goods.getOrCheck() == 0 ? "待审核" : (goods.getOrCheck() == 1 ? "审核通过" : "审核未通过"));
        this.failReason = new SimpleStringProperty(goods.getOrCheck() == 1 ? null : goods.getFailReason());
        this.saleOut = new SimpleStringProperty(goods.getSaleOut() == 0 ? "未售出" : "已售出");
        this.outTime = new SimpleStringProperty(goods.getOutTime() == null ? null : goods.getOutTime().toString());
        this.purchaserId = new SimpleStringProperty(goods.getPurchaserId());
        this.receiveAddress = new SimpleStringProperty(goods.getReceiveAddress());
    }

    public String getGoodsId() {
        return goodsId.get();
    }

    public SimpleStringProperty goodsIdProperty() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId.set(goodsId);
    }

    public String getGoodsName() {
        return goodsName.get();
    }

    public SimpleStringProperty goodsNameProperty() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName.set(goodsName);
    }

    public String getIntroduction() {
        return introduction.get();
    }

    public SimpleStringProperty introductionProperty() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction.set(introduction);
    }

    public double getGoodsPrice() {
        return goodsPrice.get();
    }

    public SimpleDoubleProperty goodsPriceProperty() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice.set(goodsPrice);
    }

    public String getKeyWords() {
        return keyWords.get();
    }

    public SimpleStringProperty keyWordsProperty() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords.set(keyWords);
    }

    public String getOrCheck() {
        return orCheck.get();
    }

    public SimpleStringProperty orCheckProperty() {
        return orCheck;
    }

    public void setOrCheck(String orCheck) {
        this.orCheck.set(orCheck);
    }

    public String getFailReason() {
        return failReason.get();
    }

    public SimpleStringProperty failReasonProperty() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason.set(failReason);
    }

    public String getSaleOut() {
        return saleOut.get();
    }

    public SimpleStringProperty saleOutProperty() {
        return saleOut;
    }

    public void setSaleOut(String saleOut) {
        this.saleOut.set(saleOut);
    }

    public String getOutTime() {
        return outTime.get();
    }

    public SimpleStringProperty outTimeProperty() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime.set(outTime);
    }

    public String getPurchaserId() {
        return purchaserId.get();
    }

    public SimpleStringProperty purchaserIdProperty() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
        this.purchaserId.set(purchaserId);
    }

    public String getReceiveAddress() {
        return receiveAddress.get();
    }

    public SimpleStringProperty receiveAddressProperty() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress.set(receiveAddress);
    }
}
