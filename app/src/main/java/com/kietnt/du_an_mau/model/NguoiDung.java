package com.kietnt.du_an_mau.model;

public class NguoiDung {
    private String userName;
    private String password;
    private String phone;
    private String hoTen;

    public NguoiDung() {
    }

    public NguoiDung(String userName, String passwoed, String phone, String hoTen) {
        this.userName = userName;
        this.password = passwoed;
        this.phone = phone;
        this.hoTen = hoTen;
    }

    public NguoiDung(String username, String sdt, String hoTen) {
        this.userName = username;
        this.phone = sdt;
        this.hoTen = hoTen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwoed) {
        this.password = passwoed;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "userName='" + userName + '\'' +
                ", passwoed='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", hoTen='" + hoTen + '\'' +
                '}';
    }
}
