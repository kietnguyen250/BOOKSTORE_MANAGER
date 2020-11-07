package com.kietnt.du_an_mau.model;

public class HoaDonChiTiet {
    private String maHDCT;
    private String maHoaDon;
    private String maSach;
    private int soLuongMua;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHDCT, String maHoaDon, String maSach, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.maHoaDon = maHoaDon;
        this.maSach = maSach;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet(String maHoaDon, String maSach, int soLuongMua) {
        this.maHoaDon = maHoaDon;
        this.maSach = maSach;
        this.soLuongMua = soLuongMua;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", hoaDon=" + maHoaDon +
                ", sach=" + maSach +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
