package com.example.mob_duanmau.ui.FramentPhieuMuon;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mob_duanmau.Adapter.LoaiSachSpinnerAdapter;
import com.example.mob_duanmau.Adapter.SachAdapter;
import com.example.mob_duanmau.DAO.LoaiSachDAO;
import com.example.mob_duanmau.DAO.SachDAO;
import com.example.mob_duanmau.Model.LoaiSach;
import com.example.mob_duanmau.Model.Sach;
import com.example.mob_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentQuanLySach extends Fragment {
    ListView lv;
    ArrayList<Sach> list;
    FloatingActionButton fb;
    Dialog dialog;
    EditText edMasach, edTenSach, edGiathue;
    Spinner spinner;
    Button btnSave, btnCannel;
    static SachDAO dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_quan_ly_sach2,container,false);
    lv = view.findViewById(R.id.lv_sach);
    fb = view.findViewById(R.id.flb_sach);
    dao = new SachDAO(getActivity());
    fb.setOnClickListener(v -> {
        opendialog(getActivity(),0);
    });
    lv.setOnItemClickListener((parent, view1, position1, id) -> {
        item = list.get(position1);
        opendialog(getActivity(),1);
        return;
    });
    return view;
}
    protected void opendialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMasach = dialog.findViewById(R.id.edMasach);
        edTenSach = dialog.findViewById(R.id.edTensach);
        edGiathue = dialog.findViewById(R.id.edGiathue);
        spinner = dialog.findViewById(R.id.spinner);
        btnCannel = dialog.findViewById(R.id.btn_cannel);
        btnSave = dialog.findViewById(R.id.btn_save);
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.GetAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context,listLoaiSach);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).maLoai;
                Toast.makeText(context,"Chon"+ listLoaiSach.get(position).tenLoai,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMasach.setEnabled(false);
        if (type != 0){
            edMasach.setText(String.valueOf(item.maSach));
            edTenSach.setText(item.tenSach);
            edGiathue.setText(String.valueOf(item.giaThue));
            for (int i = 0; i < listLoaiSach.size(); i++)
                if (item.maLoai == (listLoaiSach.get(i).maLoai)){
                    position = i;
                }
            Log.i("demo","posSach"+position);
                spinner.setSelection(position);
        }
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            item = new Sach();
            item.tenSach = edTenSach.getText().toString();
            item.giaThue = Integer.parseInt(edGiathue.getText().toString());
            item.maLoai = maLoaiSach;
            if (validate() > 0){
                if (type == 0){
                    if (dao.insert(item) > 0){
                        Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }else {
                    item.maSach = Integer.parseInt(edMasach.getText().toString());
                    if (dao.update(item) > 0){
                        Toast.makeText(context,"sửa thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context,"sửa thất bại",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
                capNhatlv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    void capNhatlv(){
        list = (ArrayList<Sach>) dao.GetAll();
        adapter = new SachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.Delete(Id);
                capNhatlv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public int validate(){
        int check = 1;
        if (edTenSach.getText().length() == 0 || edGiathue.getText().length() == 0){
            Toast.makeText(getContext(),"Phải nhật đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}