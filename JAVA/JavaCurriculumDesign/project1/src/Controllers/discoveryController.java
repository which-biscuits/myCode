package Controllers;

import dataBean.CacheDataBean;
import dataBean.GoodsBean;
import dataBean.StatusBean;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import utils.SQLUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class discoveryController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CacheDataBean.setStatus(StatusBean.OnDiscovery);
        new Thread(() -> setGoodsView(SQLUtil.getRandomGoods(12))).start();
    }

    @FXML
    void refreshOnClick(ActionEvent event) {
        new Thread(() -> setGoodsView(SQLUtil.getRandomGoods(12))).start();
    }

    @FXML
    void findGoodsOnClick(ActionEvent event) {  // 筛选商品
        if (findText.getText() != null && !findText.getText().equals("")) {
            String[] userKey = findText.getText().split("[,|/]");
            setGoodsView(SQLUtil.findGoodsByKeyWords(userKey));
        }
    }

    private void setGoodsView(List<GoodsBean> goodsList) {
        Platform.runLater(() -> goodsView.getChildren().clear());
        ExecutorService exec = Executors.newFixedThreadPool(12);
        for (GoodsBean goodsBean : goodsList) {
            exec.execute(new addGoodsToView(goodsBean));
        }
        exec.shutdown();
    }
    class addGoodsToView implements Runnable {
        GoodsBean goods;
        public addGoodsToView(GoodsBean goods) {
            this.goods = goods;
        }
        public void run() {
            ImageView imageView = new ImageView(new Image("file:" + Objects.requireNonNull(SQLUtil.getGoodsPhoto(goods)).getPath()));
            imageView.setFitWidth(230);imageView.setFitHeight(150);
            AnchorPane.setTopAnchor(imageView, 10.0);AnchorPane.setLeftAnchor(imageView, 5.0);

            Label goodsName = new Label(goods.getGoodsName());
            goodsName.setPrefSize(115, 40);
            AnchorPane.setTopAnchor(goodsName, 160.0);AnchorPane.setLeftAnchor(goodsName, 5.0);

            Label goodsPrice = new Label(String.valueOf(goods.getGoodsPrice()) + " 元");
            goodsPrice.setPrefSize(115, 40);
            AnchorPane.setTopAnchor(goodsPrice, 160.0);AnchorPane.setLeftAnchor(goodsPrice, 120.0);

            Button introduce = new Button("详情");
            introduce.setPrefSize(110, 40);
            AnchorPane.setTopAnchor(introduce, 205.0);AnchorPane.setLeftAnchor(introduce, 5.0);

            Button buy = new Button("购买");
            buy.setPrefSize(110, 40);
            AnchorPane.setTopAnchor(buy, 205.0);AnchorPane.setLeftAnchor(buy, 125.0);

            AnchorPane anchorPane = new AnchorPane(imageView, goodsName, goodsPrice, introduce, buy);
            anchorPane.setPrefSize(240, 240);

            introduce.setOnAction(event -> {
                CacheDataBean.setLookingGoods(goods);
                CacheDataBean.getWorkingPane().getChildren().clear();
                FXMLLoader loader = new FXMLLoader();	// 创建对象
                InputStream in = getClass().getResourceAsStream("../resource/fxml/introduction.fxml");
                AnchorPane page = null; // 对象方法的参数是InputStream，返回值是Object
                try {
                    page = loader.load(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CacheDataBean.getWorkingPane().getChildren().add(page);
            });
            buy.setOnAction(event -> {
                CacheDataBean.setLookingGoods(goods);
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
            });
            Platform.runLater(() -> {
                goodsView.getChildren().add(anchorPane);
            });
        }
    }

    @FXML
    private TextField findText;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button findGoods;

    @FXML
    private FlowPane goodsView;

    @FXML
    private Button refresh;
}
