package com.example.mob_duanmau.ui.FramentPhieuMuon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mob_duanmau.Adapter.ThanhVienAdapter;
import com.example.mob_duanmau.DAO.ThanhVienDAO;
import com.example.mob_duanmau.Model.ThanhVien;
import com.example.mob_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentQuanLyThanhVien extends Fragment {
    ListView mListView;
    FloatingActionButton fbthanhvien;
    ArrayList<ThanhVien> list;
    Dialog dialog;
    EditText ed_maTV, ed_tenTV, ed_namSinh;
    Button btn_save, btn_delete;
    ThanhVien item;

    static ThanhVienDAO thanhVienDAO;
    ThanhVienAdapter thanhVienAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quan_ly_thanh_vien,container,false);

        mListView = view.findViewById(R.id.lv_thanhvien);
        fbthanhvien = view.findViewById(R.id.flb_Tv);
        thanhVienDAO = new ThanhVienDAO(getActivity());

        fbthanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(getActivity(),0);
            }
        });
        mListView.setOnItemClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            opendialog(getActivity(),1);
            return;
        });
        return view;
    }
    protected void opendialog(final Context context, final int Type){
        dialog  = new Dialog(context);
        dialog.setContentView(R.layout.thanhvien_dialog);
        ed_maTV = dialog.findViewById(R.id.ed_maT);
        ed_tenTV = dialog.findViewById(R.id.ed_tenTV);
        ed_namSinh = dialog.findViewById(R.id.ed_namSinh);
        btn_save = dialog.findViewById(R.id.btn_luuTV);
        btn_delete = dialog.findViewById(R.id.btn_Huy);

        ed_maTV.setEnabled(false);
        if (Type != 0){
            ed_maTV.setText(String.valueOf(item.maTV));
            ed_tenTV.setText(item.hoTen);
            ed_namSinh.setText(item.namSinh);
        }
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThanhVien();
                item.hoTen=ed_tenTV.getText().toString();
                item.namSinh=ed_namSinh.getText().toString();
                if (Type==0){
                    if (thanhVienDAO.insert(item)>0){
                        Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else
                        Toast.makeText(context,"Thêm Thất bại",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    item.maTV = Integer.parseInt(ed_maTV.getText().toString());
                    if (thanhVienDAO.update(item)>0){
                        Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context,"Sửa Thất bại",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
                capNhatlv();
            }
        });
        dialog.show();
    }
    void capNhatlv(){
        list = (ArrayList<ThanhVien>)thanhVienDAO.GetAll();
        thanhVienAdapter = new ThanhVienAdapter(getActivity(),this,list);
        mListView.setAdapter(thanhVienAdapter);
    }
    public void Xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                thanhVienDAO.Delete(Id);
                capNhatlv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No",((dialog1, which) -> {dialog1.cancel();}));
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
}