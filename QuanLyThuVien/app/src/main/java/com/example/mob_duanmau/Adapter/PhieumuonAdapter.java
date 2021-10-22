package com.example.mob_duanmau.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob_duanmau.DAO.SachDAO;
import com.example.mob_duanmau.DAO.ThanhVienDAO;
import com.example.mob_duanmau.Model.PhieuMuon;
import com.example.mob_duanmau.Model.Sach;
import com.example.mob_duanmau.Model.ThanhVien;
import com.example.mob_duanmau.R;
import com.example.mob_duanmau.ui.FramentPhieuMuon.FragmentPhieuMuon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieumuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    FragmentPhieuMuon fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM, tvTenTV,tvtenSach,tvtienThue,tvNgay,tvtraSach;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;

    public PhieumuonAdapter(@NonNull Context context,FragmentPhieuMuon fragment,ArrayList<PhieuMuon> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_thanhvien,null);
        }
        final PhieuMuon item = list.get(position);
        if (item != null){

            tvMaPM = v.findViewById(R.id.tvMaphieu);
            tvMaPM.setText("Mã phiếu :"+item.maPM);
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.maSach));
            tvtenSach = v.findViewById(R.id.tvtenSach);
            tvtenSach.setText("Tên sách :"+sach.tenSach);
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.maTV));
            tvTenTV = v.findViewById(R.id.tvThanhvien);
            tvTenTV.setText("Ho Tên :"+thanhVien.hoTen);
            tvtienThue = v.findViewById(R.id.tvTienthue);
            tvtienThue.setText("Tiền thuê :"+item.tienThue);
            tvNgay = v.findViewById(R.id.tvNgaythue);
            tvNgay.setText("Ngày thuê :"+item.ngay);
            tvtraSach = v.findViewById(R.id.tvTrangthai);
            if (item.traSach==1){
                tvtraSach.setTextColor(Color.BLUE);
                tvtraSach.setText("Đã trả");
            }else {
                tvtraSach.setTextColor(Color.RED);
                tvtraSach.setText("Chưa trả");
            }
            imgDel = v.findViewById(R.id.imagePM);
        }
        imgDel.setOnClickListener(v1 -> {
            fragment.xoa(String.valueOf(item.maPM));
        });
        return v;
    }
}
