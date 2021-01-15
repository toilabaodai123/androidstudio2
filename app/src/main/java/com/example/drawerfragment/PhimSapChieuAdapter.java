package com.example.drawerfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhimSapChieuAdapter extends RecyclerView.Adapter<PhimSapChieuAdapter.ViewHolder> {
    public PhimSapChieuAdapter(List<PhimSapChieu> phimSapChieuList, Context context) {
        this.phimSapChieuList = phimSapChieuList;
        this.context = context;
    }

    public List<PhimSapChieu> phimSapChieuList;
    public Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_phimsapchieu,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhimSapChieu phim = phimSapChieuList.get(position);
        holder.tenphim.setText(phim.getTenphim());
        holder.loaiphim.setText(phim.getLoaiphim());

    }

    @Override
    public int getItemCount() {
        return phimSapChieuList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tenphim,loaiphim;
        public Button xemchitiet;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tenphim=itemView.findViewById(R.id.tv_tenphim_sapchieu);
            loaiphim=itemView.findViewById(R.id.tv_tenloaiphim_sapchieu);
            xemchitiet=itemView.findViewById(R.id.btxemchitiet_sapchieu);
            xemchitiet.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {

            int position=getAdapterPosition();
            Intent intent = new Intent(context,ChiTietPhimSapChieu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("tenphim",phimSapChieuList.get(position).getTenphim());
            intent.putExtra("noidung",phimSapChieuList.get(position).getNoidungphim());
            context.startActivity(intent);
        }
    }
}
