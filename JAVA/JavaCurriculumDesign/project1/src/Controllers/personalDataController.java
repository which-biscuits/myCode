package Controllers;

import dataBean.CacheDataBean;
import dataBean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.SQLUtil;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class personalDataController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setView(CacheDataBean.getNowUser());
    }

    @FXML
    private TextField userId;

    @FXML
    private Button modify;

    @FXML
    private Button confirm;

    @FXML
    private TextField money;

    @FXML
    private Button recharge;

    @FXML
    private TextField rechargeMoney;

    @FXML
    private TextField userPhone;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userName;

    @FXML
    private CheckBox sexMale;

    @FXML
    private CheckBox sexFemale;

    @FXML
    private DatePicker userBirthday;

    @FXML
    private DatePicker registerTime;

    @FXML
    private Label errorMessage;

    @FXML
    void confirmOnClick(ActionEvent event) {
        errorMessage.setText("");
        if (userId.getText().equals("") || userId.getText() == null) {
            errorMessage.setText("请输入用户账号");
            return;
        } else if (!Pattern.matches("^.{5,20}$", userId.getText())) {
            errorMessage.setText("用户账号格式错误");
            return;
        } else if (!userId.getText().equals(CacheDataBean.getNowUser().getUserId()) && SQLUtil.hasUser(userId.getText())) {
            errorMessage.setText("用户账号已存在");
            return;
        }
        if (!Pattern.matches("^.{2,20}$", userName.getText())) {
            errorMessage.setText("用户名长度不允许");
            return;
        }
        if (sexMale.isSelected() == sexFemale.isSelected()) {
            errorMessage.setText("请选择用户性别");
            return;
        }
        if (!Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", userEmail.getText())) {
            errorMessage.setText("邮箱格式错误");
            return;
        }
        if (!Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", userPhone.getText())) {
            errorMessage.setText("手机号格式输入错误");
            return;
        }
        if (userBirthday.getValue() == null) {
            errorMessage.setText("请选择用户生日");
            return;
        }
        UserBean user = CacheDataBean.getNowUser();
        user.setUserId(userId.getText());
        user.setUserName(userName.getText());
        user.setUserSex(sexMale.isSelected() ? 1 : 0);
        user.setUserEmail(userEmail.getText());
        user.setUserPhone(userPhone.getText());
        user.setUserBirthday(Date.from(userBirthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        SQLUtil.updateUser(user);

        this.setView(CacheDataBean.getNowUser());
    }

    @FXML
    void modifyOnClick(ActionEvent event) {
        confirm.setDisable(false);
        userId.setEditable(true);
        userName.setEditable(true);
        sexMale.setDisable(false);
        sexFemale.setDisable(false);
        userEmail.setEditable(true);
        userPhone.setEditable(true);
        userBirthday.setDisable(false);
    }

    @FXML
    void sexFemaleOnClick(ActionEvent event) {
        if (sexMale.isSelected()) {
            sexMale.setSelected(false);
        }
    }

    @FXML
    void sexMaleOnClick(ActionEvent event) {
        if (sexFemale.isSelected()) {
            sexFemale.setSelected(false);
        }
    }

    private void setView(UserBean user) {
        confirm.setDisable(true);
        userId.setText(user.getUserId());
        userName.setText(user.getUserName());
        userId.setEditable(false);
        userName.setEditable(false);
        sexMale.setDisable(true);
        sexFemale.setDisable(true);
        userEmail.setEditable(false);
        userPhone.setEditable(false);
        userBirthday.setDisable(true);
        if (user.getUserSex() == 0) {
            sexFemale.setSelected(true);
        } else {
            sexMale.setSelected(true);
        }
        userEmail.setText(user.getUserEmail());
        userPhone.setText(user.getUserPhone());
        userBirthday.setValue(user.getUserBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        registerTime.setValue(user.getRegisterTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        money.setText(String.valueOf(user.getMoney()));
    }

    @FXML
    void rechargeOnClick(ActionEvent event) {
        if (!Pattern.matches("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$", rechargeMoney.getText())) {
            errorMessage.setText("请正确输入充值金额");return;
        } else if (SQLUtil.updateMoney(CacheDataBean.getNowUser(), Double.parseDouble(rechargeMoney.getText()))){
            CacheDataBean.getNowUser().setMoney(CacheDataBean.getNowUser().getMoney() + Double.parseDouble(rechargeMoney.getText()));
            money.setText(String.valueOf(CacheDataBean.getNowUser().getMoney()));
            rechargeMoney.setText("");
            return;
        }
        errorMessage.setText("充值失败请重新充值");
    }

}
