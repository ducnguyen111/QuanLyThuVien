package com.example.mob_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob_duanmau.Model.TOP;
import com.example.mob_duanmau.R;
import com.example.mob_duanmau.ui.FramentPhieuMuon.FragmentThongke;

import java.util.ArrayList;

public class ThongkeAdapter extends ArrayAdapter<TOP> {
    private Context context;
    FragmentThongke fragment;
    private ArrayList<TOP> list;
    TextView tvSach, tvSl;

    public ThongkeAdapter(@NonNull Context context, FragmentThongke fragment,ArrayList<TOP> list ) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater  inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_top,null);
        }
        final TOP item = list.get(position);
        if (item != null){
            tvSach = v.findViewById(R.id.tvSachtop);
            tvSach.setText("Sách :"+item.tenSach);
            tvSl = v.findViewById(R.id.tvSoluong);
            tvSl.setText("Số lượng :"+item.soLuong);
        }
        return v;
    }
}
