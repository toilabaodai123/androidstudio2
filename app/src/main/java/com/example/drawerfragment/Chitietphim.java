package com.example.drawerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

public class Chitietphim extends AppCompatActivity {
    private TextView idphim ;
    private TextView tenphimm ;
    private TextView test3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietphim);

        Intent intent =getIntent();
        String id = intent.getStringExtra("id");
        String tenphim = intent.getStringExtra("tenphim");
        Toast.makeText(this,id+"     "+tenphim, Toast.LENGTH_SHORT).show();
        idphim=(TextView) findViewById(R.id.tv_idphim);
        idphim.setText(id);
        tenphimm=(TextView) findViewById(R.id.tv_tenphim);
        tenphimm.setText(tenphim);
    }

}
