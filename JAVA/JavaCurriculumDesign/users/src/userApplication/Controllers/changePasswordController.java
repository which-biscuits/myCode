package userApplication.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userApplication.dataBean.CacheDataBean;
import userApplication.utils.SQLUtil;

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
        if (!oldPassword.getText().equals(CacheDataBean.getNowUser().getPassword())) {
            errorMessage.setText("密码输入错误");return;
        } else if (!Pattern.matches("^.{0,20}$", firstPassword.getText())) {
            errorMessage.setText("密码过长");return;
        } else if (firstPassword.getText().equals(CacheDataBean.getNowUser().getPassword())) {
            errorMessage.setText("输入密码与原密码相同");return;
        } else if (!firstPassword.getText().equals(secondPassword.getText())) {
            errorMessage.setText("两次输入密码不一致");return;
        }

        CacheDataBean.getNowUser().setPassword(firstPassword.getText());

        SQLUtil.updateUser(CacheDataBean.getNowUser());
        cancelOnClick(null);
    }

}
