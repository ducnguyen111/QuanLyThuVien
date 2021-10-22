package com.example.mob_duanmau.ui.FramentPhieuMuon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob_duanmau.DAO.ThuthuDAO;
import com.example.mob_duanmau.Model.Thuthu;
import com.example.mob_duanmau.R;

import static android.content.Context.MODE_PRIVATE;


public class FragmentDoimatkhau extends Fragment {
    EditText edPassOld, edPass, edRepass;
    ThuthuDAO dao;
    Button btn_luu, btn_huy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimatkhau,container,false);

        edPassOld = view.findViewById(R.id.ed_matkhaucu);
        edPass = view.findViewById(R.id.ed_matkhauMoi);
        edRepass = view.findViewById(R.id.ed_nhaplaimatkhau);
        btn_luu = view.findViewById(R.id.btn_luuMK);
        btn_huy = view.findViewById(R.id.btn_HuyMK);

        dao = new ThuthuDAO(getContext());

        btn_huy.setOnClickListener(v -> {
            edPassOld.setText("");
            edPass.setText("");
            edRepass.setText("");
        });

        btn_luu.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE",MODE_PRIVATE);
            String user = sharedPreferences.getString("USERNAME","");
            if (validate()>0){
                Thuthu thuthu = dao.getID(user);
                thuthu.matKhau = edPass.getText().toString();
                dao.updatePass(thuthu);
                if (dao.updatePass(thuthu) > 0){
                    Toast.makeText(getContext(),"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    edPassOld.setText("");
                    edPass.setText("");
                    edRepass.setText("");
                }else {
                    Toast.makeText(getContext(),"Thay đổi mật khẩu thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public int validate(){
        int check = 1;
        if (edPassOld.getText().length()==0 || edPass.getText().length()==0 || edRepass.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",MODE_PRIVATE);
            String PassOld = pref.getString("PASSWORD","");
            String Pass = edPass.getText().toString();
            String RePass = edRepass.getText().toString();
            if (!PassOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai",Toast.LENGTH_SHORT).show();
                check = -1;
            }if (!Pass.equals(RePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}