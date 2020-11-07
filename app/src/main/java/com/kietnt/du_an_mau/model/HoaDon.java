package com.kietnt.du_an_mau.model;

import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private String NgayMua;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, String ngayMua) {
        this.maHoaDon = maHoaDon;
        NgayMua = ngayMua;
    }

    public HoaDon(String string, Date parse) {
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(String ngayMua) {
        NgayMua = ngayMua;
    }
}
