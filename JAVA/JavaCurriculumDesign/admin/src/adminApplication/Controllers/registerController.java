package adminApplication.Controllers;

import adminApplication.dataBean.Admin;
import adminApplication.dataBean.AdminBean;
import adminApplication.dataBean.CacheDataBean;
import adminApplication.dataBean.StatusBean;
import adminApplication.utils.DialogBox;
import adminApplication.utils.SQLUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.Date;
import java.time.ZoneId;
import java.util.regex.Pattern;

public class registerController {

    @FXML
    private TextField adminId;

    @FXML
    private PasswordField password;

    @FXML
    private TextField adminName;

    @FXML
    private CheckBox sexMale;

    @FXML
    private CheckBox sexFemale;

    @FXML
    private TextField adminPhone;

    @FXML
    private TextField adminEmail;

    @FXML
    private Button registerConfirm;

    @FXML
    private ImageView registerIcon;

    @FXML
    private Button cancel;

    @FXML
    private Label registerMessage;

    @FXML
    void cancelOnClick(ActionEvent event) {
        Stage registerStage = CacheDataBean.getStage();
        CacheDataBean.setStage(null);
        registerStage.close();
    }

    @FXML
    void registerConfirmOnClick(ActionEvent event) {
        if (adminId.getText().equals("") || adminId.getText() == null) { DialogBox.warning("请输入用户账号"); return;
        } else if (!Pattern.matches("^.{5,20}$", adminId.getText())) { DialogBox.warning("用户账号格式错误"); return;
        } else if (SQLUtil.hasAdmin(adminId.getText())) { DialogBox.warning("用户账号已存在"); return; }

        if (!Pattern.matches("^.{0,20}$", password.getText())) { DialogBox.warning("密码过长"); return; }

        if (!Pattern.matches("^.{2,20}$", adminName.getText())) { DialogBox.warning("用户名长度不允许"); return; }

        if (sexMale.isSelected() == sexFemale.isSelected()) { DialogBox.warning("请选择用户性别"); return; }

        if (!Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", adminEmail.getText())) {
            DialogBox.warning("邮箱格式错误"); return; }

        if (!Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", adminPhone.getText())) {
            DialogBox.warning("手机号格式输入错误"); return; }

        AdminBean admin = new AdminBean(); admin.setAdminId(adminId.getText()); admin.setPassword(password.getText());
        admin.setAdminName(adminName.getText()); admin.setAdminSex(sexMale.isSelected() ? 1 : 0); admin.setAdminEmail(adminEmail.getText());
        admin.setAdminPhone(adminPhone.getText()); admin.setRegisterTime(new Date(System.currentTimeMillis()));

        if (!SQLUtil.addAdmin(admin)) { DialogBox.warning("注册失败, 请重新尝试"); }
        // 注册成功返回主页面
        this.cancelOnClick(null);
    }

    @FXML   // 使得男女按钮仅一个可被选中
    void sexFemaleOnClick(ActionEvent event) {
        sexMale.setSelected(false);
    }

    @FXML
    void sexMaleOnclick(ActionEvent event) {
        sexFemale.setSelected(false);
    }

}

