package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongTinCaNhan extends AppCompatActivity {
    public String taikhoan;
    public TextView taikhoann,hotenn;
    public RecyclerView recyclerView;
    public List<thongtinlichsu> thongtinlichsuList;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtincanhan);








        Intent intent=getIntent();
        taikhoan = intent.getStringExtra("taikhoan");
        taikhoann=findViewById(R.id.tv_taikhoan_thongtin);
        taikhoann.setText(taikhoan);
        hotenn=findViewById(R.id.tv_hoten_thongtin);



        recyclerView=findViewById(R.id.recyclerview_lichsu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        thongtinlichsuList=new ArrayList<>();







        loadhoten();
        loadlichsu();
    }

    private void loadlichsu() {

        String url = "http://192.168.1.103/apiloadvetheotaikhoan.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String tenphim = object.getString("tenphim");
                        String thoigiandat = object.getString("thoigiandat");
                        thongtinlichsuList.add(new thongtinlichsu(tenphim,thoigiandat));


                    }
                    adapter = new thongtinlichsuAdapter(thongtinlichsuList,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params = new HashMap<>();
                params.put("taikhoan",taikhoan);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);





    }

    private void loadhoten() {
        String url = "http://192.168.1.103/apiloadten.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hotenn.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params = new HashMap<>();
                params.put("taikhoan",taikhoan);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
