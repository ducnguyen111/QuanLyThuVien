package com.example.mob_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob_duanmau.Database.SQLiteHelper;
import com.example.mob_duanmau.Model.Sach;
import com.example.mob_duanmau.Model.Thuthu;

import java.util.ArrayList;
import java.util.List;

public class ThuthuDAO {

    SQLiteDatabase sqLiteDatabase;

    public ThuthuDAO(Context context) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    }

    public long insert(Thuthu obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT", obj.maTT);
        contentValues.put("hoTen", obj.hoTen);
        contentValues.put("matKhau", obj.matKhau);
        return sqLiteDatabase.insert("ThuThu", null, contentValues);
    }

    public long updatePass(Thuthu obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", obj.hoTen);
        contentValues.put("matKhau", obj.matKhau);
        return sqLiteDatabase.update("ThuThu", contentValues, "maTT=?", new String[]{obj.maTT});
    }

    public int delete(String id) {
        return sqLiteDatabase.delete("ThuThu", "maTT=?", new String[]{id});
    }

    public List<Thuthu> GetAll() {
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public Thuthu getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<Thuthu> list = getData(sql, id);
        return list.get(0);
    }

    public List<Thuthu> getData(String sql, String... selection) {
        List<Thuthu> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selection);
        while (cursor.moveToNext()) {
            Thuthu obj = new Thuthu();
            obj.maTT = cursor.getString(cursor.getColumnIndex("maTT"));
            obj.hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
            obj.matKhau = cursor.getString(cursor.getColumnIndex("matKhau"));
            list.add(obj);
        }
        return list;
    }

    public int checklogin(String id, String password) {
        String sql = "SELECT * FROM  ThuThu WHERE maTT=? AND matKhau=?";
        List<Thuthu> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
