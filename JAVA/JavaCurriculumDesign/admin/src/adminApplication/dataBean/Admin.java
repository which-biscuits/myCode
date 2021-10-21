package adminApplication.dataBean;

import javafx.beans.property.SimpleStringProperty;

public class Admin {
    private final SimpleStringProperty adminId;
    private final SimpleStringProperty adminPassword;
    private final SimpleStringProperty adminName;
    private final SimpleStringProperty adminSex;
    private final SimpleStringProperty adminEmail;
    private final SimpleStringProperty adminPhone;
    private final SimpleStringProperty registerTime;

    public Admin(AdminBean admin) {
        this.adminId = new SimpleStringProperty(admin.getAdminId());
        this.adminPassword = new SimpleStringProperty(admin.getPassword());
        this.adminName = new SimpleStringProperty(admin.getAdminName());
        this.adminSex = new SimpleStringProperty(admin.getAdminSex() == 0 ? "女" : "男");
        this.adminEmail = new SimpleStringProperty(admin.getAdminEmail());
        this.adminPhone = new SimpleStringProperty(admin.getAdminPhone());
        this.registerTime = new SimpleStringProperty(admin.getRegisterTime().toString());
    }

    public String getAdminId() {
        return adminId.get();
    }

    public SimpleStringProperty adminIdProperty() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId.set(adminId);
    }

    public String getAdminPassword() {
        return adminPassword.get();
    }

    public SimpleStringProperty adminPasswordProperty() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword.set(adminPassword);
    }

    public String getAdminName() {
        return adminName.get();
    }

    public SimpleStringProperty adminNameProperty() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName.set(adminName);
    }

    public String getAdminSex() {
        return adminSex.get();
    }

    public SimpleStringProperty adminSexProperty() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex.set(adminSex);
    }

    public String getAdminEmail() {
        return adminEmail.get();
    }

    public SimpleStringProperty adminEmailProperty() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail.set(adminEmail);
    }

    public String getAdminPhone() {
        return adminPhone.get();
    }

    public SimpleStringProperty adminPhoneProperty() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone.set(adminPhone);
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
}
