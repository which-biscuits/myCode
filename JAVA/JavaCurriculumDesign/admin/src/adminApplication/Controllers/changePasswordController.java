package adminApplication.Controllers;

import adminApplication.dataBean.AdminBean;
import adminApplication.dataBean.CacheDataBean;
import adminApplication.utils.DialogBox;
import adminApplication.utils.SQLUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class changePasswordController {

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    private TextField oldPassword;

    @FXML
    private PasswordField firstPassword;

    @FXML
    private PasswordField secondPassword;

    @FXML
    private Label errorMessage;

    @FXML
    void cancelOnClick(ActionEvent event) {
        Stage passwordStage = CacheDataBean.getStage();
        CacheDataBean.setStage(null);
        passwordStage.close();
    }

    @FXML
    void confirmOnClick(ActionEvent event) {
        if (!oldPassword.getText().equals(CacheDataBean.getNowAdmin().getPassword())) {
            errorMessage.setText("密码输入错误");return;
        } else if (!Pattern.matches("^.{0,20}$", firstPassword.getText())) {
            errorMessage.setText("密码过长");return;
        } else if (firstPassword.getText().equals(CacheDataBean.getNowAdmin().getPassword())) {
            errorMessage.setText("输入密码与原密码相同");return;
        } else if (!firstPassword.getText().equals(secondPassword.getText())) {
            errorMessage.setText("两次输入密码不一致");return;
        }

        AdminBean admin = SQLUtil.findAdminById(CacheDataBean.getWorkingAdminId());
        assert admin != null;
        admin.setPassword(firstPassword.getText());
        SQLUtil.updateAdmin(admin);
        if (CacheDataBean.getNowAdmin().getAdminId().equals(admin.getAdminId())) {
            CacheDataBean.setNowAdmin(admin);
        }

        if (!SQLUtil.updateAdmin(CacheDataBean.getNowAdmin())) {
            DialogBox.warning("修改密码存在错误!");
        }
        cancelOnClick(null);
    }

}
