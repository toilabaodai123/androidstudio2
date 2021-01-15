package com.example.drawerfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimPhim extends AppCompatActivity {

    public EditText tenphimm;
    public RecyclerView recyclerView;
    public TimPhimAdapter adapter;
    public List<Phim_TimPhim> timPhimList;
    public String tenphimx;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timphim);
        recyclerView=findViewById(R.id.recyclerview_timphim);
        tenphimm=findViewById(R.id.et_tenphim_timphim);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        timPhimList=new ArrayList<>();
        loadphim();

    }

    private void loadphim() {
        String url = "http://192.168.1.103/apiloadphimtimphim.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0 ; i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String tenphim =  object.getString("tenphim");
                        String loaiphim = object.getString("tenloaiphim");
                        timPhimList.add(new Phim_TimPhim(tenphim,loaiphim));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter=new TimPhimAdapter(timPhimList,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    public void timkiemphim(View view) {
        timPhimList=new ArrayList<>();
        String url = "http://192.168.1.103/apitimphim.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    tenphimx=tenphimm.getText().toString();
                    Toast.makeText(getApplicationContext(),tenphimx,Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0 ; i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String tenphim =  object.getString("tenphim");
                        String loaiphim = object.getString("tenloaiphim");
                        timPhimList.add(new Phim_TimPhim(tenphim,loaiphim));

                    }
                    adapter=new TimPhimAdapter(timPhimList,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),tenphimx,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                tenphimx=tenphimm.getText().toString();
                Map<String,String> params = new HashMap<>();
                params.put("tenphim",tenphimx);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
