package com.android.login;

public class UserDetails {

    String mail,mobile,password;

    public UserDetails(String mail, String mobile, String password) {
        this.mail = mail;
        this.mobile = mobile;
        this.password = password;
    }

    public UserDetails(){}


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
