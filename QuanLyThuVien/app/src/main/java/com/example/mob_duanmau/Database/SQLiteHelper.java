package com.example.mob_duanmau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "PNLIB";
    static final int VERSION = 1;
    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu =
                "create table ThuThu (" +
                        "maTT TEXT PRIMARY KEY , " +
                        "hoTen TEXT NOT NULL, " +
                        "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);

        String createTableThanhVien =
                "create table ThanhVien (" +
                        "maTV INTEGER not null PRIMARY KEY AUTOINCREMENT, " +
                        "hoTen TEXT NOT NULL, " +
                        "namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);

        String createTableLoaiSach =
                "create table LoaiSach (" +
                        "MaLoai  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "TenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);

        String createTableSach =
                "create table Sach (" +
                        "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenSach TEXT NOT NULL, " +
                        "giaThue INTEGER NOT NULL, " +
                        "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);

        String createTablePhieuMuon =
                "create table PhieuMuon (" +
                        "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "maTT TEXT REFERENCES ThuThu(maTT), " +
                        "maTV INTEGER not null REFERENCES ThanhVien(maTV), " +
                        "maSach INTEGER REFERENCES Sach(maSach), " +
                        "tienThue INTEGER NOT NULL, " +
                        "ngay Text NOT NULL, " +
                        "traSach INTEGER NOT NULL)";
        db.execSQL(createTablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu ="drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien ="drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach ="drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach ="drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon ="drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
    }
}
