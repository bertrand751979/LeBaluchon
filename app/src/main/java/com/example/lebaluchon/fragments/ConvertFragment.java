package com.example.lebaluchon.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lebaluchon.LeBaluchonRetrofitApi;
import com.example.lebaluchon.MyAlertDialogFragment;
import com.example.lebaluchon.R;
import com.example.lebaluchon.modelsConverter.Root;
import com.example.lebaluchon.repository.RepositoryLeBaluchon;
import com.example.lebaluchon.viewModels.ConvertFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConvertFragment extends Fragment {
    private EditText editAmountToConvert;
    private EditText editSymbolFrom;
    private EditText editSymbolTo;
    private Button btnToConvert;
    private TextView viewRates;
    private TextView viewResult;
    private ConvertFragmentViewModel convertFragmentViewModel;
    private Double rest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        convertFragmentViewModel = new ViewModelProvider(this).get(ConvertFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_convert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rest= RepositoryLeBaluchon.getInstance().getNewResult();
        editAmountToConvert = view.findViewById(R.id.edit_amount);
        editSymbolFrom = view.findViewById(R.id.edit_from);
        editSymbolTo = view.findViewById(R.id.edit_to);
        btnToConvert = view.findViewById(R.id.btn_convert);
        viewResult = view.findViewById(R.id.edit_result);
        viewRates = view.findViewById(R.id.edit_rates);
        setViewItem();
    }

    private void setViewItem(){
        btnToConvert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkError();
                ifEmptyCase();
                convertFragmentViewModel.callService(editSymbolTo.getText().toString(),editSymbolFrom.getText().toString(),editAmountToConvert.getText().toString());
                convertFragmentViewModel.rootLiveData.observe(getViewLifecycleOwner(), new Observer<Root>() {
                    @Override
                    public void onChanged(Root root) {
                        viewRates.setText(String.valueOf(root.getInfo().getRate()));
                        viewResult.setText(String.valueOf(root.getResult()));
                    }
                });

            }
        });
    }

        private void ifEmptyCase(){
            if(editAmountToConvert.getText().toString().equals("")){
                Toast.makeText(ConvertFragment.this.getContext(), "Veuillez saisir un montant", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editSymbolFrom.getText().toString().equals("")){
                Toast.makeText(ConvertFragment.this.getContext(), "Veuillez saisir devise de départ", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editSymbolTo.getText().toString().equals("")){
                Toast.makeText(ConvertFragment.this.getContext(), "Veuillez saisir devise de destination", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    public void checkError(){
        convertFragmentViewModel.liveDataIsError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
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
