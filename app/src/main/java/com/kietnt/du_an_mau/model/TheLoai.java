package com.kietnt.du_an_mau.model;

import java.io.Serializable;

public class TheLoai implements Serializable {
    private String maLoai;
    private String tenLoai;
    private String moTa;
    private int viTri;

    public TheLoai() {
    }

    public TheLoai(String maLoai, String tenLoai, String moTa, int viTri) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    @Override
    public String toString() {
        return getMaLoai() + " | " + getTenLoai();
    }
}
