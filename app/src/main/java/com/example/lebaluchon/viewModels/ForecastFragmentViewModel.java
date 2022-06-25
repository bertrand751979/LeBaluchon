package com.example.lebaluchon.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lebaluchon.LeBaluchonRetrofitForecastApi;
import com.example.lebaluchon.modelsForecast.RootForecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastFragmentViewModel extends ViewModel {
    private MutableLiveData<RootForecast>myListMutable = new MutableLiveData<>();
    public LiveData<RootForecast>listLiveData = myListMutable;

    private MutableLiveData<RootForecast>myListMutableNewYork = new MutableLiveData<>();
    public LiveData<RootForecast>listLiveDataNewYork = myListMutableNewYork;

    private MutableLiveData<Boolean> isError = new MutableLiveData<>();
    public LiveData<Boolean> liveDataIsError = isError;

    public void callService(){
        LeBaluchonRetrofitForecastApi.LeBaluchonRetrofitForecastService service = LeBaluchonRetrofitForecastApi.getInstance().getClient().create(LeBaluchonRetrofitForecastApi.LeBaluchonRetrofitForecastService.class);
        Call<RootForecast> call= service.getRoots("London","99660dc561c36a9f4a0da1b943fa91ca");
        call.enqueue(new Callback<RootForecast>() {
            @Override
            public void onResponse(Call<RootForecast> call, Response<RootForecast> response) {
                processResponse(response);
            }

            @Override
            public void onFailure(Call<RootForecast> call, Throwable t) {

                //showAlertDialog();
            }
        });
    }
    private void processResponse(Response<RootForecast> response) {
        if(response.body()!=null){
            myListMutable.postValue(response.body());
        }
    }

    public void callServiceNewYork(){
        LeBaluchonRetrofitForecastApi.LeBaluchonRetrofitForecastService service = LeBaluchonRetrofitForecastApi.getInstance().getClient().create(LeBaluchonRetrofitForecastApi.LeBaluchonRetrofitForecastService.class);
        Call<RootForecast> call= service.getRoots("New York","99660dc561c36a9f4a0da1b943fa91ca");
        call.enqueue(new Callback<RootForecast>() {
            @Override
            public void onResponse(Call<RootForecast> call, Response<RootForecast> response) {
                if(response.code()==200){
                    processResponseNewYork(response);
                }else{
                    sendError();
                }
            }

            @Override
            public void onFailure(Call<RootForecast> call, Throwable t) {
                sendError();
            }
        });
    }
    private void processResponseNewYork(Response<RootForecast> response) {
        if(response.body()!=null){
            myListMutableNewYork.postValue(response.body());
        }
    }

    public void sendError(){
        isError.postValue(true);
    }


}
