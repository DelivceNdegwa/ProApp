package com.example.proapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proapp.adapters.ProfessionAdapter;
import com.example.proapp.adapters.ProfessionalAdapter;
import com.example.proapp.models.Profession;
import com.example.proapp.models.Professional;
import com.example.proapp.utils.ObjectBox;
import com.example.proapp.utils.PreferenceStorage;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class HomeActivity extends AppCompatActivity {

    List<Professional> professionals = new ArrayList<>();
    List<Profession> professions = new ArrayList<>();

    ProfessionalAdapter professionalAdapter;
    ProfessionAdapter professionAdapter;

    TextView txtHello;
    PreferenceStorage preferenceStorage;

    Box<Profession> professionBox = ObjectBox.get().boxFor(Profession.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = preferenceStorage.getUserName();
        txtHello.setText("Hello "+name);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_selected = item.getItemId();

        switch (item_selected){
            case R.id.action_settings:
                Intent intent = new Intent(HomeActivity.this, ExploreActivity.class);
                startActivity(intent);
                return true;


            case R.id.action_logout:
                showAlert();
                return true;


            case R.id.action_add:
                Intent i = new Intent(HomeActivity.this, JobFormActivity.class);
                startActivity(i);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Don't leave me")
               .setMessage("Are you sure you want to go?")
               .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       preferenceStorage.setLoggedInStatus(false);
                       Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                       startActivity(intent);
                       finish();
                   }
               })
               .setNegativeButton("Stay", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();
                   }
               });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showAddProffessionDialog(){
        final BottomSheetDialog addProfessionDialog = new BottomSheetDialog(this);
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


