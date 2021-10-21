package userApplication.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import userApplication.dataBean.*;
import userApplication.utils.*;

public class justBuyItController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new setView(CacheDataBean.getLookingGoods(), CacheDataBean.getNowUser())).start();
    }

    @FXML
    void cancelOnClick(ActionEvent event) {
        CacheDataBean.getWorkingPane().getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/userApplication/resource/fxml/discovery.fxml");
        if (CacheDataBean.getStatus() == StatusBean.OnIntroduction) {
            in = getClass().getResourceAsStream("/userApplication/resource/fxml/introduction.fxml");
        }

        AnchorPane page = null; // 对象方法的参数是InputStream，返回值是Object
        try {
            page = loader.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheDataBean.getWorkingPane().getChildren().add(page);
    }

    @FXML
    void confirmForBuyOnClick(ActionEvent event) {
        GoodsBean goods = CacheDataBean.getLookingGoods();
        assert goods != null;
        if (goods.getSaleOut() == 1) {
            DialogBox.warning("商品已被售出!");
            cancelOnClick(null);return;
        }
        if (CacheDataBean.getNowUser().getMoney() < goods.getGoodsPrice()) {
            DialogBox.warning("余额不足,请充值!");
            cancelOnClick(null);return;
        }
        if (receiveAddress.getText() == null || receiveAddress.getText().equals("")) {
            DialogBox.warning("请填写收货地址!");return;
        }
        goods.setSaleOut(1);
        goods.setOutTime(new Date(System.currentTimeMillis()));
        goods.setPurchaserId(CacheDataBean.getNowUser().getUserId());
        goods.setReceiveAddress(receiveAddress.getText());
        if (!SQLUtil.buyGoods(CacheDataBean.getNowUser().getUserId(), goods.getUserId(), goods)) {
            DialogBox.warning("购买出现问题, 请重新下单!");
        }
        new Thread(() -> CacheDataBean.setNowUser(SQLUtil.findUser(CacheDataBean.getNowUser().getUserId()))).start();
        cancelOnClick(null);
        DialogBox.warning("购买成功!");
    }

    class setView implements Runnable {
        GoodsBean goods;
        UserBean seller;
        UserBean buyer;

        public setView(GoodsBean goods, UserBean buyer) {
            this.goods = goods;
            assert goods != null;
            this.seller = SQLUtil.findUser(goods.getUserId());
            this.buyer = buyer;
        }
        @Override
        public void run() {
            Platform.runLater(() -> {
                if (CacheDataBean.getNowUser().getUserId().equals(goods.getUserId())) {
                    confirmForBuy.setDisable(true);
                }
                goodsName.setText(goods.getGoodsName());
                goodsId.setText(goods.getGoodsId());
                sellerName.setText(seller.getUserName());
                sellerId.setText(seller.getUserId());
                buyerId.setText(buyer.getUserId());
                goodsPrice.setText(goods.getGoodsPrice() + " 元");
                goodsImage.setImage(new Image("file:" + Objects.requireNonNull(SQLUtil.getGoodsPhoto(goods)).getPath()));
            });
        }
    }

    @FXML
    private Button confirmForBuy;

    @FXML
    private Button cancel;

    @FXML
    private TextField goodsName;

    @FXML
    private TextField goodsId;

    @FXML
    private TextField sellerName;

    @FXML
    private TextField sellerId;

    @FXML
    private TextField buyerId;

    @FXML
    private TextField goodsPrice;

    @FXML
    private TextField receiveAddress;

    @FXML
    private ImageView goodsImage;
}
