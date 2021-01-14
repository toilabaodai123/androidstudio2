package com.example.drawerfragment;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class thongtinlichsuAdapter extends RecyclerView.Adapter<thongtinlichsuAdapter.ViewHolder> {
    private List<thongtinlichsu> thongtinlichsuList;

    public thongtinlichsuAdapter(List<thongtinlichsu> thongtinlichsuList, Context context) {
        this.thongtinlichsuList = thongtinlichsuList;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_lichsu,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        thongtinlichsu tt = thongtinlichsuList.get(position);
        holder.tenphim.setText(tt.getTenphim());
        holder.thoigiandat.setText(tt.getThoigiandat());
        holder.id.setText(tt.getId());

    }

    @Override
    public int getItemCount() {
        return thongtinlichsuList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tenphim , thoigiandat ,id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tenphim=itemView.findViewById(R.id.tv_tenphim_lichsu);
            thoigiandat=itemView.findViewById(R.id.tv_thoigiandat_lichsu);
            id = itemView.findViewById(R.id.tv_id_lichsu);

        }
    }

}
