package sava.data;

import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WorkerBean {
    private long workerId;          // bigint identity
    private String siteName = null;        // varchar 255
    private String workerCode = null;      // varchar 63
    private String workerName = null;      // varchar 31
    private String workerSex = null;       // varchar 7
    private java.sql.Date workerBirthday = null;    // datetime
    private String workerAddress = null;   // varchar 127
    private String workerPhone = null;     // varchar 31
    private String workerJob = null;       // varchar 31
    private String workerPosition = null;  // varchar 31
    private java.sql.Date registerTime = null;      // datetime
    private String workerPhoto = null;     // varchar 255

    public void setParameter(String Parameter, String value) throws ParseException {
        SimpleDateFormat doTime = new SimpleDateFormat("yyyy-MM-dd");
        switch (Parameter) {
            case "siteName" :           siteName = value; break;
            case "workerCode" :         workerCode = value; break;
            case "workerName" :         workerName = value; break;
            case "workerSex" :          workerSex = value; break;
            case "workerBirthday" :     workerBirthday = new Date(doTime.parse(value).getTime()); break;
            case "workerAddress" :      workerAddress = value; break;
            case "workerPhone" :        workerPhone = value; break;
            case "workerJob" :          workerJob = value; break;
            case "workerPosition" :     workerPosition = value; break;
            case "registerTime" :       registerTime = new Date(doTime.parse(value).getTime()); break;
            case "workerPhoto" : workerPhoto = value; break;
        }
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerSex() {
        return workerSex;
    }

    public void setWorkerSex(String workerSex) {
        this.workerSex = workerSex;
    }

    public java.sql.Date getWorkerBirthday() {
        return workerBirthday;
    }

    public void setWorkerBirthday(java.sql.Date workerBirthday) {
        this.workerBirthday = workerBirthday;
    }

    public String getWorkerAddress() {
        return workerAddress;
    }

    public void setWorkerAddress(String workerAddress) {
        this.workerAddress = workerAddress;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getWorkerJob() {
        return workerJob;
    }

    public void setWorkerJob(String workerJob) {
        this.workerJob = workerJob;
    }

    public String getWorkerPosition() {
        return workerPosition;
    }

    public void setWorkerPosition(String workerPosition) {
        this.workerPosition = workerPosition;
    }

    public java.sql.Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(java.sql.Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getWorkerPhoto() {
        return workerPhoto;
    }

    public void setWorkerPhoto(String workerPhoto) {
        this.workerPhoto = workerPhoto;
    }

    public String[] toList() {
        return new String[] {workerName, workerCode, siteName,
                new SimpleDateFormat("yyyy-MM-dd").format(workerBirthday),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(registerTime)};
    }

    public static WorkerBean toWorker(ResultSet rs) throws SQLException {
        WorkerBean answer = new WorkerBean();
        answer.setWorkerId(rs.getLong("workerId"));
        answer.setSiteName(rs.getString("siteName"));
        answer.setWorkerCode(rs.getString("workerCode"));
        answer.setWorkerName(rs.getString("workerName"));
        answer.setWorkerSex(rs.getString("workerSex"));
        answer.setWorkerBirthday(rs.getDate("workerBirthday"));
        answer.setWorkerAddress(rs.getString("workerAddress"));
        answer.setWorkerPhone(rs.getString("workerPhone"));
        answer.setWorkerJob(rs.getString("workerJob"));
        answer.setWorkerPosition(rs.getString("workerPosition"));
        answer.setRegisterTime(rs.getDate("registerTime"));
        answer.setWorkerPhoto(rs.getString("workerPhoto"));
        return answer;
    }

    public long getWorkerId() {
        return workerId;
    }
}
