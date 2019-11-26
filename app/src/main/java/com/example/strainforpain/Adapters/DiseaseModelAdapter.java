package com.example.strainforpain.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strainforpain.Adapters.Model.diseaseModels.Datum;
import com.example.strainforpain.HomeActivity;
import com.example.strainforpain.R;

import java.util.List;

public class DiseaseModelAdapter extends RecyclerView.Adapter<DiseaseModelAdapter.ViewHolder> {
    @NonNull

    private List<Datum> diseaseModels;
    private Context context ;

    public DiseaseModelAdapter(@NonNull List<Datum> diseaseModels, Context context) {
        this.diseaseModels = diseaseModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disease_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Datum diseaseModel = diseaseModels.get(position);
        holder.diseaseName.setText(diseaseModel.getDiseaseName());

    }

    @Override
    public int getItemCount() {
        return diseaseModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView diseaseName ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            diseaseName = itemView.findViewById(R.id.desiceName);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Datum datum = diseaseModels.get(getAdapterPosition());

            Intent intent = new Intent(context , HomeActivity.class);
//
//            intent.putExtra("ids" , datum.getId());
//            Toast.makeText(context, "id"+ datum.getId(), Toast.LENGTH_SHORT).show();
//            intent.putExtra("diseaseName" , datum.getDiseaseName());
//            context.startActivity(intent);

            Bundle bundle = new Bundle();
            bundle.putString("idess" , String.valueOf(datum.getId()));
            bundle.putString("name" , datum.getDiseaseName());

            intent.putExtras(bundle);
            context.startActivity(intent);

        }
    }
}
