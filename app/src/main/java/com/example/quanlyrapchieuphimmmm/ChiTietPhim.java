package com.example.quanlyrapchieuphimmmm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChiTietPhim extends AppCompatActivity {

    private TextView idphim ;
    private TextView tenphimm ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietphim);

        Intent intent =getIntent();
        String id = intent.getStringExtra("id");
        String tenphim = intent.getStringExtra("tenphim");
        Toast.makeText(this,id+"     "+tenphim, Toast.LENGTH_SHORT).show();
        idphim=(TextView) findViewById(R.id.tvchitietIdphim);
        idphim.setText(id);
        tenphimm=(TextView) findViewById(R.id.tvchitietTenphim);
        tenphimm.setText(tenphim);




    }
}
