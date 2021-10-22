package com.example.mob_duanmau.ui.FramentPhieuMuon;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mob_duanmau.Adapter.PhieumuonAdapter;
import com.example.mob_duanmau.Adapter.SachSpinnerAdapter;
import com.example.mob_duanmau.Adapter.ThanhVienSpinnerAdapter;
import com.example.mob_duanmau.DAO.PhieuMuonDAO;
import com.example.mob_duanmau.DAO.SachDAO;
import com.example.mob_duanmau.DAO.ThanhVienDAO;
import com.example.mob_duanmau.Model.PhieuMuon;
import com.example.mob_duanmau.Model.Sach;
import com.example.mob_duanmau.Model.ThanhVien;
import com.example.mob_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.time.LocalDateTime.now;


public class FragmentPhieuMuon extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fb;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    EditText tvNgay, tvtienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCannel;
    static PhieuMuonDAO dao;
    PhieumuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhvien;
    ThanhVienDAO thanhVienDAO;
    int mathanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    int maSach, tienThue;
    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear,mMonth,mDay;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        lv =view.findViewById(R.id.lv_phieumuon);
        fb= view.findViewById(R.id.flb_phieumuon);
        fb.setOnClickListener(v -> {
            opendialog(getActivity(),0);
        });
        dao = new PhieuMuonDAO(getActivity());
        lv.setOnItemClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            opendialog(getActivity(),1);
            return;
        });
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void opendialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieumuon_dialog);
        edMaPM = dialog.findViewById(R.id.tvMaphieuDialog);
        spTV = dialog.findViewById(R.id.spinnerThanhvien);
        spSach = dialog.findViewById(R.id.spinnerSach);
        tvNgay = dialog.findViewById(R.id.tvNgaythueDialog);
        tvtienThue = dialog.findViewById(R.id.tvTienthueDialog);
        chkTraSach = dialog.findViewById(R.id.checkBoxPhieumuon);
        btnCannel = dialog.findViewById(R.id.btnhuyMaP);
        btnSave = dialog.findViewById(R.id.btnLuuMaP);
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhvien = new ArrayList<>();
        listThanhvien = (ArrayList<ThanhVien>) thanhVienDAO.GetAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhvien);
        spTV.setAdapter(thanhVienSpinnerAdapter)    ;

        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mathanhVien = listThanhvien.get(position).maTV;
                Toast.makeText(context,"Chọn :"+listThanhvien.get(position).hoTen,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<>();
        listSach = (ArrayList<Sach>) sachDAO.GetAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).maSach;
                tienThue = listSach.get(position).giaThue;
                Toast.makeText(context,"Chọn :"+listSach.get(position).tenSach,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaPM.setEnabled(false);
        if (type != 0){
            edMaPM.setText(String.valueOf(item.maPM));
            for (int i = 0; i < listThanhvien.size(); i++)
                if (item.maTV == (listThanhvien.get(i).maTV)){
                    positionTV = i;
                }
            spTV.setSelection(positionTV);
             for (int i = 0; i < listSach.size(); i++ )
                 if (item.maTV == (listSach.get(i).maSach)){
                     positionSach = i;
                 }
             spSach.setSelection(positionSach);
                 tvNgay.setText("Ngày Thuê :"+item.ngay);
                 tvtienThue.setText("Tiền thuê :"+item.tienThue);
                 if (item.traSach==1){
                     chkTraSach.setChecked(true);
                 }else {
                     chkTraSach.setChecked(false);
                 }
        }
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            item = new PhieuMuon();
            item.maSach = maSach;
            item.maTV = mathanhVien;
            item.tienThue= tienThue;
            item.ngay=tvNgay.getText().toString();
            if (chkTraSach.isChecked()){
                item.traSach=1;
            }else {
                item.traSach=0;
            }
            if (type==0){
                if (dao.insert(item)>0){
                    Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context,"Thêm Thất bại",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }else {
                item.maPM =Integer.parseInt(edMaPM.getText().toString());
                if (dao.update(item)>0){
                    Log.e("UPDATE ERRO :","ZZZSASASASASA");
                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
            dialog.dismiss();
            capnhaplv();
        });
        dialog.show();
    }
    void capnhaplv(){
        list = (ArrayList<PhieuMuon>) dao.GetAll();
        adapter = new PhieumuonAdapter(getActivity(),this,list);
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
                capnhaplv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No",(dialog1, which) -> {
            dialog1.cancel();
        });
    }
}