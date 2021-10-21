package userApplication.Controllers;

import javafx.fxml.Initializable;
import userApplication.dataBean.*;
import userApplication.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class registerController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 加载注册页面图标
        Image icon = new Image(String.valueOf(getClass().getResource("/userApplication/resource/images/icon.png")));
        registerIcon.setImage(icon);
    }

    @FXML private TextField userId;
    @FXML private PasswordField password;
    @FXML private TextField userName;
    @FXML private CheckBox sexMale;
    @FXML private CheckBox sexFemale;
    @FXML private TextField userPhone;
    @FXML private TextField userEmail;
    @FXML private DatePicker userBirthday;
    @FXML private Button registerConfirm;
    @FXML private ImageView registerIcon;
    @FXML private Button cancel;
    @FXML private Label registerMessage;

    @FXML   // 使得男女按钮仅一个可被选中
    void sexFemaleOnClick(ActionEvent event) {
        sexMale.setSelected(false);
    }

    @FXML
    void sexMaleOnclick(ActionEvent event) {
        sexFemale.setSelected(false);
    }

    @FXML
    void cancelOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userApplication/resource/fxml/login.fxml"));
        Pane root = loader.load();
        CacheDataBean.getMyStage().setTitle("LOAP 二手交易平台 登录页面");
        CacheDataBean.getMyStage().setScene(new Scene(root, 800, 500));
    }

    @FXML
    void registerConfirmOnClick(ActionEvent event) throws IOException {
        if (userId.getText().equals("") || userId.getText() == null) {
            DialogBox.warning("请输入用户账号");
            return;
        } else if (!Pattern.matches("^.{5,20}$", userId.getText())) {
            DialogBox.warning("用户账号格式错误");
            return;
        } else if (SQLUtil.hasUser(userId.getText())) {
            DialogBox.warning("用户账号已存在");
            return;
        }
        if (!Pattern.matches("^.{0,20}$", password.getText())) {
            DialogBox.warning("密码过长");
            return;
        }
        if (!Pattern.matches("^.{2,20}$", userName.getText())) {
            DialogBox.warning("用户名长度不允许");
            return;
        }
        if (sexMale.isSelected() == sexFemale.isSelected()) {
            DialogBox.warning("请选择用户性别");
            return;
        }
        if (!Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", userEmail.getText())) {
            DialogBox.warning("邮箱格式错误");
            return;
        }
        if (!Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", userPhone.getText())) {
            DialogBox.warning("手机号格式输入错误");
            return;
        }
        if (userBirthday.getValue() == null) {
            DialogBox.warning("请选择用户生日");
            return;
        }
        UserBean user = new UserBean();
        user.setUserId(userId.getText());
        user.setPassword(password.getText());
        user.setUserName(userName.getText());
        user.setUserSex(sexMale.isSelected() ? 1 : 0);
        user.setUserEmail(userEmail.getText());
        user.setUserPhone(userPhone.getText());
        user.setUserBirthday(Date.from(userBirthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setRegisterTime(new Date(System.currentTimeMillis()));

        if (!SQLUtil.addUser(user)) {
            DialogBox.warning("注册失败, 请重新尝试");
        }
        // 注册成功返回主页面
        this.cancelOnClick(null);
    }
}
