package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ThanhToan extends AppCompatActivity {
    public String taikhoan , phim , rap , ghe ,xuatchieu,tongtien;
    public TextView taikhoann,phimm,rapp,ghee,xuatchieuu,tongtienn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan);

        Intent intent =getIntent();
        taikhoan=intent.getStringExtra("taikhoan");
        phim=intent.getStringExtra("phim");
        rap=intent.getStringExtra("rap");
        ghe=intent.getStringExtra("ghe");
        xuatchieu=intent.getStringExtra("xuatchieu");

        taikhoann=findViewById(R.id.tv_taikhoan_thanhtoan3);
        taikhoann.setText(taikhoan);
        phimm=findViewById(R.id.tv_tenphim_thanhtoan2);
        phimm.setText(phim);
        rapp=findViewById(R.id.tv_rap_thanhtoan2);
        rapp.setText(rap);
        ghee=findViewById(R.id.tv_ghe_thanhtoan2);
        ghee.setText(ghe);
        xuatchieuu=findViewById(R.id.tv_xuatchieu_thanhtoan2);
        xuatchieuu.setText(xuatchieu);
        tongtienn=findViewById(R.id.tv_tongtien_thanhtoan2);


        loadgiatienphim();

    }

    private void loadgiatienphim() {
        String url = "http://192.168.1.103/apiloadgiatienphim.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tongtienn.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                ghe=ghee.getText().toString();
                Map<String,String> params = new HashMap<>();
                params.put("idphim",phim);
                params.put("xuatchieu",xuatchieu);
                params.put("ghe",ghe);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }


    public void thanhtoan(View view) {
        if(taikhoan.equalsIgnoreCase("Tài khoản")){
            Toast.makeText(getApplicationContext(),"Đăng nhập?",Toast.LENGTH_SHORT).show();
        }
        else {
            String url = "http://192.168.1.103/apithemve.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();

                    if (response == "1") {
                        Toast.makeText(getApplicationContext(), "Không thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ThanhToan.this,ThongTinCaNhan.class);
                        intent.putExtra("taikhoan",taikhoan);
                        startActivity(intent);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    tongtien=tongtienn.getText().toString();
                    params.put("dmy", xuatchieu);
                    params.put("rap", rap);
                    params.put("phim", phim);
                    params.put("taikhoan", taikhoan);
                    params.put("ghe", ghe);
                    params.put("tongtien",tongtien);
                    return params;
                }
            };

            Volley.newRequestQueue(this).add(request);
        }
    }
}
