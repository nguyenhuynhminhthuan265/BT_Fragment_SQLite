package com.example.bt_fragment.entity;

public class Class {
    int MaLop;
    String TenLop;

    public Class(int maLop, String tenLop) {
        MaLop = maLop;
        TenLop = tenLop;
    }

    public Class() {
    }

    public int getMaLop() {
        return MaLop;
    }

    public void setMaLop(int maLop) {
        MaLop = maLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }

    @Override
    public String toString() {
        return "Class{" +
                "MaLop=" + MaLop +
                ", TenLop=" + TenLop +
                '}';
    }
}
