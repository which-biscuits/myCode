package adminApplication.dataBean;

import adminApplication.utils.SQLUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty userId;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty userSex;
    private final SimpleStringProperty userEmail;
    private final SimpleStringProperty userPhone;
    private final SimpleStringProperty userBirthday;
    private final SimpleStringProperty registerTime;
    private final SimpleStringProperty orLog;
    private final SimpleDoubleProperty money;
    private final SimpleIntegerProperty purchaseCount;
    private final SimpleIntegerProperty saleCount;

    public Person(UserBean user) {
        this.userId = new SimpleStringProperty(user.getUserId());
        this.userName = new SimpleStringProperty(user.getUserName());
        this.userSex = new SimpleStringProperty(user.getUserSex() == 0 ? "女" : "男");
        this.userEmail = new SimpleStringProperty(user.getUserEmail());
        this.userPhone = new SimpleStringProperty(user.getUserPhone());
        this.userBirthday = new SimpleStringProperty(user.getUserBirthday().toString());
        this.registerTime = new SimpleStringProperty(user.getRegisterTime().toString());
        this.orLog = new SimpleStringProperty(user.getOrLog() == 0? "下线":"在线");
        this.money = new SimpleDoubleProperty(user.getMoney());
        this.purchaseCount = new SimpleIntegerProperty(SQLUtil.getPurchaseNumbersByUserId(user.getUserId()));
        this.saleCount = new SimpleIntegerProperty(SQLUtil.getSaleNumbers(user.getUserId()));
    }

    public String getUserId() {
        return userId.get();
    }

    public SimpleStringProperty userIdProperty() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getUserSex() {
        return userSex.get();
    }

    public SimpleStringProperty userSexProperty() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex.set(userSex);
    }

    public String getUserEmail() {
        return userEmail.get();
    }

    public SimpleStringProperty userEmailProperty() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail.set(userEmail);
    }

    public String getUserPhone() {
        return userPhone.get();
    }

    public SimpleStringProperty userPhoneProperty() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone.set(userPhone);
    }

    public String getUserBirthday() {
        return userBirthday.get();
    }

    public SimpleStringProperty userBirthdayProperty() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday.set(userBirthday);
    }

    public String getRegisterTime() {
        return registerTime.get();
    }

    public SimpleStringProperty registerTimeProperty() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime.set(registerTime);
    }

    public String getOrLog() {
        return orLog.get();
    }

    public SimpleStringProperty orLogProperty() {
        return orLog;
    }

    public void setOrLog(String orLog) {
        this.orLog.set(orLog);
    }

    public double getMoney() {
        return money.get();
    }

    public SimpleDoubleProperty moneyProperty() {
        return money;
    }

    public void setMoney(double money) {
        this.money.set(money);
    }

    public int getPurchaseCount() {
        return purchaseCount.get();
    }

    public SimpleIntegerProperty purchaseCountProperty() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount.set(purchaseCount);
    }

    public int getSaleCount() {
        return saleCount.get();
    }

    public SimpleIntegerProperty saleCountProperty() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount.set(saleCount);
    }
}
