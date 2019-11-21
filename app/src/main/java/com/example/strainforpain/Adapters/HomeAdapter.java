package com.example.strainforpain.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strainforpain.Adapters.Model.HomeModel;
import com.example.strainforpain.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    @NonNull

    private List<HomeModel> homeModelList;
    private Context context ;

    public HomeAdapter(@NonNull List<HomeModel> homeModelList, Context context) {
        this.homeModelList = homeModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HomeModel homeModel = homeModelList.get(position);
        holder.name.setText(homeModel.getName());
         }


    @Override
    public int getItemCount() {
        return homeModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name , name1 , name2 , name3 ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
