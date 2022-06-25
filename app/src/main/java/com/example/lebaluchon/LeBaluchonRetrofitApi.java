package com.example.lebaluchon;

import com.example.lebaluchon.modelsConverter.Root;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public class LeBaluchonRetrofitApi {
    public interface LeBaluchonRetrofitService{
        @GET("convert")
        Call<Root> getRoots(@Query("apikey") String apiKey,@Query("to")String myto,@Query("from")String from,@Query("amount")Integer amount);
    }

    private final static String BASE_URL ="https://api.apilayer.com/exchangerates_data/";
    private static LeBaluchonRetrofitApi INSTANCE = null;
    private LeBaluchonRetrofitApi(){}
    public static LeBaluchonRetrofitApi getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LeBaluchonRetrofitApi();
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
