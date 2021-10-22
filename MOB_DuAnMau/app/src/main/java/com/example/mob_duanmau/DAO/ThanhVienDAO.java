package com.example.mob_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob_duanmau.Database.SQLiteHelper;
import com.example.mob_duanmau.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
   private SQLiteDatabase db;

   public ThanhVienDAO(Context context){
      SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
       db = sqLiteHelper.getWritableDatabase();
   }
   public long insert(ThanhVien obj){
       ContentValues contentValues = new ContentValues();
       contentValues.put("hoTen",obj.hoTen);
       contentValues.put("namSinh",obj.namSinh);
       return db.insert("ThanhVien",null,contentValues);
   }
   public int update(ThanhVien obj){
       ContentValues contentValues = new ContentValues();
       contentValues.put("hoTen",obj.hoTen);
       contentValues.put("namSinh",obj.namSinh);
       return db.update("ThanhVien",contentValues,"maTV=?",new String[]{String.valueOf(obj.maTV)});
   }
   public int Delete(String id){
       return db.delete("ThanhVien","maTV=?",new String[]{id});
   }
   public List<ThanhVien> GetAll(){
       String sql = "SELECT * FROM ThanhVien";
       return getData(sql);
   }
   public ThanhVien getID(String id){
       String sql  = "SELECT * FROM ThanhVien WHERE maTV=?";
       List<ThanhVien> list = getData(sql,id);
       return list.get(0);
   }
   private List<ThanhVien> getData(String sql,String...selectionArgs){

       List<ThanhVien> list = new ArrayList<>();
       Cursor cursor = db.rawQuery(sql,selectionArgs);
       while (cursor.moveToNext()){
           ThanhVien thanhVien = new ThanhVien();
           thanhVien.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
           thanhVien.hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
           thanhVien.namSinh = cursor.getString(cursor.getColumnIndex("namSinh"));
           list.add(thanhVien);
       }
       return list;
   }
}
