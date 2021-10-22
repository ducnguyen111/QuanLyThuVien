package com.example.mob_duanmau.ui.FramentPhieuMuon;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mob_duanmau.Adapter.LoaiSachAdapter;
import com.example.mob_duanmau.Adapter.ThanhVienAdapter;
import com.example.mob_duanmau.DAO.LoaiSachDAO;
import com.example.mob_duanmau.DAO.ThanhVienDAO;
import com.example.mob_duanmau.Model.LoaiSach;
import com.example.mob_duanmau.Model.ThanhVien;
import com.example.mob_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentQuanLyLoaiSach extends Fragment {
    ListView mListView;
    FloatingActionButton fbloaisach;
    ArrayList<LoaiSach> loaiSachArrayList;
    Dialog dialog;
    EditText ed_maLoai, ed_tenLoai;
    Button btn_luu, btn_huy;
    LoaiSachDAO loaiSachDAO;
    LoaiSach item;
    LoaiSachAdapter loaiSachAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_sach,container,false);

        mListView = view.findViewById(R.id.lv_loaisach);
        fbloaisach = view.findViewById(R.id.flb_loaisach);
        loaiSachDAO = new LoaiSachDAO(getActivity());

        fbloaisach.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                opendialog(getActivity(),0);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = loaiSachArrayList.get(position);
                opendialog(getActivity(),1);
                return;
            }
        });
        return view;
    }
    protected void opendialog(final Context context, final int Type){
        dialog  = new Dialog(context);
        dialog.setContentView(R.layout.loaisach_dialog);
        ed_maLoai = dialog.findViewById(R.id.ed_maLoai);
        ed_tenLoai = dialog.findViewById(R.id.ed_tenLoai);
        btn_luu = dialog.findViewById(R.id.btn_luuLoaisach);
        btn_huy = dialog.findViewById(R.id.btn_HuyLoaisach);

        ed_maLoai.setEnabled(false);
        if (Type != 0){
            ed_maLoai.setText(String.valueOf(item.maLoai));
            ed_tenLoai.setText(item.tenLoai);

        }
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_luu .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSach();
                item.tenLoai = ed_tenLoai.getText().toString();
                if (Type==0){
                    if (loaiSachDAO.insert(item)>0){
                        Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else
                        Toast.makeText(context,"Thêm Thất bại",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    item.maLoai = Integer.parseInt(ed_maLoai.getText().toString());
                    if (loaiSachDAO.update(item)>0){
                        Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else
                        Toast.makeText(context,"Sửa Thất bại",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                capNhatlv();
            }
        });
        dialog.show();
    }
    void capNhatlv(){
        loaiSachArrayList = (ArrayList<LoaiSach>)loaiSachDAO.GetAll();
        loaiSachAdapter = new LoaiSachAdapter(getActivity(),this,loaiSachArrayList);
        mListView.setAdapter(loaiSachAdapter);
    }
    public void Xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (dialog1, which) -> {
            loaiSachDAO.Delete(Id);
            capNhatlv();
            dialog1.cancel();
        });
        builder.setNegativeButton("No", (dialog1, which) -> {dialog1.cancel();});
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
}