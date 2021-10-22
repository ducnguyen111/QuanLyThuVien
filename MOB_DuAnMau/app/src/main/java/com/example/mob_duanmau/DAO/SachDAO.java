package com.example.mob_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob_duanmau.Database.SQLiteHelper;
import com.example.mob_duanmau.Model.Sach;
import com.example.mob_duanmau.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
     SQLiteDatabase db;
    Context context;
    SQLiteHelper sqLiteHelper;

    public SachDAO( Context context) {
       sqLiteHelper=new SQLiteHelper(context);
       db= sqLiteHelper.getWritableDatabase();
       this.context=context;
    }

    public long insert(Sach obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach", obj.tenSach);
        contentValues.put("giaThue", obj.giaThue);
        contentValues.put("maLoai",obj.maLoai);
        return db.insert("Sach", null, contentValues);
    }

    public int update(Sach obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach", obj.tenSach);
        contentValues.put("giaThue", obj.giaThue);
        return db.update("Sach", contentValues, "maSach=?", new String[]{String.valueOf(obj.maSach)});
    }

    public int Delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    public List<Sach> GetAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

    private List<Sach> getData(String sql, String... selectionArgs) {

        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Sach sach = new Sach();
            sach.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            sach.tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
            sach.giaThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue")));
            sach.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            list.add(sach);
        }
        return list;
    }
}
