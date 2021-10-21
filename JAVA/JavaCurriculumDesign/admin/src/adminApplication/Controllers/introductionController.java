package adminApplication.Controllers;

import adminApplication.dataBean.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import adminApplication.utils.DialogBox;
import adminApplication.utils.SQLUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class introductionController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new setView(CacheDataBean.getLookingGoodsId())).start();
    }

    @FXML
    private ImageView goodsImage;

    @FXML
    private TextArea introduction;

    @FXML
    private Button noPass;

    @FXML
    private Button contactSeller;

    @FXML
    private DatePicker registerTime;

    @FXML
    private TextField goodsName;

    @FXML
    private TextField sellerId;

    @FXML
    private TextField goodsPrice;

    @FXML
    private TextField keyWords;

    @FXML
    private TextField sellerName;

    @FXML
    private Button cancel;

    @FXML
    private Button pass;

    @FXML
    private TextField failReason;

    @FXML
    void cancelOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnAuditGoods);
        CacheDataBean.getWorkingPane().getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/auditGoods.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        CacheDataBean.getWorkingPane().getChildren().add(page);
    }

    @FXML
    void contactSellerOnClick(ActionEvent event) {
        CacheDataBean.setStatus(StatusBean.OnServiceWithSeller);
        GoodsBean goods = SQLUtil.getGoodsByGoodsId(CacheDataBean.getLookingGoodsId());
        assert goods != null;
        CacheDataBean.setChatUserId(goods.getUserId());
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
    }

    @FXML
    void noPasOnClick(ActionEvent event) throws IOException {
        if (failReason.getText() == null || failReason.getText().equals("")) {
            DialogBox.warning("请输入拒绝原因！");
        } else if (DialogBox.confirm("确认拒绝吗？")){
            SQLUtil.noPassAudit(CacheDataBean.getLookingGoodsId(), failReason.getText());
            cancelOnClick(null);
        }
    }

    @FXML
    void passOnClick(ActionEvent event) throws IOException {
        if (DialogBox.confirm("确认通过吗？")) {
            SQLUtil.passAudit(CacheDataBean.getLookingGoodsId());
            cancelOnClick(null);
        }
    }

    class setView implements Runnable {
        GoodsBean goods;
        UserBean seller;

        public setView(String GoodsId) {
            this.goods = SQLUtil.getGoodsByGoodsId(GoodsId);
            assert goods != null;
            this.seller = SQLUtil.findUser(goods.getUserId());
        }

        @Override
        public void run() {
            Platform.runLater(() -> {
                goodsName.setText(goods.getGoodsName());
                sellerId.setText(seller.getUserId());
                sellerName.setText(seller.getUserName());
                goodsPrice.setText(goods.getGoodsPrice() + " 元");
                registerTime.setValue(goods.getRegisterTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                introduction.setText(goods.getIntroduction());
                keyWords.setText(String.join(",", goods.getKeyWords()));
                goodsImage.setImage(new Image("file:" + Objects.requireNonNull(SQLUtil.getGoodsPhoto(goods)).getPath()));

            });
        }
    }
}