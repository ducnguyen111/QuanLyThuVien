package com.example.mob_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob_duanmau.Database.SQLiteHelper;
import com.example.mob_duanmau.Model.LoaiSach;
import com.example.mob_duanmau.Model.PhieuMuon;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase sqLiteDatabase;

    public PhieuMuonDAO(Context context){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    }
    public long insert(PhieuMuon obj){
        ContentValues contentValues = new ContentValues();
     //   contentValues.put("maPm",obj.maPM);cái này là tự tăng ko cần em hiểu ko
//        contentValues.put("maTT",obj.maTT);
        contentValues.put("maTV",obj.maTV);
        contentValues.put("maSach",obj.maSach);
        contentValues.put("tienThue",obj.tienThue);
        contentValues.put("ngay",obj.ngay);
        contentValues.put("traSach",obj.traSach);
        return sqLiteDatabase.insert("PhieuMuon",null,contentValues);
    }
    public int update(PhieuMuon obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maPm",obj.maPM);
       // contentValues.put("maTT",obj.maTT);
        contentValues.put("maTV",obj.maTV);
        contentValues.put("maSach",obj.maSach);
        contentValues.put("ngay",obj.ngay);
        contentValues.put("tienThue",obj.tienThue);
        contentValues.put("traSach",obj.traSach);
        return sqLiteDatabase.update("PhieuMuon",contentValues,"maPM=?",new String[]{String.valueOf(obj.maPM)});
    }
    public int Delete(String id){
        return sqLiteDatabase.delete("PhieuMuon","maPM=?",new String[]{id});
    }
    public List<PhieuMuon> GetAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }
    public PhieuMuon getID(String id){
        String sql  = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }
    private List<PhieuMuon> getData(String sql,String...selectionArgs){

        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.maPM = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPM")));
            phieuMuon.maTT = cursor.getString(cursor.getColumnIndex("maTT"));
            phieuMuon.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
            phieuMuon.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            phieuMuon.ngay = cursor.getString(cursor.getColumnIndex("ngay"));
            phieuMuon.tienThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienThue")));
            phieuMuon.traSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("traSach")));
            list.add(phieuMuon);

        }
        return list;
    }
}
