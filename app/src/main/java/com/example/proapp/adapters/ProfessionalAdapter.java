package com.example.proapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proapp.ProDetailsActivity;
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
    }

    @Override
    public int getItemCount() {
        return professionals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtOccupation;
        Button btnContact;
        ImageView imgPro;
        long id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_pro_name);
            txtOccupation = itemView.findViewById(R.id.txt_pro_occupation);
            btnContact = itemView.findViewById(R.id.btn_pro_contact);
            imgPro = itemView.findViewById(R.id.img_pro_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    String name = professionals.get(position).getName();
                    String occupation = professionals.get(position).getOccupation();

                    Intent intent = new Intent(context, ProDetailsActivity.class);
                    intent.putExtra("NAME", name);
//                    intent.putExtra("OCCUPATION", occupation);
                    context.startActivity(intent);
                }
            });

            btnContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:0798116374"));
                    context.startActivity(intent);
                }
            });
        }


    }
}
