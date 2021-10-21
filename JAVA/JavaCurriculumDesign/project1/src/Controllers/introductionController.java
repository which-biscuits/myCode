package Controllers;

import dataBean.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.SQLUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class introductionController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CacheDataBean.setStatus(StatusBean.OnIntroduction);
        new Thread(new setView(CacheDataBean.getLookingGoods())).start();
    }

    @FXML
    void buyItOnClick(ActionEvent event) {
        CacheDataBean.getWorkingPane().getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("../resource/fxml/justBuyIt.fxml");
        AnchorPane page = null; // 对象方法的参数是InputStream，返回值是Object
        try {
            page = loader.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheDataBean.getWorkingPane().getChildren().add(page);
    }

    @FXML
    void contactSellerOnClick(ActionEvent event) throws IOException {
        MessageBean message = new MessageBean();
        GoodsBean goods = CacheDataBean.getLookingGoods();
        message.setSendUserId(CacheDataBean.getNowUser().getUserId());
        assert goods != null;
        message.setReceiveUserId(goods.getUserId());
        message.setGoodsId(goods.getGoodsId());
        message.setSendTime(new Date(System.currentTimeMillis()));
        message.setOrRead(0);
        message.setMessage("我正在浏览你的商品 : " + goods.getGoodsName());
        SQLUtil.addMessage(message);

        CacheDataBean.setStatus(StatusBean.OnMessageWithSeller);
        CacheDataBean.getWorkingPane().getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("../resource/fxml/message.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        CacheDataBean.getWorkingPane().getChildren().add(page);
    }

    @FXML
    void modifyOnClick(ActionEvent event) {
        CacheDataBean.getWorkingPane().getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("../resource/fxml/modify.fxml");
        AnchorPane page = null; // 对象方法的参数是InputStream，返回值是Object
        try {
            page = loader.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheDataBean.getWorkingPane().getChildren().add(page);
    }

    @FXML
    void cancelOnClick(ActionEvent event) {
        CacheDataBean.getWorkingPane().getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("../resource/fxml/discovery.fxml");
        if (CacheDataBean.getStatus() == StatusBean.OnMyPurchases) {
            in = getClass().getResourceAsStream("../resource/fxml/myPurchases.fxml");
        } else if (CacheDataBean.getStatus() == StatusBean.OnMyDistribution) {
            in = getClass().getResourceAsStream("../resource/fxml/myDistribution.fxml");
        }
        AnchorPane page = null; // 对象方法的参数是InputStream，返回值是Object
        try {
            page = loader.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheDataBean.getWorkingPane().getChildren().add(page);
    }

    class setView implements Runnable {
        GoodsBean goods;
        UserBean seller;

        public setView(GoodsBean goods) {
            this.goods = goods;
            assert goods != null;
            this.seller = SQLUtil.findUser(goods.getUserId());
        }


        @Override
        public void run() {
            Platform.runLater(() -> {
                if (goods.getSaleOut() == 1) {
                    buyIt.setVisible(false);
                }
                if (CacheDataBean.getLookingGoods().getUserId().equals(CacheDataBean.getNowUser().getUserId())) {
                    contactSeller.setVisible(false);
                    buyIt.setVisible(false);
                    if (goods.getOrCheck() != -1) {
                        failReason.setVisible(false);
                    } else {
                        failReason.setText(goods.getFailReason());
                    }
                } else {
                    modify.setVisible(false);
                    failReason.setVisible(false);
                }
                goodsName.setText(goods.getGoodsName());
                sellerId.setText(seller.getUserId());
                sellerName.setText(seller.getUserName());
                goodsPrice.setText(goods.getGoodsPrice() + " 元");
                registerTime.setText(goods.getRegisterTime().toString());
                introduction.setText(goods.getIntroduction());
                keyWords.setText(String.join(",", goods.getKeyWords()));
            });
            Platform.runLater(() -> {
                goodsImage.setImage(new Image("file:" + Objects.requireNonNull(SQLUtil.getGoodsPhoto(goods)).getPath()));
            });
        }
    }

    @FXML
    private Button cancel;

    @FXML
    private ImageView goodsImage;

    @FXML
    private TextField sellerName;

    @FXML
    private TextArea introduction;

    @FXML
    private Button buyIt;

    @FXML
    private Button contactSeller;

    @FXML
    private TextField registerTime;

    @FXML
    private TextField goodsName;

    @FXML
    private TextField sellerId;

    @FXML
    private TextField goodsPrice;

    @FXML
    private TextField keyWords;

    @FXML
    private Button modify;

    @FXML
    private TextField failReason;
}
