package com.example.mob_duanmau.Model;

import java.sql.Date;

public class PhieuMuon {
    public int maPM;
    public String maTT;
    public int maTV;
    public int maSach;
    public String ngay;
    public int tienThue;
    public int traSach;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, String ngay, int tienThue) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.tienThue = tienThue;
    }
}
