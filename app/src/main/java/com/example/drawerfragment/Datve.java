package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Datve extends AppCompatActivity {
    private TextView tenphimm ,taikhoan;
    ArrayList<String> xuatchieus;
    ArrayList<String> raps;
    Spinner spinner;
    Spinner spinner2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datve);

        Intent intent =getIntent();
        String tenphim = intent.getStringExtra("tenphim");
        String taikhoann = intent.getStringExtra("taikhoan");
        tenphimm=findViewById(R.id.tvtenphimdatve2);
        tenphimm.setText(tenphim);
        taikhoan=findViewById(R.id.testtaikhoannguoidung);
        taikhoan.setText(taikhoann);

        loadxuatchieu();
        loadrap();
        //loadghe();
    }

    private void loadxuatchieu() {
        String url = "http://192.168.1.103/apiloadxuatchieu.php";
        xuatchieus=new ArrayList<>();
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0;i<response.length();i++){
                    try {
                        Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
                        JSONObject object = response.getJSONObject(i);
                        //String idxuatchieu = object.getString("id");
                        String dmy = object.getString("dmy");
                        xuatchieus.add(dmy);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                spinner=(Spinner)findViewById(R.id.snxuatchieu_datve);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,xuatchieus);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void loadrap() {
        String url = "http://192.168.1.103/apiloadrap.php";
        raps=new ArrayList<>();
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0;i<response.length();i++){
                    try {
                        Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
                        JSONObject object = response.getJSONObject(i);
                        //String idxuatchieu = object.getString("id");
                        String tenrap = object.getString("tenrap");
                        raps.add(tenrap);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                spinner2=(Spinner)findViewById(R.id.snrap_datve);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,raps);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    public void chonghe(View view) {
        Intent intent = new Intent(this,gheActivity.class);
        startActivity(intent);
    }
}
