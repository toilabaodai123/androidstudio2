package com.example.drawerfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.ViewHolder> {

    public BinhLuanAdapter(List<BinhLuan> binhLuanList, Context context) {
        this.binhLuanList = binhLuanList;
        this.context = context;
    }

    public List<BinhLuan> binhLuanList;
    public Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_binhluan,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BinhLuan binhLuan = binhLuanList.get(position);
        holder.khachhang.setText(binhLuan.getTaikhoan());
        holder.noidung.setText(binhLuan.getNoidung());

    }

    @Override
    public int getItemCount() {
        return binhLuanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView khachhang,noidung;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            khachhang=itemView.findViewById(R.id.tv_taikhoan_binhluan);
            noidung=itemView.findViewById(R.id.tv_noidung_binhluan);
        }
    }
}
