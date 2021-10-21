package adminApplication.Controllers;

import adminApplication.dataBean.CacheDataBean;
import adminApplication.dataBean.StatusBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CacheDataBean.setWorkingPane(workingSpace);
        Image icon = new Image(String.valueOf(getClass().getResource("/adminApplication/resource/images/mainIcon.png")));
        mainIcon.setImage(icon);
        Image photo = new Image(String.valueOf(getClass().getResource("/adminApplication/resource/images/femalePhoto.png")));
        if (CacheDataBean.getNowAdmin() != null ) {
            userName.setText(CacheDataBean.getNowAdmin().getAdminName());
            if (CacheDataBean.getNowAdmin().getAdminSex() == 1)
                photo = new Image(String.valueOf(getClass().getResource("/adminApplication/resource/images/malePhoto.png")));
        }
        userPhoto.setImage(photo);

        try {
            auditGoodsOnClick(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button auditGoods;

    @FXML
    private Button userInfo;

    @FXML
    private Button goodsInfo;

    @FXML
    private Button Service;

    @FXML
    private Button adminInfo;

    @FXML
    private StackPane workingSpace;

    @FXML
    private ImageView mainIcon;

    @FXML
    private ImageView userPhoto;

    @FXML
    private MenuItem personalData;

    @FXML
    private MenuItem changePassword;

    @FXML
    private MenuItem logOut;

    @FXML
    private Label userName;

    @FXML
    void ServiceOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnService);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/service.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void adminInfoOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnGoodsInfo);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/adminInfo.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void auditGoodsOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnAuditGoods);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/auditGoods.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void changePasswordOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setWorkingAdminId(CacheDataBean.getNowAdmin().getAdminId());
        Stage passwordStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminApplication/resource/fxml/changePassword.fxml"));
        Pane root = loader.load();
        passwordStage.setTitle("修改密码");
        passwordStage.setScene(new Scene(root, 600, 400));
        passwordStage.initModality(Modality.APPLICATION_MODAL); // 阻止事件传入其他的任何窗口
        passwordStage.setResizable(false);   // 窗口大小不可调整
        passwordStage.setAlwaysOnTop(true);
        CacheDataBean.setStage(passwordStage);
        passwordStage.show();
    }

    @FXML
    void goodsInfoOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnGoodsInfo);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/goodsInfo.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void logOutOnClick(ActionEvent event) throws IOException {
        CacheDataBean.logOut();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminApplication/resource/fxml/login.fxml"));
        Pane root = loader.load();
        CacheDataBean.getMyStage().setTitle("LOAP 二手交易平台 管理员");
        CacheDataBean.getMyStage().setScene(new Scene(root, 800, 500));
        CacheDataBean.getMyStage().setResizable(false);   // 窗口大小不可调整
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        CacheDataBean.getMyStage().setX((screenRectangle.getWidth() - 800) / 2.0);
        CacheDataBean.getMyStage().setY((screenRectangle.getHeight()- 500) / 2.0 - 100);
        CacheDataBean.getMyStage().show();
    }

    @FXML
    void userInfoOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnUserInfo);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/userInfo.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }
}
