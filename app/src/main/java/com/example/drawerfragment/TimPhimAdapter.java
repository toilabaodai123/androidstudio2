package com.example.drawerfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimPhimAdapter extends RecyclerView.Adapter<TimPhimAdapter.ViewHolder> {


    public TimPhimAdapter(List<Phim_TimPhim> timPhimList, Context context) {
        this.timPhimList = timPhimList;
        this.context = context;
    }

    private List<Phim_TimPhim> timPhimList;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_timphim,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Phim_TimPhim phim = timPhimList.get(position);
       holder.tenphim.setText(phim.getTenphim());
       holder.loaiphim.setText(phim.getLoaiphim());
    }

    @Override
    public int getItemCount() {
        return timPhimList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tenphim,loaiphim;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tenphim=itemView.findViewById(R.id.tv_tenphim_timphim);
            loaiphim=itemView.findViewById(R.id.tv_loaiphim_timphim);
        }
    }
}
