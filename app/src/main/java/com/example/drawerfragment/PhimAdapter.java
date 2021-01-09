package com.example.drawerfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.ViewHolder> {


    public PhimAdapter(List<Phim> phimList, Context context) {
        this.phimList = phimList;
        this.context = context;
    }

    private List<Phim> phimList;
    private Context context;


    @NonNull
    @Override
    public PhimAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_phim,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhimAdapter.ViewHolder holder, int position) {
        Phim listphim = phimList.get(position);
        holder.idphim.setText(listphim.getId());
        holder.tenphim.setText(listphim.getTenphim());
    }

    @Override
    public int getItemCount() {
        return phimList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView idphim;
        public TextView tenphim;
        public Button datve;
        public Button xemchitiet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idphim=(TextView)itemView.findViewById(R.id.tv_idphim);
            tenphim=(TextView)itemView.findViewById(R.id.tv_tenphim);
            datve=(Button)itemView.findViewById(R.id.btdatve);
            xemchitiet=(Button)itemView.findViewById(R.id.btxemchitiet);
            xemchitiet.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Toast.makeText(context,"ABC"+position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,Chitietphim.class);
            intent.putExtra("id",phimList.get(position).getId());
            intent.putExtra("tenphim",phimList.get(position).getTenphim());
            context.startActivity(intent);
        }
    }
}
