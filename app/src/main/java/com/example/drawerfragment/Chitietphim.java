package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chitietphim extends AppCompatActivity {
    private TextView idphim ;
    private TextView tenphimm,noidungphimm ;
    private TextView test3;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<BinhLuan> binhLuanList;
    private RequestQueue requestt;
    public String idx;
    public String taikhoan;

    public EditText noidungg;
    public String noidung;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietphim);

        Intent intent =getIntent();
        recyclerView=findViewById(R.id.recyclerview_binhluan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        binhLuanList = new ArrayList<>();
        String id = intent.getStringExtra("id");
        taikhoan = intent.getStringExtra("taikhoan");
        idx=id;
        String tenphim = intent.getStringExtra("tenphim");
        String noidungphim = intent.getStringExtra("noidungphim");
        noidungphimm=findViewById(R.id.tv_noidungphim);
        noidungphimm.setText(noidungphim);
        Toast.makeText(this,id+"     "+tenphim, Toast.LENGTH_SHORT).show();
        idphim=(TextView) findViewById(R.id.tv_idphim);
        idphim.setText(id);
        tenphimm=(TextView) findViewById(R.id.tv_tenphim);
        tenphimm.setText(tenphim);

        noidungg=(EditText)findViewById(R.id.et_noidung_binhluan);


        requestt=Volley.newRequestQueue(this);
        loadbinhluan();
    }

    private void loadbinhluan() {

        String url="http://192.168.1.103/apiloadbinhluan.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String taikhoan=object.getString("khachhang");
                        String noidung=object.getString("noidung");
                        binhLuanList.add(new BinhLuan(taikhoan,noidung));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new BinhLuanAdapter(binhLuanList,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idphim",idx);
                return params;
            }
        };
        requestt.add(request);
    }

    public void thembinhluan(View view) {
        if(taikhoan.equalsIgnoreCase("Tài khoản"))
            Toast.makeText(getApplicationContext(),"Đăng nhập ?", Toast.LENGTH_SHORT).show();
        else {
            String url = "http://192.168.1.103/apithembinhluan.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"OK", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    noidung=noidungg.getText().toString();
                    Map<String, String> params = new HashMap<>();
                    params.put("taikhoan", taikhoan);
                    params.put("noidung", noidung);
                    params.put("idphim", idx);

                    return params;
                }
            };
            requestt.add(request);
        }
    }
}
