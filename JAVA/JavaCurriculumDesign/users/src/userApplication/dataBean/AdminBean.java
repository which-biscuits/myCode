package userApplication.dataBean;

import java.util.Date;

public class AdminBean {

    private String adminId;
    private String password;
    private String adminName;
    private int adminSex;
    private String adminEmail;
    private String adminPhone;
    private Date registerTime;

    public String getAdminId() {
        return adminId;
    }
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getAdminSex() {
        return adminSex;
    }
    public void setAdminSex(int adminSex) {
        this.adminSex = adminSex;
    }

    public String getAdminEmail() {
        return adminEmail;
    }
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }
    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public Date getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
