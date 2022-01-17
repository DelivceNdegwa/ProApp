package com.example.proapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proapp.R;
import com.example.proapp.models.Professional;

import java.util.List;

public class ProfessionalAdapter extends RecyclerView.Adapter<ProfessionalAdapter.ViewHolder> {
    Context context;
    List<Professional> professionals;

    public ProfessionalAdapter(Context context, List<Professional> professionals) {
        this.context = context;
        this.professionals = professionals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // We specify our itemview here
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_professional, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Professional professional = professionals.get(position);
        holder.txtName.setText(professional.getName());
        holder.txtOccupation.setText(professional.getOccupation());
        Glide.with(context).load(professional.getImage()).placeholder(R.drawable.ambassador).into(holder.imgPro);
        holder.phoneNumber = professional.getPhone_number();
        holder.id = professional.getId();
    }

    @Override
    public int getItemCount() {
        return professionals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtOccupation;
        Button btnContact;
        ImageView imgPro;
        String phoneNumber;
        int id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_pro_name);
            txtOccupation = itemView.findViewById(R.id.txt_pro_occupation);
            btnContact = itemView.findViewById(R.id.btn_pro_contact);
            imgPro = itemView.findViewById(R.id.img_pro_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", id);
                    Navigation.findNavController(view).navigate(R.id.proDetailsFragment, bundle);
                }
            });

            btnContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+phoneNumber));
                    context.startActivity(intent);



                }
            });
        }


    }
}
