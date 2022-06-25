package com.example.lebaluchon.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lebaluchon.MyAlertDialogFragment;
import com.example.lebaluchon.R;
import com.example.lebaluchon.modelsForecast.RootForecast;
import com.example.lebaluchon.viewModels.ForecastFragmentViewModel;

public class ForecastFragment extends Fragment {
    private ForecastFragmentViewModel forecastFragmentViewModel;
    private TextView tMin;
    private TextView tMax;
    private TextView pressure;
    private TextView humidity;
    private TextView city;
    private TextView tMinNewYork;
    private TextView tMaxNewYork;
    private TextView pressureNewYork;
    private TextView humidityNewYork;
    private TextView cityNewYork;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forecastFragmentViewModel = new ViewModelProvider(this).get(ForecastFragmentViewModel.class);
        forecastFragmentViewModel.callService();
        forecastFragmentViewModel.callServiceNewYork();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tMin = view.findViewById(R.id.tMin);
        tMax = view.findViewById(R.id.tMax);
        pressure = view.findViewById(R.id.pressure);
        humidity = view.findViewById(R.id.humidity);
        city = view.findViewById(R.id.city_name);
        tMinNewYork = view.findViewById(R.id.nyMin);;
        tMaxNewYork = view.findViewById(R.id.nyMax);;
        pressureNewYork = view.findViewById(R.id.nyPressure);;
        humidityNewYork = view.findViewById(R.id.nyHumidity);;
        cityNewYork = view.findViewById(R.id.nyCityName);;
        setViewItem();
        setViewItemNewYork();
        checkError();
    }

    private  void setViewItem() {
        forecastFragmentViewModel.listLiveData.observe(getViewLifecycleOwner(), new Observer<RootForecast>() {
            @Override
            public void onChanged(RootForecast rootForecast) {
                tMin.setText(String.valueOf(rootForecast.getMain().getTemp_min()));
                tMax.setText(String.valueOf(rootForecast.getMain().getTemp_max()));
                pressure.setText(String.valueOf(rootForecast.getMain().getPressure()));
                humidity.setText(String.valueOf(rootForecast.getMain().getHumidity()));
                city.setText(rootForecast.getName());
            }
        });
    }

    private void setViewItemNewYork(){
        forecastFragmentViewModel.listLiveDataNewYork.observe(getViewLifecycleOwner(), new Observer<RootForecast>() {
            @Override
            public void onChanged(RootForecast rootForecast) {
                tMinNewYork.setText(String.valueOf(rootForecast.getMain().getTemp_min()));
                tMaxNewYork.setText(String.valueOf(rootForecast.getMain().getTemp_max()));
                pressureNewYork.setText(String.valueOf(rootForecast.getMain().getPressure()));
                humidityNewYork.setText(String.valueOf(rootForecast.getMain().getHumidity()));
                cityNewYork.setText(rootForecast.getName());
            }
        });
    }

    public void checkError(){
        forecastFragmentViewModel.liveDataIsError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean==true){
                    showAlertDialog();

                }
            }
        });
    }

    public void showAlertDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        MyAlertDialogFragment alertDialog = MyAlertDialogFragment.newInstance("Some title");
        alertDialog.show(fm, "fragment_alert");
    }


}
