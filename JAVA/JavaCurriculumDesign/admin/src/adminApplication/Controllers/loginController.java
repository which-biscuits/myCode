package adminApplication.Controllers;

import adminApplication.dataBean.AdminBean;
import adminApplication.dataBean.CacheDataBean;
import adminApplication.utils.DialogBox;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import adminApplication.utils.SQLUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image icon = new Image(String.valueOf(getClass().getResource("/adminApplication/resource/images/icon.png")));
        loginIcon.setImage(icon);
    }

    @FXML
    private ImageView loginIcon;

    @FXML
    private TextField userId;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginIn;

    @FXML
    void loginInOnClick(ActionEvent event) throws IOException {
        if (userId.getText() == null || userId.getText().equals("")) {
            DialogBox.warning("管理员账号不能为空");return;
        } else {
            AdminBean nowAdmin = SQLUtil.findAdminForLogIn(userId.getText(), password.getText());
            if (nowAdmin == null) {
                DialogBox.warning("用户名或密码错误!");return;
            } else {
                CacheDataBean.setNowAdmin(nowAdmin);
                CacheDataBean.getMyStage().setY(50);
                CacheDataBean.getMyStage().setX(50);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminApplication/resource/fxml/main.fxml"));
                Pane root = loader.load();
                CacheDataBean.getMyStage().setScene(new Scene(root, 1700, 900));
                CacheDataBean.getMyStage().setResizable(false);   // 窗口大小不可调整
                CacheDataBean.getMyStage().setTitle("LOAP 二手交易平台");
            }
        }
    }
}
