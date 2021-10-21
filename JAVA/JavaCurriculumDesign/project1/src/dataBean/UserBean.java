package dataBean;

import java.util.Date;

public class UserBean {

    private String userId;      // 用户 ID 唯一
    private String password;    // 用户 密码
    private String userName;    // 用户姓名
    private int userSex;         // 用户性别 1 for male  0 for female
    private String userEmail;   // 用户邮箱
    private String userPhone;   // 用户手机
    private Date userBirthday;  // 用户生日
    private Date registerTime;  // 用户注册时间
    private Double money;   // 用户余额

    public UserBean(String userId, String password, String userName, int userSex, String userEmail, String userPhone, Date userBirthday, Date registerTime, Double money) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userSex = userSex;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userBirthday = userBirthday;
        this.registerTime = registerTime;
        this.money = money;
    }
    public UserBean() {}

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }
    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Date getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
