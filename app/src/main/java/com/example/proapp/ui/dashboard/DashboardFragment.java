package com.example.proapp.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proapp.R;
import com.example.proapp.adapters.ProfessionAdapter;
import com.example.proapp.adapters.ProfessionalAdapter;
import com.example.proapp.databinding.FragmentDashboardBinding;
import com.example.proapp.models.Profession;
import com.example.proapp.models.Professional;
import com.example.proapp.networking.pojos.ProfessionResponse;
import com.example.proapp.networking.pojos.TestResponse;
import com.example.proapp.networking.services.ServiceGenerator;
import com.example.proapp.ui.home.HomeViewModel;
import com.example.proapp.utils.ObjectBox;
import com.example.proapp.utils.PreferenceStorage;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentDashboardBinding binding;


    List<Professional> professionals = new ArrayList<>();
    List<Profession> professions = new ArrayList<>();
    List<TestResponse> responses = new ArrayList<>();

    ProfessionalAdapter professionalAdapter;
    ProfessionAdapter professionAdapter;

    TextView txtHello;
    PreferenceStorage preferenceStorage;

    Box<Profession> professionBox = ObjectBox.get().boxFor(Profession.class);

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferenceStorage = new PreferenceStorage(getActivity());

        txtHello = root.findViewById(R.id.txt_hello);


        Button btnAllProfessions = root.findViewById(R.id.btn_all_proffessions);
        RecyclerView hiresRecyclerView = root.findViewById(R.id.hires_recyclerview);

        RecyclerView professionsRecyclerView = root.findViewById(R.id.professions_recyclerview);
        professionsRecyclerView.setNestedScrollingEnabled(true);
        professionsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        professionAdapter = new ProfessionAdapter(getActivity(), professions);
        professionsRecyclerView.setAdapter(professionAdapter);

        hiresRecyclerView.setNestedScrollingEnabled(true);
        hiresRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        professionalAdapter = new ProfessionalAdapter(getActivity(), professionals);
        hiresRecyclerView.setAdapter(professionalAdapter);

        btnAllProfessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProffessionDialog();
            }
        });

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
//        homeViewModel.getProfessions().observe(requireActivity(), professions_list -> {
//            professions.clear();
//            professions.addAll(professions_list);
//            professionAdapter.notifyDataSetChanged();
//
//
//        });
//        getCloudComments();
        getProfessions();
    }

    private void getCloudComments() {

        // This declares and initializes a new call  to get the comments
        Call<List<TestResponse>> call = ServiceGenerator.getInstance().getApiConnector().getComments();

        //This executes the call asynchronously using enque()
        call.enqueue(new Callback<List<TestResponse>>() {
            @Override
            public void onResponse(Call<List<TestResponse>> call, Response<List<TestResponse>> response) {

                if(response.code() == 200 && response.body() != null){
                    Toast.makeText(requireActivity(), "Received "+response.body().size()+" items", Toast.LENGTH_SHORT).show();
//                    responses.clear();
//                    responses.addAll(response.body());
                    Log.d("TEST::", "onResponse: "+response.body());


                }
                else{
                    Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<TestResponse>> call, Throwable t) {
                Toast.makeText(requireActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.d("TEST::", "onFailure: "+t.getMessage());
            }
        });

    }

    private void getProfessions(){
        Call<List<ProfessionResponse>> call = ServiceGenerator.getInstance().getApiConnector().getProfessions();

        call.enqueue(new Callback<List<ProfessionResponse>>() {
            @Override
            public void onResponse(Call<List<ProfessionResponse>> call, Response<List<ProfessionResponse>> response) {

                if(response.code() == 200 && response.body() != null){
                    professions.clear();
                    for(int i = 0; i < response.body().size(); i++){
                        ProfessionResponse proResponse = response.body().get(i);
                        professions.add(new Profession(proResponse.getName(), proResponse.getIcon(), proResponse.getCreatedAt()));
                    }
                    professionAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<ProfessionResponse>> call, Throwable t) {
                Toast.makeText(requireActivity(), "Check your connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showAddProffessionDialog(){
        final BottomSheetDialog addProfessionDialog = new BottomSheetDialog(getActivity());
        addProfessionDialog.setContentView(R.layout.new_profession_layout);

        TextInputEditText addProfession = addProfessionDialog.findViewById(R.id.input_profession_name);
        Button btnAddProffession = addProfessionDialog.findViewById(R.id.add_proffession);

        btnAddProffession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profession = addProfession.getText().toString().trim();
                if(!profession.isEmpty()){
                    saveProfession(profession);
                    professionAdapter.notifyDataSetChanged();
                    addProfessionDialog.dismiss();
                }
                else{
                    addProfession.setError("Add a profession");
                }
            }
        });
        addProfessionDialog.show();
    }

    public void saveProfession(String name){
        Profession profession = new Profession(name);
        long id = professionBox.put(profession);
        profession.setId(id);
        professions.add(profession);

    }
}

