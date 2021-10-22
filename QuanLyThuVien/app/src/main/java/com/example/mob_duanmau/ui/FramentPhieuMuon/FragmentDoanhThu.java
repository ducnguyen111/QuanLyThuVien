package com.example.mob_duanmau.ui.FramentPhieuMuon;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mob_duanmau.DAO.ThongKeDAO;
import com.example.mob_duanmau.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class FragmentDoanhThu extends Fragment {
    Button btnTuNgay, btnDenNgay, btnDoanhthu;
    EditText edTuNgay, edDenNgay;
    TextView tvDoanhthu;
    int mYear,mMonth,mDay;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM--dd");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edTuNgay = v.findViewById(R.id.ed_tungay);
        edDenNgay = v.findViewById(R.id.ed_denngay);
        tvDoanhthu = v.findViewById(R.id.tv_doanhthu);
        btnTuNgay = v.findViewById(R.id.btntungay);
        btnDenNgay = v.findViewById(R.id.btndenngay);
        btnDoanhthu = v.findViewById(R.id.btn_doanhthu);
        btnTuNgay.setOnClickListener(v1 -> {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateTungay,mYear,mMonth,mDay);
            d.show();
        });
        btnDenNgay.setOnClickListener(v1 -> {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateDenngay,mYear,mMonth,mDay);
            d.show();
        });
        btnDoanhthu.setOnClickListener(v1 -> {
            String tuNgay = edTuNgay.getText().toString();
            String denNgay = edTuNgay.getText().toString();
            ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
            tvDoanhthu.setText("Doanh thu : " + thongKeDAO.getDoanhthu(tuNgay,denNgay)+ "VNĐ");
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener mDateTungay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edTuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDateDenngay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}