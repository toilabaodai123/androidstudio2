package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gheActivity extends AppCompatActivity implements gheInterface{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ghe> gheList;
    private RequestQueue request;
    public String dmy,rap,phim,taikhoan;
    public TextView xa,xb,xc,xd,ak;
    public ArrayList<String> testt;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghe);

        testt = new ArrayList<>();
        Intent intent2 =getIntent();
        dmy = intent2.getStringExtra("dmy");
        rap = intent2.getStringExtra("rap");
        phim = intent2.getStringExtra("phim");
        taikhoan=intent2.getStringExtra("taikhoan");
        xa=findViewById(R.id.tvtaikhoan_ghe);
        xa.setText(taikhoan);
        xb=findViewById(R.id.tvphim_ghe);
        xb.setText(phim);
        xc=findViewById(R.id.tv_xuatchieu_ghe);
        xc.setText(dmy);
        xd=findViewById(R.id.tvrap_ghe);
        xd.setText(rap);
        //Toast.makeText(getApplicationContext(),dmy+" "+rap+" "+ phim, Toast.LENGTH_SHORT).show();

        recyclerView=findViewById(R.id.recyclerviewghe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        gheList=new ArrayList<>();
        //loaddsghe();
        loaddsghe2();
    }

    private void loaddsghe2()  {

        String url = "http://192.168.1.103/apitest2.php";
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),c, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray array=new JSONArray(response);
                    //Toast.makeText(getApplicationContext(),array.toString(), Toast.LENGTH_SHORT).show();
                    for (int i = 0 ; i<array.length();i++){
                        JSONObject object =array.getJSONObject(i);
                        String id=object.getString("id");
                        String tenghe=object.getString("tenghe");
                        gheList.add(new ghe(id,tenghe));
                    }
                    adapter = new gheAdapter(gheList,getApplicationContext(),gheActivity.this);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params= new HashMap<>();
                params.put("dmy",dmy);
                params.put("rap",rap);
                params.put("phim",phim);
                params.put("abc","abc");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }


    @Override
    public void onItemClick(int position) {
        String k = gheList.get(position).getId();
        ak=findViewById(R.id.tvghedachon_ghe);
        testt.add(k);
        Toast.makeText(this,testt.toString(), Toast.LENGTH_SHORT).show();
        if(ak.getText().toString().equalsIgnoreCase(""))
        ak.append(k);

        else
        {
            ak.append(" "+k);
        }

    }

    @Override
    public void onLongItemClick(int postition) {

    }

    public void testghe2(View view) {
        Intent intent = new Intent(gheActivity.this , ThanhToan.class);
        intent.putExtra("taikhoan",taikhoan);
        intent.putExtra("phim",phim);
        intent.putExtra("rap",rap);
        intent.putExtra("xuatchieu",dmy);
        intent.putExtra("ghe",ak.getText().toString());
        startActivity(intent);
    }




    /*private void loaddsghe() {
        String url="http://192.168.1.103/apitest.php";
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
                for(int i = 0 ; i<response.length();i++){
                    try {
                        JSONObject object= response.getJSONObject(i);
                        String id=object.getString("id");
                        String tenghe=object.getString("tenghe");
                        gheList.add(new ghe(id,tenghe));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new gheAdapter(gheList,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);



    }

     */


}
