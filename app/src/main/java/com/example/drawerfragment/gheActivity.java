package com.example.drawerfragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

public class gheActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ghe> gheList;
    private RequestQueue request;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghe);

        recyclerView=findViewById(R.id.recyclerviewghe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        gheList=new ArrayList<>();
        loaddsghe();

    }

    private void loaddsghe() {
        String url="http://192.168.1.103/apiloadghe.php";
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
}
