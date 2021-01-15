package com.example.drawerfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private TextView taikhoann;
    private TextView matkhauu;
    private TextView test;
    private TextView test2;
    private TextView test3 , test4 ;
    public String a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.dangnhap_menu).setVisible(true);
        menu.findItem(R.id.dangky_menu).setVisible(true);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new MainFragment());
        fragmentTransaction.commit();
        View hoten = navigationView.getHeaderView(0);
        test3 = (TextView) hoten.findViewById(R.id.tvhoten);
        test4=hoten.findViewById(R.id.tvtaikhoan);
        a = test3.getText().toString();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.home_menu:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                fragmentTransaction.commit();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dangky_menu:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new SecondFragment());
                fragmentTransaction.commit();
                Toast.makeText(this, "đăng ký", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dangnhap_menu:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new DangnhapFragment());
                fragmentTransaction.commit();
                Toast.makeText(this, "Đăng nhập", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dangxuat_menu:

                test = findViewById(R.id.tvhoten);
                test2 = findViewById(R.id.tvtaikhoan);
                test.setText("Họ tên");
                test2.setText("Tài khoản");
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                fragmentTransaction.commit();

                break;
            case R.id.thongtin_menu:
                Intent intent = new Intent(MainActivity.this, ThongTinCaNhan.class);
                String x = test3.getText().toString();
                String xx = test4.getText().toString();
                intent.putExtra("taikhoan",xx);
                startActivity(intent);
                break;
            case R.id.timphim_menu:
                Intent intent2 = new Intent(MainActivity.this, TimPhim.class);
                startActivity(intent2);
                break;
        }

        return true;
    }

    public void dangnhap(View view) {
        taikhoann = findViewById(R.id.ettaikhoan_dangnhap);
        matkhauu = findViewById(R.id.etmatkhau_dangnhap);
        String url = "http://192.168.1.103/apikhachhang2.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                test = findViewById(R.id.tvhoten);
                String a = test.getText().toString();
                test2 = findViewById(R.id.tvtaikhoan);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String taikhoan = object.getString("taikhoan");
                        String matkhau = object.getString("matkhau");
                        String hoten = object.getString("hoten");
                        if (taikhoann.getText().toString().equalsIgnoreCase(taikhoan) && matkhauu.getText().toString().equalsIgnoreCase(matkhau)) {
                            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                            test.setText(hoten);
                            test2.setText(taikhoan);
                            fragmentManager = getSupportFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                            fragmentTransaction.commit();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);


    }

    public void check(View view) {
        Menu menu = navigationView.getMenu();

        if (test.getText().toString() == "Họ tên") {
            menu.findItem(R.id.dangnhap_menu).setVisible(true);
            menu.findItem(R.id.dangky_menu).setVisible(true);
            menu.findItem(R.id.dangxuat_menu).setVisible(false);
            menu.findItem(R.id.thongtin_menu).setVisible(false);
        } else {
            menu.findItem(R.id.dangxuat_menu).setVisible(true);
            menu.findItem(R.id.dangky_menu).setVisible(false);
            menu.findItem(R.id.dangnhap_menu).setVisible(false);
            menu.findItem(R.id.thongtin_menu).setVisible(true);
        }
    }

}