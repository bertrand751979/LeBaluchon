package com.example.lebaluchon.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.lebaluchon.R;
import com.example.lebaluchon.fragments.ConvertFragment;
import com.example.lebaluchon.fragments.ForecastFragment;
import com.example.lebaluchon.fragments.TogoNewsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    public static String NEWS_EXTRA = "newExtra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConvertFragment()).commit();

    }


    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setSelectedItemId(R.id.nav_currency);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_currency:k:
                selectedFragment = new ConvertFragment();
                    break;
            }
            switch (item.getItemId()) {
                case R.id.nav_news:
                    selectedFragment = new TogoNewsFragment();
                    break;
            }
            switch (item.getItemId()) {
                case R.id.nav_forecast:
                    selectedFragment = new ForecastFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };



}