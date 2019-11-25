package com.example.strainforpain.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strainforpain.Adapters.Model.HomeModel;
import com.example.strainforpain.DiseaseActivity;
import com.example.strainforpain.R;
import com.example.strainforpain.models.DiseaseModel.DiseaseModel;

import java.util.List;

public class DiseaseModelAdapter extends RecyclerView.Adapter<DiseaseModelAdapter.ViewHolder> {
    @NonNull

    private List<DiseaseModel> diseaseModels;
    private Context context ;

    public DiseaseModelAdapter(@NonNull List<DiseaseModel> diseaseModels, Context context) {
        this.diseaseModels = diseaseModels;
        this.context = context;
    }

    public DiseaseModelAdapter(DiseaseActivity diseaseActivity, List<DiseaseModel> diseaseModels) {
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disease_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DiseaseModel diseaseModel = diseaseModels.get(position);

        holder.diseaseName.setText(diseaseModel.getName());
    }

    @Override
    public int getItemCount() {
        return diseaseModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView diseaseName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            diseaseName = itemView.findViewById(R.id.desiceName);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
