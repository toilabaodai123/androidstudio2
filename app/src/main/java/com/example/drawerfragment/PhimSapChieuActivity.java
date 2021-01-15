package com.example.drawerfragment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhimSapChieuActivity extends AppCompatActivity {

    public TextView tenphimm,loaiphimm;
    public String tenphim,loaiphim;
    public RecyclerView recyclerView;
    public PhimSapChieuAdapter adapter;
    public List<PhimSapChieu> phimSapChieuList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phimsapchieu);

        recyclerView=findViewById(R.id.recyclerview_phimsapchieu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        phimSapChieuList=new ArrayList<>();
        loadphimsapchieu();


    }

    private void loadphimsapchieu() {
        String url = "http://192.168.1.103/apiloadphimsapchieu.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0 ; i <response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String tenphim =  object.getString("tenphim");
                        String tenloaiphim = object.getString("tenloaiphim");
                        String noidungphim=object.getString("noidungphim");
                        phimSapChieuList.add(new PhimSapChieu(tenphim,tenloaiphim,noidungphim));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new PhimSapChieuAdapter(phimSapChieuList,getApplicationContext());
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
