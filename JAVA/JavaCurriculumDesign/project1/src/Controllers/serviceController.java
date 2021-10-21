package Controllers;

import dataBean.CacheDataBean;
import dataBean.GoodsBean;
import dataBean.ServiceBean;
import dataBean.StatusBean;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.SQLUtil;

import java.net.URL;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class serviceController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Thread(new addToServiceView(SQLUtil.getAllServiceByUserId(CacheDataBean.getNowUser().getUserId()))).start();
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                if (CacheDataBean.getStatus() != null && CacheDataBean.getStatus() == StatusBean.OnService) {
                    if (SQLUtil.hasNewServiceByUserId(CacheDataBean.getNowUser().getUserId())) {
                        new Thread(new addToServiceView(SQLUtil.getNewServiceByUserId(CacheDataBean.getNowUser().getUserId()))).start();
                    }
                } else {
                    timer.cancel();
                }
            }
        }, 1000, 200);
    }

    @FXML
    void sendOnClick(ActionEvent event) {
        if (textIn.getText() != null && !textIn.getText().equals("")) {
            ServiceBean service = new ServiceBean();
            service.setUserId(CacheDataBean.getNowUser().getUserId());
            service.setOrSend(0);
            service.setSendTime(new Date(System.currentTimeMillis()));
            service.setOrRead(0);
            service.setMessage(textIn.getText());
            if (CacheDataBean.getLookingGoods() != null) {
                service.setGoodsId(CacheDataBean.getLookingGoods().getGoodsId());
            }

            SQLUtil.addService(service);
            textIn.setText("");
            new Thread(new addToServiceView(new LinkedList<ServiceBean>() {{add(service);}})).start();
        }
    }

    @FXML
    void sendReadingGoodsOnClick(ActionEvent event) {
        if (CacheDataBean.getLookingGoods() != null) {
            GoodsBean goods = CacheDataBean.getLookingGoods();
            String text = "我正在浏览商品 : " + goods.getGoodsName();
            ServiceBean service = new ServiceBean();
            service.setUserId(CacheDataBean.getNowUser().getUserId());
            service.setOrSend(0);
            service.setSendTime(new Date(System.currentTimeMillis()));
            service.setOrRead(0);
            service.setMessage(text);
            service.setGoodsId(goods.getGoodsId());


            SQLUtil.addService(service);
            new Thread(new addToServiceView(new LinkedList<ServiceBean>() {{add(service);}})).start();
        }
    }

    class addToServiceView implements Runnable {
        List<ServiceBean> serviceList;

        public addToServiceView(List<ServiceBean> serviceList) {
            this.serviceList = serviceList;
        }

        @Override
        public void run() {
            CountDownLatch countDownLatch = new CountDownLatch(serviceList.size());
            for (ServiceBean service : serviceList) {
                TextArea text = new TextArea(service.getMessage());
                text.setPrefWidth(300.0); text.setPrefHeight((service.getMessage().length() / 20.0 + 1) * 25);
                text.setEditable(false); text.setWrapText(true);
                ImageView image = new ImageView();
                image.setFitHeight(40);image.setFitWidth(40.0);
                image.setImage(new Image(String.valueOf(getClass().getResource("../resource/images/femalePhoto.png"))));
                if (service.getOrSend() == 0) {
                    if (CacheDataBean.getNowUser().getUserSex() == 1) {
                        image.setImage(new Image(String.valueOf(getClass().getResource("../resource/images/malePhoto.png"))));
                    }
                    AnchorPane.setTopAnchor(image, 7.5); AnchorPane.setLeftAnchor(image, 940.0);
                    AnchorPane.setTopAnchor(text, 5.0); AnchorPane.setLeftAnchor(text, 630.0);
                } else {
                    AnchorPane.setTopAnchor(image, 7.5); AnchorPane.setLeftAnchor(image, 0.0);
                    AnchorPane.setTopAnchor(text, 5.0); AnchorPane.setLeftAnchor(text, 50.0);
                }
                AnchorPane anchorPane = new AnchorPane(text, image);
                anchorPane.setPrefSize(995, 50);
                Platform.runLater(() -> {
                    serviceView.getChildren().add(anchorPane);
                    countDownLatch.countDown();
                });
            }
            try {
                countDownLatch.await();
                Thread.sleep(50);
                Platform.runLater(() -> {
                    serviceScrollPane.setVvalue(1.0);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private ScrollPane serviceScrollPane;

    @FXML
    private VBox serviceView;

    @FXML
    private TextField textIn;

    @FXML
    private Button send;

    @FXML
    private Button sendReadingGoods;
}
