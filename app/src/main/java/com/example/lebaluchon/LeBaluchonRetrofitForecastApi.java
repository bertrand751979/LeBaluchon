package com.example.lebaluchon;

import com.example.lebaluchon.modelsConverter.Root;
import com.example.lebaluchon.modelsForecast.RootForecast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class LeBaluchonRetrofitForecastApi {
    public interface LeBaluchonRetrofitForecastService{
        @GET("weather")
        Call<RootForecast> getRoots(@Query("q")String q,@Query("appid") String appid);
    }

    private final static String BASE_URL ="https://api.openweathermap.org/data/2.5/";
    private static LeBaluchonRetrofitForecastApi INSTANCE = null;
    private LeBaluchonRetrofitForecastApi(){}
    public static LeBaluchonRetrofitForecastApi getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LeBaluchonRetrofitForecastApi();
        }
        return INSTANCE;
    }

    public Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
