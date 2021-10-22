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

import com.example.mob_duanmau.Model.ThanhVien;
import com.example.mob_duanmau.R;
import com.example.mob_duanmau.ui.FramentPhieuMuon.FragmentQuanLyThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {

    private Context context;
    FragmentQuanLyThanhVien fragmentQuanLyThanhVien;
    private ArrayList<ThanhVien> list;
    TextView tv_maTV, tv_tenTV, tv_namSinh;
    ImageView imgdele;
    public ThanhVienAdapter(@NonNull Context context, FragmentQuanLyThanhVien fragmentQuanLyThanhVien, ArrayList<ThanhVien> list) {
        super(context, 0,list);
        this.context = context;
        this.fragmentQuanLyThanhVien = fragmentQuanLyThanhVien;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.thanhvien_item,null);
        }
        final ThanhVien item = list.get(position);
        if (item != null){
            tv_maTV = v.findViewById(R.id.tv_maTV);
            tv_tenTV = v.findViewById(R.id.tv_tenTV);
            tv_namSinh = v.findViewById(R.id.tv_namSinh);
            imgdele = v.findViewById(R.id.img_delete);

            tv_maTV.setText("Mã thành viên :"+item.maTV);
            tv_tenTV.setText("Tên thành viên :"+item.hoTen);
            tv_namSinh.setText("Năm Sinh :"+item.namSinh);
        }
        imgdele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentQuanLyThanhVien.Xoa(String.valueOf(item.maTV));
            }
        });
        return v;
    }
}
