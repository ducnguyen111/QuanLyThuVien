package com.example.mob_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob_duanmau.Model.LoaiSach;
import com.example.mob_duanmau.Model.ThanhVien;
import com.example.mob_duanmau.R;

import java.util.ArrayList;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item_spinner,null);
        }
        final ThanhVien item = list.get(position);
        if (item != null){
            tvMaTV = v.findViewById(R.id.tvSpinnerthanhvienId2);
            tvMaTV.setText(item.maTV+".");
            tvTenTV = v.findViewById(R.id.tvSpinnerthanhvienTen2);
            tvTenTV.setText(item.hoTen);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item_spinner,null);
        }
        final ThanhVien item = list.get(position);
        if (item != null){
            tvMaTV = v.findViewById(R.id.tvSpinnerthanhvienId2);
            tvMaTV.setText(item.maTV+".");
            tvTenTV = v.findViewById(R.id.tvSpinnerthanhvienTen2);
            tvTenTV.setText(item.hoTen);
        }
        return v;
    }
}
