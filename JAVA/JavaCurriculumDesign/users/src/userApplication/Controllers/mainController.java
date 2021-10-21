package userApplication.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import userApplication.dataBean.*;
import userApplication.utils.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 保存工作区
        CacheDataBean.setWorkingPane(workingSpace);
        // 设置图标
        Image icon = new Image(String.valueOf(getClass().getResource("/userApplication/resource/images/icon.png")));
        mainIcon.setImage(icon);
        // 通过用户性别选择用户头像
        Image photo = new Image(String.valueOf(getClass().getResource("/userApplication/resource/images/femalePhoto.png")));
        if (CacheDataBean.getNowUser() != null ) {
            userName.setText(CacheDataBean.getNowUser().getUserName());
            if (CacheDataBean.getNowUser().getUserSex() == 1)
                photo = new Image(String.valueOf(getClass().getResource("/userApplication/resource/images/malePhoto.png")));
        }
        userPhoto.setImage(photo);
        // 默认加载 发现模块
        try {
            discoveryOnClick(null);
        } catch (IOException e) {
            DialogBox.error(e);
        }
    }

    @FXML
    void discoveryOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnDiscovery);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/userApplication/resource/fxml/discovery.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void ServiceOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnService);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/userApplication/resource/fxml/service.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void distributionOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnDistribution);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/userApplication/resource/fxml/distribution.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void messageOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnMessage);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/userApplication/resource/fxml/message.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void myDistributionOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnMyDistribution);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/userApplication/resource/fxml/myDistribution.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void myPurchasesOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnMyPurchases);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/userApplication/resource/fxml/myPurchases.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void personalDataOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setStatus(StatusBean.OnPersonalData);
        workingSpace.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("/userApplication/resource/fxml/personalData.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        workingSpace.getChildren().add(page);
    }

    @FXML
    void changePasswordOnClick(ActionEvent events) throws IOException {
        Stage passwordStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userApplication/resource/fxml/changePassword.fxml"));
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
    void logOutOnClick(ActionEvent events) throws IOException {
        CacheDataBean.logOut();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userApplication/resource/fxml/login.fxml"));
        Pane root = loader.load();
        CacheDataBean.getMyStage().setTitle("LOAP 二手交易平台 登录页面");
        CacheDataBean.getMyStage().setScene(new Scene(root, 800, 500));
        CacheDataBean.getMyStage().setResizable(false);   // 窗口大小不可调整
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        CacheDataBean.getMyStage().setX((screenRectangle.getWidth() - 800) / 2.0);
        CacheDataBean.getMyStage().setY((screenRectangle.getHeight()- 500) / 2.0 - 100);
        CacheDataBean.getMyStage().show();
    }

    @FXML
    private MenuItem personalData;

    @FXML
    private MenuItem changePassword;

    @FXML
    private Button myPurchases;

    @FXML
    private MenuButton Mine;

    @FXML
    private Button Discovery;

    @FXML
    private Button Distribution;

    @FXML
    private Button MyDistribution;

    @FXML
    private Button Service;

    @FXML
    private Button Message;

    @FXML
    private StackPane workingSpace;

    @FXML
    private ImageView mainIcon;

    @FXML
    private ImageView userPhoto;

    @FXML
    private Label userName;

    @FXML
    private MenuItem logOut;
}
