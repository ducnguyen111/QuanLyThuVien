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

import com.example.mob_duanmau.Model.LoaiSach;
import com.example.mob_duanmau.Model.ThanhVien;
import com.example.mob_duanmau.R;
import com.example.mob_duanmau.ui.FramentPhieuMuon.FragmentQuanLyLoaiSach;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    ImageView imageViewDele;
    private Context context;
    FragmentQuanLyLoaiSach fragmentQuanLyLoaiSach;
    private ArrayList<LoaiSach> loaiSachArrayList;
    TextView tv_maloai, tv_tenloai;
    public LoaiSachAdapter(@NonNull Context context,FragmentQuanLyLoaiSach fragmentQuanLyLoaiSach, ArrayList<LoaiSach> loaiSachArrayList) {
        super(context, 0,loaiSachArrayList);
        this.context = context;
        this.fragmentQuanLyLoaiSach = fragmentQuanLyLoaiSach;
        this.loaiSachArrayList = loaiSachArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.loaisach_item,null);
        }
        final LoaiSach item = loaiSachArrayList.get(position);
        if (item != null){
            tv_maloai = v.findViewById(R.id.tv_maLoai);
            tv_tenloai = v.findViewById(R.id.tv_tenLoai);
            imageViewDele = v.findViewById(R.id.img_delete);

            tv_maloai.setText("Mã sách :"+item.maLoai);
            tv_tenloai.setText("Tên loại :"+item.tenLoai);

        }
        imageViewDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentQuanLyLoaiSach.Xoa(String.valueOf(item.maLoai));
            }
        });
        return v;
    }
}
