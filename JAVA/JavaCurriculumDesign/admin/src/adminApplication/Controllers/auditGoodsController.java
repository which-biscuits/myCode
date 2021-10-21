package adminApplication.Controllers;

import adminApplication.dataBean.CacheDataBean;
import adminApplication.dataBean.GoodsBean;
import adminApplication.dataBean.ServiceBean;
import adminApplication.dataBean.StatusBean;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import adminApplication.utils.SQLUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class auditGoodsController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> setGoodsView(SQLUtil.getNeedCheckGoods(24))).start();
    }

    @FXML
    private FlowPane goodsView;

    @FXML
    private TextField textIn;

    @FXML
    private Button findGoods;

    @FXML
    private Button refresh;

    @FXML
    void refreshOnClick(ActionEvent event) {
        new Thread(() -> setGoodsView(SQLUtil.getNeedCheckGoods(24))).start();
    }

    @FXML
    void findGoodsOnClick(ActionEvent event) {
        if (textIn.getText() != null && !textIn.getText().equals("")) {
            String[] userKey = textIn.getText().split("[,|/]");
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

            Button audit = new Button("审核");
            audit.setPrefSize(110, 40);
            AnchorPane.setTopAnchor(audit, 205.0);AnchorPane.setLeftAnchor(audit, 5.0);

            Button contactSeller = new Button("联系卖家");
            contactSeller.setPrefSize(110, 40);
            AnchorPane.setTopAnchor(contactSeller, 205.0);AnchorPane.setLeftAnchor(contactSeller, 125.0);

            AnchorPane anchorPane = new AnchorPane(imageView, goodsName, goodsPrice, audit, contactSeller);
            anchorPane.setPrefSize(240, 240);

            audit.setOnAction(event -> {
                CacheDataBean.setStatus(StatusBean.FromAuditGoods);
                CacheDataBean.setLookingGoodsId(goods.getGoodsId());
                CacheDataBean.getWorkingPane().getChildren().clear();
                FXMLLoader loader = new FXMLLoader();	// 创建对象
                InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/introduction.fxml");
                AnchorPane page = null; // 对象方法的参数是InputStream，返回值是Object
                try {
                    page = loader.load(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CacheDataBean.getWorkingPane().getChildren().add(page);
            });
            contactSeller.setOnAction(event -> {
                CacheDataBean.setStatus(StatusBean.OnServiceWithSeller);
                CacheDataBean.setLookingGoodsId(goods.getGoodsId());
                CacheDataBean.setChatUserId(Objects.requireNonNull(SQLUtil.getGoodsByGoodsId(goods.getGoodsId())).getUserId());
                String text = "我正在审核商品 : " + goods.getGoodsName();
                ServiceBean service = new ServiceBean();
                service.setUserId(CacheDataBean.getChatUserId());
                service.setOrSend(1);
                service.setSendTime(new Date(System.currentTimeMillis()));
                service.setOrRead(0);
                service.setMessage(text);
                service.setGoodsId(CacheDataBean.getLookingGoodsId());

                SQLUtil.addService(service);
                CacheDataBean.getWorkingPane().getChildren().clear();
                FXMLLoader loader = new FXMLLoader();	// 创建对象
                InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/service.fxml");
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

}
