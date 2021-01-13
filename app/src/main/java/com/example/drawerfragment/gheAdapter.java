package com.example.drawerfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class gheAdapter extends RecyclerView.Adapter<gheAdapter.ViewHolder> {
    public String x;
    public TextView aa;
    public String ab;

    public gheAdapter(List<ghe> gheList, Context context,gheInterface gheInterface) {
        this.gheList = gheList;
        this.context = context;
        this.gheInterface=gheInterface;
    }

    public List<ghe> getGheList() {
        return gheList;
    }

    public Context getContext() {
        return context;
    }

    private List<ghe> gheList;
    private Context context;
    public ArrayList<String> list;
    private gheInterface gheInterface;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_ghe, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        list=new ArrayList<>();

        ghe Ghe = gheList.get(position);
        holder.idghe.setText(Ghe.getId());
        holder.tenghe.setText(Ghe.getTenghe());

    }

    @Override
    public int getItemCount() {
        return gheList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView idghe, a;
        public TextView tenghe;
        public CheckBox cbghe;
        ArrayList<String> list = new ArrayList<>();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idghe = itemView.findViewById(R.id.tvidghe);
            tenghe = itemView.findViewById(R.id.tvtenghe);
            cbghe = itemView.findViewById(R.id.cb_ghe);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gheInterface.onItemClick(getAdapterPosition());

                }
            });
            itemView.setOnLongClickListener(v -> {
                gheInterface.onLongItemClick(getAdapterPosition());
                return true;
            });




        }


    }
}
