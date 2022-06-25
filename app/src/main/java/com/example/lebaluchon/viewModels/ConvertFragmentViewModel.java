package com.example.lebaluchon.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.lebaluchon.LeBaluchonRetrofitApi;
import com.example.lebaluchon.modelsConverter.Root;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConvertFragmentViewModel extends ViewModel {
    private MutableLiveData<Root> dataRoot = new MutableLiveData<>();
    public LiveData<Root> rootLiveData = dataRoot;

    private MutableLiveData<Boolean> isError = new MutableLiveData<>();
    public LiveData<Boolean> liveDataIsError = isError;

    public void callService(String editTo, String editFrom, String editAmount){
        LeBaluchonRetrofitApi.LeBaluchonRetrofitService service = LeBaluchonRetrofitApi.getInstance().getClient().create(LeBaluchonRetrofitApi.LeBaluchonRetrofitService.class);
        Call<Root> call= service.getRoots("M7z2wgFQwZPezg9XPDUSSCVxqL3Z7zqU",editTo,editFrom,Integer.valueOf(editAmount));
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(response.code()==200){
                    processResponse(response);
                }
                else{sendError();}
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                    sendError();
            }
        });
    }

    private void processResponse(Response<Root> response) {
        if((response.body()!=null)&&(response.body().getInfo().getRate()>0)){
            //Double showRate = response.body().getInfo().getRate();
            Log.d("showRates", String.valueOf(response.body().getInfo().getRate()));
            dataRoot.postValue(response.body());
        }
    }

    public void sendError(){
        isError.postValue(true);
    }

}

