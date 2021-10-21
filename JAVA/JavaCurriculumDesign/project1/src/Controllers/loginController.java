package Controllers;

import dataBean.CacheDataBean;
import dataBean.UserBean;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import utils.DialogBox;
import utils.SQLUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 加载 登陆界面图标
        Image icon = new Image(String.valueOf(getClass().getResource("../resource/images/icon.png")));
        loginIcon.setImage(icon);
    }

    @FXML private ImageView loginIcon;      // 界面图标
    @FXML private TextField userId;         // 用户账号输入
    @FXML private PasswordField password;   // 用户密码输入
    @FXML private Button register;          // 注册按钮
    @FXML private Button loginIn;           // 登录按钮

    @FXML   // 点击 登录按钮
    void loginInOnClick(ActionEvent event) throws IOException {
        if (userId.getText() == null || userId.getText().equals("")) {
            DialogBox.warning("用户账号不能为空");
        } else {
            // 从数据库获取用户信息并判断是否在线
            UserBean nowUser = SQLUtil.findUserForLogIn(userId.getText(), password.getText());
            if (nowUser == null) {
                if (SQLUtil.hasUserOnLine(userId.getText())) {  //
                    DialogBox.warning("用户已登录!");return;
                }
                DialogBox.warning("用户名或密码错误!");
            } else {
                CacheDataBean.setNowUser(nowUser);  // 保存当前工作用户
                Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
                CacheDataBean.getMyStage().setX((screenRectangle.getWidth() - 1200) / 2.0);
                CacheDataBean.getMyStage().setY((screenRectangle.getHeight()- 700) / 2.0 - 100);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/fxml/main.fxml"));
                Pane root = loader.load();
                CacheDataBean.getMyStage().setScene(new Scene(root, 1200, 700));
                CacheDataBean.getMyStage().setResizable(false);   // 窗口大小不可调整
                CacheDataBean.getMyStage().setTitle("LOAP 二手交易平台");
            }
        }
    }

    @FXML   // 点击 注册按钮
    void registerOnClick(ActionEvent event) throws IOException {
        // 注册窗口
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/fxml/register.fxml"));
        Pane root = loader.load();

        CacheDataBean.getMyStage().setScene(new Scene(root, 800, 500));
        CacheDataBean.getMyStage().setTitle("LOAP 二手交易平台 注册页面");
    }
}
