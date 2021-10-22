package com.example.mob_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob_duanmau.DAO.LoaiSachDAO;
import com.example.mob_duanmau.Model.LoaiSach;
import com.example.mob_duanmau.Model.Sach;
import com.example.mob_duanmau.R;
import com.example.mob_duanmau.ui.FramentPhieuMuon.FragmentQuanLySach;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    FragmentQuanLySach fragment;
    private ArrayList<Sach> list;
    TextView tvMasach, tvTenSach, tvGiathue, tvLoai;
    ImageView imgDel;
    public SachAdapter(@NonNull Context context, FragmentQuanLySach fragment,ArrayList<Sach> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item = list.get(position);
        if (item != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.maLoai));
            tvMasach = v.findViewById(R.id.tvMasach);
            tvMasach.setText("Mã sách :" + item.maSach);
            tvTenSach = v.findViewById(R.id.tvtenSach);
            tvTenSach.setText("Tên  sách :"+item.tenSach);
            tvGiathue = v.findViewById(R.id.tvGiathue);
            tvGiathue.setText("Giá thuê :"+item.giaThue);
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Mã loại :"+loaiSach.maLoai);
            imgDel = v.findViewById(R.id.img_delete_sach);


        }
        imgDel.setOnClickListener(v1 -> {
            fragment.xoa(String.valueOf(item.maSach));
        });
        return v;
    }
}
