package Controllers;

import dataBean.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.MessageUtil;
import utils.SQLUtil;

import java.net.URL;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class messageController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
            setUserView(SQLUtil.getMessagePreviewByUserId(CacheDataBean.getNowUser().getUserId()));
            if ( CacheDataBean.getStatus() == StatusBean.OnMessageWithSeller) {
                CacheDataBean.setStatus(StatusBean.OnMessage);
                GoodsBean goods = CacheDataBean.getLookingGoods();
                assert goods != null;
                CacheDataBean.setChatUserId(goods.getUserId());
                send.setDisable(false);
                CacheDataBean.setMessagePreview(SQLUtil.getAllMessageByBothId(CacheDataBean.getNowUser().getUserId(), goods.getUserId()));
                setMessageView(CacheDataBean.getMessagePreview());
            }
        }).start();
        send.setDisable(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                if ( CacheDataBean.getStatus() != null && CacheDataBean.getStatus() == StatusBean.OnMessage) {
                    setUserView(SQLUtil.getMessagePreviewByUserId(CacheDataBean.getNowUser().getUserId()));
                    if (CacheDataBean.getChatUserId() != null) {
                        if (messageView.getChildren().size() == 0) {
                            setMessageView(SQLUtil.getAllMessageByBothId(CacheDataBean.getNowUser().getUserId(), CacheDataBean.getChatUserId()));
                        } else if (SQLUtil.hasNewMessageByBothId(CacheDataBean.getNowUser().getUserId(), CacheDataBean.getChatUserId())){
                            new Thread(new addMessageToMessageView(SQLUtil.getNewMessageByBothId(CacheDataBean.getNowUser().getUserId(), CacheDataBean.getChatUserId()))).start();
                        }
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
            MessageBean message = new MessageBean(CacheDataBean.getNowUser().getUserId(), CacheDataBean.getChatUserId(), null,
                    new Date(System.currentTimeMillis()), 0, textIn.getText());
            SQLUtil.addMessage(message);
            textIn.setText("");
            new Thread(new addMessageToMessageView(new LinkedList<MessageBean>(){{add(message);}})).start();
        }
    }

    @FXML
    void sendReadingGoodsOnClick(ActionEvent event) {
        if (CacheDataBean.getLookingGoods() != null) {
            GoodsBean goods = CacheDataBean.getLookingGoods();
            String text = "我正在浏览你的商品 : " + goods.getGoodsName();
            MessageBean message = new MessageBean(CacheDataBean.getNowUser().getUserId(), CacheDataBean.getChatUserId(), goods.getGoodsId(),
                    new Date(System.currentTimeMillis()), 0, text);
            SQLUtil.addMessage(message);

            new Thread(new addMessageToMessageView(new LinkedList<MessageBean>(){{add(message);}})).start();
        }
    }

    private void setUserView(HashMap<String, Integer> messagePreview) {
        Platform.runLater(() -> {
            userView.getChildren().clear();
        });
        new Thread(new addMessageToUserView(messagePreview)).start();
    }

    class addMessageToUserView implements Runnable {
        HashMap<String, Integer> messagePreview;
        public addMessageToUserView(HashMap<String, Integer> messagePreview) {
            this.messagePreview = messagePreview;
        }

        @Override
        public void run() {
            for (String key : messagePreview.keySet()) {
                Button user = new Button(key);
                user.setPrefSize(140, 50);
                AnchorPane.setTopAnchor(user, 0.0);AnchorPane.setLeftAnchor(user, 5.0);
                user.setOnAction(event -> {
                    CacheDataBean.setChatUserId(key);
                    choiceUser.setText(key);
                    SQLUtil.messageToOverRead(key, CacheDataBean.getNowUser().getUserId());
                    setMessageView(SQLUtil.getAllMessageByBothId(CacheDataBean.getNowUser().getUserId(), key));
                });
                Label counter = new Label(String.valueOf(messagePreview.get(key)));
                counter.setStyle("-fx-alignment: CENTER");
                counter.setPrefSize(50, 50);
                AnchorPane.setTopAnchor(counter, 0.0);AnchorPane.setLeftAnchor(counter, 150.0);

                AnchorPane anchorPane = new AnchorPane(user, counter);
                Platform.runLater(() -> {userView.getChildren().add(anchorPane);});
            }
        }
    }

    private void setMessageView(List<MessageBean> messageList){
        send.setDisable(false);
        Platform.runLater(() -> {choiceUser.setText(CacheDataBean.getChatUserId());messageView.getChildren().clear();});
        new Thread(new addMessageToMessageView(messageList)).start();
    }

    class addMessageToMessageView implements Runnable {
        List<MessageBean> messageList;
        public addMessageToMessageView(List<MessageBean> message) {
            this.messageList = message;
        }

        @Override
        public void run() {
            CountDownLatch countDownLatch = new CountDownLatch(messageList.size());
            for (MessageBean message : messageList) {
                TextArea text = new TextArea(message.getMessage());
                text.setPrefWidth(300.0); text.setPrefHeight((message.getMessage().length() / 20.0 + 1) * 25);
                text.setEditable(false); text.setWrapText(true);
                ImageView image = new ImageView(new Image(String.valueOf(getClass().getResource("../resource/images/malePhoto.png"))));
                image.setFitHeight(40);image.setFitWidth(40.0);
                if (message.getSendUserId().equals(CacheDataBean.getNowUser().getUserId())) {
                    AnchorPane.setTopAnchor(image, 7.5); AnchorPane.setLeftAnchor(image, 730.0);
                    AnchorPane.setTopAnchor(text, 5.0); AnchorPane.setLeftAnchor(text, 410.0);
                } else {
                    AnchorPane.setTopAnchor(image, 7.5); AnchorPane.setLeftAnchor(image, 0.0);
                    AnchorPane.setTopAnchor(text, 5.0); AnchorPane.setLeftAnchor(text, 50.0);
                }
                AnchorPane anchorPane = new AnchorPane(text, image);
                anchorPane.setPrefSize(770.0, 50);
                Platform.runLater(() -> {
                    messageView.getChildren().add(anchorPane);
                    countDownLatch.countDown();
                });
            }
            try {
                countDownLatch.await();
                Thread.sleep(50);
                Platform.runLater(() -> {
                    messageScrollPane.setVvalue(1.0);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private ScrollPane messageScrollPane;

    @FXML
    private Label choiceUser;

    @FXML
    private VBox userView;

    @FXML
    private VBox messageView;

    @FXML
    private TextField textIn;

    @FXML
    private Button sendReadingGoods;

    @FXML
    private Button send;
}
