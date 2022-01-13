package com.example.proapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proapp.databinding.ActivityExploreBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExploreActivity extends AppCompatActivity {

    private ActivityExploreBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityExploreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_explore);

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.accountFragment
                ).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}
