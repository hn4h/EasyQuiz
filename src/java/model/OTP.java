package model;

import java.time.LocalDateTime;

public class OTP {
    private int id;
    private String userName;
    private boolean isUsed;
    private String otp;
    private LocalDateTime expiredTime;
    public OTP(int id, boolean isUsed, String otp, LocalDateTime expiredTime) {
        this.id = id;
        this.isUsed = isUsed;
        this.otp = otp;
        this.expiredTime = expiredTime;
    }
    public OTP( String userName , boolean isUsed, String otp, LocalDateTime expiredTime) {
        this.userName = userName;
        this.isUsed = isUsed;
        this.otp = otp;
        this.expiredTime = expiredTime;
    }
    public OTP() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }
}
