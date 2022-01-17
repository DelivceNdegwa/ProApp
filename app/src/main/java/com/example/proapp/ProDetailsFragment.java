package com.example.proapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.proapp.networking.pojos.ProfessionalResponse;
import com.example.proapp.networking.services.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProDetailsFragment extends Fragment {
    int id = 0;

    public ProDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pro_details, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        getDetails();
    }

    private void getDetails() {
        Call<ProfessionalResponse> call = ServiceGenerator
                .getInstance()
                .getApiConnector()
                .getProfessionalData(id);

        call.enqueue(new Callback<ProfessionalResponse>() {
            @Override
            public void onResponse(Call<ProfessionalResponse> call, Response<ProfessionalResponse> response) {
                if(response.code() == 200 && response.body() != null){
                    Toast.makeText(getActivity(), "Retrieved details for "+response.body().getFirstName(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("TEST::", "onResponse: "+response.code()+" "+response.message());
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfessionalResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Check your connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}