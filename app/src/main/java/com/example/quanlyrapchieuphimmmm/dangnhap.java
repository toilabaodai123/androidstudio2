package com.example.quanlyrapchieuphimmmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

import java.util.HashMap;
import java.util.Map;

public class dangnhap extends AppCompatActivity {

    private RequestQueue request;
    private EditText taikhoann;
    private EditText matkhauu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
    }

    public void dangnhap(View view) {
        taikhoann = findViewById(R.id.ettaikhoan_dangnhap);
        matkhauu = findViewById(R.id.etmatkhau_dangnhap);
        int dem = 0;
        String url2 = "http://192.168.1.103/apikhachhang2.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                for(int i = 0 ; i <response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String taikhoan = object.getString("taikhoan");
                        String matkhau = object.getString("matkhau");
                        String hoten = object.getString("hoten");

                        if(taikhoann.getText().toString().equalsIgnoreCase(taikhoan) && matkhauu.getText().toString().equalsIgnoreCase(matkhau))
                        {
                            Toast.makeText(getApplicationContext(),"Thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(dangnhap.this,MainActivity.class);
                            //Toast.makeText(getApplicationContext(),taikhoan+matkhau+hoten, Toast.LENGTH_SHORT).show();
                            //intent.putExtra("id",phimList.get(position).getId());
                            intent.putExtra("taikhoan",taikhoan);
                            intent.putExtra("hoten",hoten);
                            startActivity(intent);
                            break;
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Sai thông tin",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}

