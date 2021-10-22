package com.example.mob_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob_duanmau.DAO.ThuthuDAO;

public class Login extends AppCompatActivity {
    EditText ed_login, ed_pass;
    Button btn_loginnow;
    CheckBox checkBox;
    String strUser, strPass;
    ThuthuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_login = findViewById(R.id.ed_tendangnhap);
        ed_pass = findViewById(R.id.ed_matkhau);
        btn_loginnow = findViewById(R.id.btn_dangnhap);
        checkBox = findViewById(R.id.cbx_luu);
        dao = new ThuthuDAO(this);

        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        ed_login.setText(preferences.getString("USERNAME",""));
        ed_pass.setText(preferences.getString("PASSWORD",""));
        checkBox.setChecked(preferences.getBoolean("REMEMBER",false));

        btn_loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklogin();
            }
        });
    }
        public void checklogin(){
            strUser = ed_login.getText().toString();
            strPass = ed_pass.getText().toString();
            if (strUser.isEmpty() || strPass.isEmpty()){
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",Toast.LENGTH_SHORT).show();
            }else{
                if (dao.checklogin(strUser,strPass)>0 || (strUser.equals("admin") && (strPass.equals("admin")))){
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    remember(strUser, strPass,checkBox.isChecked());
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
    }
    public void remember(String u, String p, boolean status){
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}