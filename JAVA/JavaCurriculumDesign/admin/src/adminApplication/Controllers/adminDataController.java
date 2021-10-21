package adminApplication.Controllers;

import adminApplication.dataBean.Admin;
import adminApplication.dataBean.AdminBean;
import adminApplication.dataBean.CacheDataBean;
import adminApplication.utils.DialogBox;
import adminApplication.utils.SQLUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class adminDataController implements Initializable {

    @FXML
    private TextField adminId;

    @FXML
    private Button modify;

    @FXML
    private Button confirm;

    @FXML
    private TextField adminPhone;

    @FXML
    private TextField adminEmail;

    @FXML
    private TextField adminName;

    @FXML
    private CheckBox sexMale;

    @FXML
    private CheckBox sexFemale;

    @FXML
    private DatePicker registerTime;

    @FXML
    void confirmOnClick(ActionEvent event) {
        if (adminId.getText().equals("") || adminId.getText() == null) {
            DialogBox.warning("请输入用户账号");
            return;
        } else if (!Pattern.matches("^.{5,20}$", adminId.getText())) {
            DialogBox.warning("用户账号格式错误");
            return;
        } else if (!adminId.getText().equals(CacheDataBean.getNowAdmin().getAdminId()) && SQLUtil.hasAdmin(adminId.getText())) {
            DialogBox.warning("用户账号已存在");
            return;
        }
        if (!Pattern.matches("^.{2,20}$", adminName.getText())) {
            DialogBox.warning("用户名长度不允许");
            return;
        }
        if (sexMale.isSelected() == sexFemale.isSelected()) {
            DialogBox.warning("请选择用户性别");
            return;
        }
        if (!Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", adminEmail.getText())) {
            DialogBox.warning("邮箱格式错误");
            return;
        }
        if (!Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", adminPhone.getText())) {
            DialogBox.warning("手机号格式输入错误");
            return;
        }
        AdminBean admin = CacheDataBean.getNowAdmin();
        admin.setAdminId(adminId.getText());
        admin.setAdminName(adminName.getText());
        admin.setAdminSex(sexMale.isSelected() ? 1 : 0);
        admin.setAdminEmail(adminEmail.getText());
        admin.setAdminPhone(adminPhone.getText());
        SQLUtil.updateAdmin(admin);
        this.setView(Objects.requireNonNull(SQLUtil.findAdminById(CacheDataBean.getWorkingAdminId())));
    }

    @FXML
    void modifyOnClick(ActionEvent event) {
        confirm.setDisable(false);
        adminId.setEditable(true);
        adminName.setEditable(true);
        sexMale.setDisable(false);
        sexFemale.setDisable(false);
        adminEmail.setEditable(true);
        adminPhone.setEditable(true);
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

    private void setView(AdminBean admin) {
        confirm.setDisable(true);
        adminId.setText(admin.getAdminId());
        adminName.setText(admin.getAdminName());
        adminId.setEditable(false);
        adminName.setEditable(false);
        sexMale.setDisable(true);
        sexFemale.setDisable(true);
        adminEmail.setEditable(false);
        adminPhone.setEditable(false);
        if (admin.getAdminSex() == 0) {
            sexFemale.setSelected(true);
        } else {
            sexMale.setSelected(true);
        }
        adminEmail.setText(admin.getAdminEmail());
        adminPhone.setText(admin.getAdminPhone());
        registerTime.setValue(admin.getRegisterTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setView(Objects.requireNonNull(SQLUtil.findAdminById(CacheDataBean.getWorkingAdminId())));
    }
}

