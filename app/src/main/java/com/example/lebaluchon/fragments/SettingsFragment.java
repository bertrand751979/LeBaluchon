package com.example.lebaluchon.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lebaluchon.R;

import java.util.Locale;

public class SettingsFragment extends Fragment {
    public static String storeLang;
    private Button btnset;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnset = view.findViewById(R.id.btn_set_language);
        btnset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setViewItem();
            }
        });

    }

    private void setViewItem(){
        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity (intent);

       /* String languageToLoad = "es"; // your language
        Configuration config = this.getContext().getResources().getConfiguration();
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        config.locale = locale;
        this.getContext().getResources().updateConfiguration(config,this.getContext() .getResources().getDisplayMetrics());
        Toast.makeText(SettingsFragment.this.getContext(), "Setting Ok", Toast.LENGTH_SHORT).show();
        refreshFragment();*/

    }

}
