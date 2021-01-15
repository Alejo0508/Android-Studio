package com.example.appsisacademiafb;

public class user {

    private String usr;
    private String name;
    private String phone;
    private String password;

    public user(){

    }

    public user(String usr, String name, String phone, String password) {
        this.usr = usr;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
