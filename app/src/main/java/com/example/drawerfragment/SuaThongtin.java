package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SuaThongtin extends AppCompatActivity {
    public String taikhoan,hoten,matkhau;
    public EditText hotenn,matkhauu;
    public TextView taikhoann;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suathongtin);

        Intent intent = getIntent();
        taikhoan=intent.getStringExtra("taikhoan");
        taikhoann=findViewById(R.id.tv_taikhoan_suathongtin);
        taikhoann.setText(taikhoan);
        hotenn=findViewById(R.id.et_hoten_suathongtin);
        hoten=hotenn.getText().toString();
        matkhauu=findViewById(R.id.et_matkhau_suathongtin);
        matkhau=matkhauu.getText().toString();
    }

    public void luu(View view) {
        if (hoten == "") {
            Toast.makeText(getApplicationContext(), "Nhập họ tên", Toast.LENGTH_SHORT).show();
        } else if (matkhau == "") {
            Toast.makeText(getApplicationContext(), "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            String url = "http://192.168.1.103/apisuathongtincanhan.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    hoten=hotenn.getText().toString();
                    matkhau=matkhauu.getText().toString();
                    Map<String, String> params = new HashMap<>();
                    params.put("taikhoan", taikhoan);
                    params.put("hoten", hoten);
                    params.put("matkhau", matkhau);
                    return params;
                }
            };
            Volley.newRequestQueue(this).add(request);
        }

    }
}
