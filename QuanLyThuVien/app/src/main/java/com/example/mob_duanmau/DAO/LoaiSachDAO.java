package com.example.mob_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mob_duanmau.Database.SQLiteHelper;
import com.example.mob_duanmau.Model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase sqLiteDatabase;

    public LoaiSachDAO(Context context){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    }
    public long insert(LoaiSach obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenLoai",obj.tenLoai);
        return sqLiteDatabase.insert("LoaiSach",null,contentValues);
    }
    public int update(LoaiSach obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenLoai",obj.tenLoai);
        return sqLiteDatabase.update("LoaiSach",contentValues,"MaLoai=?",new String[]{String.valueOf(obj.maLoai)});
    }
    public int Delete(String id){
        return sqLiteDatabase.delete("LoaiSach","MaLoai=?",new String[]{id});
    }
    public List<LoaiSach> GetAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }
    public LoaiSach getID(String id){
        String sql  = "SELECT * FROM LoaiSach WHERE MaLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }

    private List<LoaiSach> getData(String sql,String...selectionArgs){

        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaLoai")));
            loaiSach.tenLoai = cursor.getString(cursor.getColumnIndex("TenLoai"));
            list.add(loaiSach);
        }
        return list;
    }
}
