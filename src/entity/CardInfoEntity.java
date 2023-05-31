package entity;

import java.sql.Date;

public class CardInfoEntity {
    /**
     * 账户名称
     */
    private String accountNum;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户余额
     */
    private double bal;



    /**
     * 是否已销户
     */
    private String state;
    /**
     * 用户姓名
     */
    private String makeUserName;

    /**
     * 证件号码
     */
    private String certNo;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 开户日期
     */
    private Date openDt;


    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBal() {
        return bal;
    }

    public void setBal(double bal) {
        this.bal = bal;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getMakeUserName() {
        return makeUserName;
    }

    public void setMakeUserName(String makeUserName) {
        this.makeUserName = makeUserName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Date getOpenDt() {
        return openDt;
    }

    public void setOpenDt(Date openDt) {
        this.openDt = openDt;
    }



}
