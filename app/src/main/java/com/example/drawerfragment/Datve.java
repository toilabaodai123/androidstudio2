package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Datve extends AppCompatActivity {
    private TextView tenphimm ,taikhoan;

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
    }
}
