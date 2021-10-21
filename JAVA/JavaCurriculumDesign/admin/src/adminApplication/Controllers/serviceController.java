package adminApplication.Controllers;

import adminApplication.dataBean.CacheDataBean;
import adminApplication.dataBean.GoodsBean;
import adminApplication.dataBean.ServiceBean;
import adminApplication.dataBean.StatusBean;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import adminApplication.utils.SQLUtil;

import java.net.URL;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class serviceController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
            setUserView(SQLUtil.getServicePreview());
            if (CacheDataBean.getStatus() == StatusBean.OnServiceWithSeller) {
                CacheDataBean.setStatus(StatusBean.OnService);
                GoodsBean goods = SQLUtil.getGoodsByGoodsId(CacheDataBean.getLookingGoodsId());
                assert goods != null;
                CacheDataBean.setChatUserId(goods.getUserId());
                send.setDisable(false);
                setServiceView(SQLUtil.getAllServiceByUserId(goods.getUserId()));
            }
        }).start();

        send.setDisable(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                if ( CacheDataBean.getStatus() != null && CacheDataBean.getStatus() == StatusBean.OnService) {
                    if (SQLUtil.hasNewService()) {
                        setUserView(SQLUtil.getServicePreview());
                    }
                    if (CacheDataBean.getChatUserId() != null) {
                        if (serviceView.getChildren().size() == 0) {
                            send.setDisable(false);
                            setServiceView(SQLUtil.getAllServiceByUserId(CacheDataBean.getChatUserId()));
                        } else if (SQLUtil.hasNewServiceByUserId(CacheDataBean.getNowAdmin().getAdminId())){
                            new Thread(new addServiceToServiceView(SQLUtil.getNewServiceByUserId(CacheDataBean.getChatUserId()))).start();
                        }
                    }
                } else {
                    timer.cancel();
                }
            }
        }, 1000, 200);
    }

    @FXML
    private VBox userView;

    @FXML
    private TextField textIn;

    @FXML
    private Button send;

    @FXML
    private Button sendReadingGoods;

    @FXML
    private Label choiceUser;

    @FXML
    private VBox serviceView;

    @FXML
    private ScrollPane serviceScrollPane;

    @FXML
    void sendOnClick(ActionEvent event) {
        if (textIn.getText() != null && !textIn.getText().equals("")) {
            ServiceBean service = new ServiceBean();
            service.setUserId(CacheDataBean.getChatUserId());
            service.setOrSend(1);
            service.setSendTime(new Date(System.currentTimeMillis()));
            service.setOrRead(0);
            service.setMessage(textIn.getText());
            service.setGoodsId(CacheDataBean.getLookingGoodsId() == null ? "" : CacheDataBean.getLookingGoodsId());
            SQLUtil.addService(service);
            textIn.setText("");
            new Thread(new addServiceToServiceView(new LinkedList<ServiceBean>(){{add(service);}})).start();
        }
    }

    @FXML
    void sendReadingGoodsOnClick(ActionEvent event) {
        if (CacheDataBean.getLookingGoodsId() != null) {
            ServiceBean service = new ServiceBean();
            service.setUserId(CacheDataBean.getChatUserId());
            service.setOrSend(1);
            service.setSendTime(new Date(System.currentTimeMillis()));
            service.setOrRead(0);
            service.setMessage("我正在浏览你的商品 : " + CacheDataBean.getLookingGoodsId());
            service.setGoodsId(CacheDataBean.getLookingGoodsId());
            SQLUtil.addService(service);
            textIn.setText("");
            new Thread(new addServiceToServiceView(new LinkedList<ServiceBean>(){{add(service);}})).start();
        }
    }

    private void setUserView(HashMap<String, Integer> previewService) {
        Platform.runLater(() -> {
            userView.getChildren().clear();
        });
        new Thread(new addServiceToUserView(previewService)).start();
    }

    class addServiceToUserView implements Runnable {
        private HashMap<String, Integer> previewService;
        public addServiceToUserView(HashMap<String, Integer> previewService) {
            this.previewService = previewService;
        }

        @Override
        public void run() {
            for (String key : previewService.keySet()) {
                Button user = new Button(key);
                user.setPrefSize(140, 50);
                AnchorPane.setTopAnchor(user, 0.0);AnchorPane.setLeftAnchor(user, 5.0);
                user.setOnAction(event -> {
                    send.setDisable(false);
                    CacheDataBean.setChatUserId(key);
                    choiceUser.setText(key);
                    setServiceView(SQLUtil.getAllServiceByUserId(CacheDataBean.getChatUserId()));
                });
                Label counter = new Label(String.valueOf(previewService.get(key)));
                counter.setStyle("-fx-alignment: CENTER");
                counter.setPrefSize(50, 50);
                AnchorPane.setTopAnchor(counter, 0.0);AnchorPane.setLeftAnchor(counter, 150.0);

                AnchorPane anchorPane = new AnchorPane(user, counter);
                Platform.runLater(() -> {userView.getChildren().add(anchorPane);});
            }
        }
    }

    private void setServiceView(List<ServiceBean> serviceList) {
        Platform.runLater(() -> {
            choiceUser.setText(CacheDataBean.getChatUserId());
            serviceView.getChildren().clear();
        });
        new Thread(new addServiceToServiceView(serviceList)).start();
    }

    class addServiceToServiceView implements Runnable {
        List<ServiceBean> serviceList;
        public addServiceToServiceView(List<ServiceBean> serviceList) {
            this.serviceList = serviceList;
        }

        @Override
        public void run() {
            CountDownLatch countDownLatch = new CountDownLatch(serviceList.size());
            for (ServiceBean service : serviceList) {
                TextArea text = new TextArea(service.getMessage());
                text.setPrefWidth(300.0); text.setPrefHeight((service.getMessage().length() / 20.0 + 1) * 25);
                text.setEditable(false); text.setWrapText(true);
                ImageView image = new ImageView(new Image(String.valueOf(getClass().getResource("/adminApplication/resource/images/femalePhoto.png"))));
                image.setFitHeight(40);image.setFitWidth(40.0);
                if (service.getOrSend() == 1) {
                    AnchorPane.setTopAnchor(image, 7.5); AnchorPane.setLeftAnchor(image, 1230.0);
                    AnchorPane.setTopAnchor(text, 5.0); AnchorPane.setLeftAnchor(text, 910.0);
                } else {
                    AnchorPane.setTopAnchor(image, 7.5); AnchorPane.setLeftAnchor(image, 0.0);
                    AnchorPane.setTopAnchor(text, 5.0); AnchorPane.setLeftAnchor(text, 50.0);
                }
                AnchorPane anchorPane = new AnchorPane(text, image);
                anchorPane.setPrefSize(770.0, 50);
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


}
