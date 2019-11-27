package com.example.strainforpain.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.strainforpain.Utills.GeneralUtills;

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
            GeneralUtills.putStringValueInEditor(context , "id" , String.valueOf(datum.getId()));

            Intent intent = new Intent(context , HomeActivity.class);

            Bundle bundle = new Bundle();
        //    bundle.putInt("idess" , InString.valueOf(datum.getId()));

            bundle.putString("name" , datum.getDiseaseName());
            Log.d("name" , datum.getDiseaseName());
            intent.putExtras(bundle);
            context.startActivity(intent);

        }
    }
}
