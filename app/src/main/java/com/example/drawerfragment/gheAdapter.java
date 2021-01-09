package com.example.drawerfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class gheAdapter extends RecyclerView.Adapter<gheAdapter.ViewHolder> {
    public gheAdapter(List<ghe> gheList, Context context) {
        this.gheList = gheList;
        this.context = context;
    }

    public List<ghe> getGheList() {
        return gheList;
    }

    public Context getContext() {
        return context;
    }

    private List<ghe> gheList;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_ghe,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ghe Ghe = gheList.get(position);
        holder.idghe.setText(Ghe.getId());
        holder.tenghe.setText(Ghe.getTenghe());
    }

    @Override
    public int getItemCount() {
        return gheList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView idghe;
        public TextView tenghe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            idghe=itemView.findViewById(R.id.tvidghe);
            tenghe=itemView.findViewById(R.id.tvtenghe);

        }
    }
}
