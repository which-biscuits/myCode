package adminApplication.Controllers;

import adminApplication.dataBean.*;
import adminApplication.utils.DialogBox;
import adminApplication.utils.SQLUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class adminInfoController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminId.setCellValueFactory(new PropertyValueFactory<>("adminId"));
        adminPassword.setCellValueFactory(new PropertyValueFactory<>("adminPassword"));
        adminName.setCellValueFactory(new PropertyValueFactory<>("adminName"));
        adminSex.setCellValueFactory(new PropertyValueFactory<>("adminSex"));
        adminEmail.setCellValueFactory(new PropertyValueFactory<>("adminEmail"));
        adminPhone.setCellValueFactory(new PropertyValueFactory<>("adminPhone"));
        registerTime.setCellValueFactory(new PropertyValueFactory<>("registerTime"));
        tableView.setItems(data);
        new Thread(() -> {
            for (AdminBean admin : Objects.requireNonNull(SQLUtil.getAllAdmin())) {
                data.add(new Admin(admin));
            }
        }).start();
    }

    ObservableList<Admin> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Admin> tableView;

    @FXML
    private TableColumn<Admin, String> adminId;

    @FXML
    private TableColumn<Admin, String> adminPassword;

    @FXML
    private TableColumn<Admin, String> adminName;

    @FXML
    private TableColumn<Admin, String> adminSex;

    @FXML
    private TableColumn<Admin, String> adminEmail;

    @FXML
    private TableColumn<Admin, String> adminPhone;

    @FXML
    private TableColumn<Admin, String> registerTime;

    @FXML
    private Button deleteAdmin;

    @FXML
    private TextField adminIdTextIn;

    @FXML
    private Button findAdmin;

    @FXML
    private Button modifyPassword;

    @FXML
    private Button modifyAdmin;

    @FXML
    private Button addAdmin;

    @FXML
    void addAdminOnClick(ActionEvent event) throws IOException {
        CacheDataBean.setWorkingAdminId(adminIdTextIn.getText());
        Stage registerStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminApplication/resource/fxml/register.fxml"));
        Pane root = loader.load();
        registerStage.setTitle("管理员注册");
        registerStage.setScene(new Scene(root, 800, 500));
        registerStage.initModality(Modality.APPLICATION_MODAL); // 阻止事件传入其他的任何窗口
        registerStage.setResizable(false);   // 窗口大小不可调整
        registerStage.setAlwaysOnTop(true);
        CacheDataBean.setStage(registerStage);
        registerStage.show();
    }

    @FXML
    void deleteAdminOnClick(ActionEvent event) {
        if (adminIdTextIn.getText() == null || adminIdTextIn.getText().equals("")) {
            DialogBox.warning("请输入管理员账号!");
        } else if (!SQLUtil.hasAdmin(adminIdTextIn.getText())) {
            DialogBox.warning("管理员账户不存在!");
        } else if (adminIdTextIn.getText().equals("1141207643")) {
            DialogBox.warning("无法删除超级账号!");
        } else if (DialogBox.confirm("确定要删除该管理员账户吗?")) {
            if (!SQLUtil.deleteAdminById(adminIdTextIn.getText())) {
                DialogBox.warning("删除异常!");
            }
            data.clear();
            new Thread(() -> {
                for (AdminBean admin : Objects.requireNonNull(SQLUtil.getAllAdmin())) {
                    data.add(new Admin(admin));
                }
            }).start();
        }
    }

    @FXML
    void findAdminOnClick(ActionEvent event) {
        if (adminIdTextIn.getText() == null || adminIdTextIn.getText().equals("")) {
            DialogBox.warning("请输入管理员账号!");return;
        }
        AdminBean admin = SQLUtil.findAdminById(adminIdTextIn.getText());
        if (admin == null) {
            DialogBox.warning("管理员账户不存在!");return;
        }
        data.clear();
        data.add(new Admin(admin));
    }

    @FXML
    void modifyAdminOnClick(ActionEvent event) throws IOException {
        if (adminIdTextIn.getText() == null || adminIdTextIn.getText().equals("")) {
            DialogBox.warning("请输入管理员账号!");return;
        }
        CacheDataBean.setWorkingAdminId(adminIdTextIn.getText());
        Stage passwordStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminApplication/resource/fxml/adminData.fxml"));
        Pane root = loader.load();
        passwordStage.setTitle("管理员信息");
        passwordStage.setScene(new Scene(root, 800, 550));
        passwordStage.initModality(Modality.APPLICATION_MODAL); // 阻止事件传入其他的任何窗口
        passwordStage.setResizable(false);   // 窗口大小不可调整
        passwordStage.setAlwaysOnTop(true);
        CacheDataBean.setStage(passwordStage);
        passwordStage.show();
    }

    @FXML
    void modifyPasswordOnClick(ActionEvent event) throws IOException {
        if (adminIdTextIn.getText() == null || adminIdTextIn.getText().equals("")) {
            DialogBox.warning("请输入管理员账号!");return;
        }
        CacheDataBean.setWorkingAdminId(adminIdTextIn.getText());
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
}
