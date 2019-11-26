package com.example.strainforpain.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.strainforpain.Adapters.Model.diseaseModels.Datum;
import com.example.strainforpain.R;


import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    @NonNull

      private List<Datum> homeModelList;
//    private Context context ;
//
//    public HomeAdapter(@NonNull List<Datum1> homeModelList, Context context) {
//        this.homeModelList = homeModelList;
//        this.context = context;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

      //  Datum1 homeModel = homeModelList.get(position);

//        holder.title.setText(homeModel.getTitle());
//        holder.name1.setText(homeModel.getCBD());
//        holder.name2.setText(homeModel.getCBN());
//        holder.name3.setText(homeModel.getTHCV());
//        holder.name4.setText(homeModel.getTHC());
//
//        Glide.with(context).load(homeModel.getImage()).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return homeModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title , name1 , name2 , name3 , name4;
        ImageView imageView ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            name1 = itemView.findViewById(R.id.CDB);
            name2 = itemView.findViewById(R.id.CBN);
            name3 = itemView.findViewById(R.id.THCV);
            name4 = itemView.findViewById(R.id.THC);
            imageView = itemView.findViewById(R.id.image);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
