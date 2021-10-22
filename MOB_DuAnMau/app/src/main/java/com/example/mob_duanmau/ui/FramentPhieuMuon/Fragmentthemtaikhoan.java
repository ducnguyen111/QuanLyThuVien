package com.example.mob_duanmau.ui.FramentPhieuMuon;

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


public class Fragmentthemtaikhoan extends Fragment {
    ThuthuDAO dao;
    EditText edUser,edHoten,edPass,edRePass;
    Button btn_save, btn_cannel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentthemtaikhoan,container,false);
        edUser = view.findViewById(R.id.ed_tenDN);
        edHoten = view.findViewById(R.id.ed_HvT);
        edPass = view.findViewById(R.id.ed_MK);
        edRePass = view.findViewById(R.id.ed_NL_matkhau);
        btn_save = view.findViewById(R.id.btn_themtaikhoan);
        btn_cannel = view.findViewById(R.id.btn_huytaikhoan);

        dao = new ThuthuDAO(getContext());

        btn_cannel.setOnClickListener(v -> {
            edUser.setText("");
            edHoten.setText("");
            edPass.setText("");
            edRePass.setText("");
        });

        btn_save.setOnClickListener(v -> {Thuthu thuthu = new Thuthu();
            thuthu.maTT = edUser.getText().toString();
            thuthu.hoTen = edHoten.getText().toString();
            thuthu.matKhau = edPass.getText().toString();
            if (validate()>0){
                if (dao.insert(thuthu) > 0){
                    Toast.makeText(getContext(),"Lưu thành công",Toast.LENGTH_SHORT).show();
                    edUser.setText("");
                    edHoten.setText("");
                    edPass.setText("");
                    edRePass.setText("");
                }else
                    Toast.makeText(getContext(),"Lưu thất bại",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public int validate(){
        int check = 1;
        if (edUser.getText().length()==0||edHoten.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String Pass = edPass.getText().toString();
            String RePass = edRePass.getText().toString();
            if (!Pass.equals(RePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}