package com.example.quanlyrapchieuphimmmm;

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

    private List<Phim> phimList;

    public PhimAdapter(List<Phim> phimList, Context context) {
        this.phimList = phimList;
        this.context = context;
    }

    private Context context;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_phim,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phim listphim =phimList.get(position);
        holder.tvid.setText(listphim.getId());
        holder.tvtenphim.setText(listphim.getTenphim());



    }

    @Override
    public int getItemCount() {
        return phimList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvid;
        public TextView tvtenphim;
        public Button btchitiet;
        public Button btdatve;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            tvid =(TextView)itemView.findViewById(R.id.tvIdphim);
            tvtenphim=(TextView) itemView.findViewById(R.id.tvTenphim);
            btchitiet=(Button) itemView.findViewById(R.id.btchitietphim);
            btdatve=(Button) itemView.findViewById(R.id.btdatve);
            btchitiet.setOnClickListener(this);
            btdatve.setOnClickListener(this::onClick2);


        }

        private void onClick2(View view) {
            int position = getAdapterPosition();
            Toast.makeText(context,"ABC"+position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,Datve.class);
            intent.putExtra("tenphim",phimList.get(position).getTenphim());
            context.startActivity(intent);

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(context,"ABC"+position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,ChiTietPhim.class);
            intent.putExtra("id",phimList.get(position).getId());
            intent.putExtra("tenphim",phimList.get(position).getTenphim());
            context.startActivity(intent);
        }
    }
}
