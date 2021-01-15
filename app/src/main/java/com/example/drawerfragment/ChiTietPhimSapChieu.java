package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChiTietPhimSapChieu extends AppCompatActivity {

    public TextView tenphimm,noidungphimm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietphimsapchieu);
        Intent intent=getIntent();
        tenphimm=findViewById(R.id.tv_tenphimsapchieu_chitiet);
        noidungphimm=findViewById(R.id.tv_noidungphimsapchieu_chitiet);
        tenphimm.setText(intent.getStringExtra("tenphim"));
        noidungphimm.setText(intent.getStringExtra("noidung"));
    }
}
