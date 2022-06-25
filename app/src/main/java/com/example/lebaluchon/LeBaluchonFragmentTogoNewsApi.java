package com.example.lebaluchon;

import com.example.lebaluchon.modelsPublished.RootTogoNews;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class LeBaluchonFragmentTogoNewsApi {
    public  interface LeBaluchonFragmentTogoNewsService{
        @GET("everything")
        Call<RootTogoNews>getRoots(@Query("q") String q, @Query("from")String from, @Query("to")String to, @Query("sortBy")String sort, @Query("apiKey")String apiKey);
        //https://newsapi.org/v2/everything?q=togo&from=2022-06-20&to=2022-06-20&sortBy=popularity&apiKey=cf5883a8ef6c4b25b2dde26488d5f680
    }
    private final static String BASE_URL ="https://newsapi.org/v2/";
    private static LeBaluchonFragmentTogoNewsApi INSTANCE = null;
    private LeBaluchonFragmentTogoNewsApi(){}
    public static LeBaluchonFragmentTogoNewsApi getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LeBaluchonFragmentTogoNewsApi();
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
