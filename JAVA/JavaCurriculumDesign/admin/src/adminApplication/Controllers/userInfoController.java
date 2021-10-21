package adminApplication.Controllers;

import adminApplication.dataBean.Person;
import adminApplication.dataBean.UserBean;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import adminApplication.utils.DialogBox;
import adminApplication.utils.SQLUtil;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class userInfoController implements Initializable {

    @FXML
    private TableView<Person> tableVIew;

    @FXML
    private TableColumn<Person, String> userId;

    @FXML
    private TableColumn<Person, String> userName;

    @FXML
    private TableColumn<Person, Integer> userSex;

    @FXML
    private TableColumn<Person, String> userEmail;

    @FXML
    private TableColumn<Person, String> userPhone;

    @FXML
    private TableColumn<Person, String> userBirthday;

    @FXML
    private TableColumn<Person, String> registerTime;

    @FXML
    private TableColumn<UserBean, Integer> orLog;

    @FXML
    private TableColumn<UserBean, Double> money;

    @FXML
    private TableColumn<UserBean, Integer> purchaseCount;

    @FXML
    private TableColumn<UserBean, Integer> saleCount;

    ObservableList<Person> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userSex.setCellValueFactory(new PropertyValueFactory<>("userSex"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        userPhone.setCellValueFactory(new PropertyValueFactory<>("userPhone"));
        userBirthday.setCellValueFactory(new PropertyValueFactory<>("userBirthday"));
        registerTime.setCellValueFactory(new PropertyValueFactory<>("registerTime"));
        orLog.setCellValueFactory(new PropertyValueFactory<>("orLog"));
        money.setCellValueFactory(new PropertyValueFactory<>("money"));
        purchaseCount.setCellValueFactory(new PropertyValueFactory<>("purchaseCount"));
        saleCount.setCellValueFactory(new PropertyValueFactory<>("saleCount"));
        tableVIew.setItems(data);
        new Thread(() -> {
            for (UserBean user : Objects.requireNonNull(SQLUtil.getAllUser())) {
                data.add(new Person(user));
            }
        }).start();
    }

    @FXML
    private TextField userIdText;

    @FXML
    private Button findUser;

    @FXML
    private TextField chargerId;

    @FXML
    private Button charge;

    @FXML
    private TextField LogOutId;

    @FXML
    private Button LogOut;

    @FXML
    private TextField chargeMoney;

    @FXML
    void LogOutOnClick(ActionEvent event) {
        if (LogOutId.getText() == null || LogOutId.getText().equals("")) {
            DialogBox.warning("请输入需要登出的账号信息！");
        } else if (DialogBox.confirm("确定要登出该账号吗？")) {
            if (!SQLUtil.UserOutLine(LogOutId.getText())) {
                DialogBox.warning("请输入正确的账号信息！");
            } else {
                LogOutId.setText("");
                new Thread(() -> {
                    data.clear();
                    for (UserBean user : Objects.requireNonNull(SQLUtil.getAllUser())) {
                        data.add(new Person(user));
                    }
                }).start();
            }
        }
    }

    @FXML
    void chargeOnClick(ActionEvent event) {
        if (chargerId.getText() == null || chargerId.getText().equals("")) {
            DialogBox.warning("请输入充值 ID！");
        } else if (!Pattern.matches("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$", chargeMoney.getText())) {
            DialogBox.warning("请正确输入充值金额！");
        } else if (DialogBox.confirm("确认充值吗？") && SQLUtil.updateMoney(chargerId.getText(), Double.parseDouble(chargeMoney.getText()))) {
            DialogBox.warning("充值成功！");
            chargerId.setText("");chargeMoney.setText("");
            new Thread(() -> {
                data.clear();
                for (UserBean user : Objects.requireNonNull(SQLUtil.getAllUser())) {
                    data.add(new Person(user));
                }
            }).start();
        }
    }

    @FXML
    void findUserOnClick(ActionEvent event) {
        if (userIdText.getText() == null || userIdText.getText().equals("")) {
            DialogBox.warning("请输入用户 ID!");
        } else {
            UserBean user = SQLUtil.findUser(userIdText.getText());
            if (user == null) {
                DialogBox.warning("用户不存在!");return;
            }
            new Thread(() -> {
                data.clear();
                userIdText.setText("");
                data.add(new Person(user));
            }).start();
        }
    }
}
