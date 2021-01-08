package com.example.quanlyrapchieuphimmmm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private PhimAdapter a;
    private List<Phim> phims;
    private RequestQueue request ;
    private TextView taikhoan;
    private TextView hoten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        phims = new ArrayList<>();

        request = Volley.newRequestQueue(this);
        loadJSON();


        //Toolbar toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        Intent intent =getIntent();
        if( getIntent().getExtras() == null)
        {
            Toast.makeText(MainActivity.this,"a", Toast.LENGTH_SHORT).show();
        }
        else {
            String hotenn = intent.getStringExtra("hoten");
            Toast.makeText(getApplicationContext(),hotenn, Toast.LENGTH_SHORT).show();
            hoten=findViewById(R.id.tvhoten_menu);
            //hoten.setText(hotenn);


        }

        }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_dangnhap:
                Intent intentdn = new Intent(MainActivity.this,dangnhap.class);
                startActivity(intentdn);
                break;
            case R.id.nav_dangky:
                Intent intentdk = new Intent(MainActivity.this,dangky.class);
                startActivity(intentdk);
                break;
            case R.id.nav_dangxuat:
                break;
        }

        return true;
    }

    private void loadJSON() {
        String url="http://192.168.1.103/api.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                for(int i = 0 ; i< response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String tenphim = jsonObject.getString("tenphim");
                        phims.add(new Phim(id,tenphim));
                        adapter = new PhimAdapter(phims,MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                    catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonArrayRequest);

    }







}