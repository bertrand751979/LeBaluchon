package com.example.lebaluchon.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.lebaluchon.LeBaluchonFragmentTogoNewsApi;
import com.example.lebaluchon.modelsPublished.Article;
import com.example.lebaluchon.modelsPublished.RootTogoNews;
import com.example.lebaluchon.repository.RepositoryLeBaluchon;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TogoNewsFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Article>> myListTogo = new MutableLiveData();
    public LiveData<List<Article>> newsDataList = myListTogo;
    private MutableLiveData<Boolean> isError = new MutableLiveData<>();
    public LiveData<Boolean> liveDataIsError = isError;

    public void callService(){
        LeBaluchonFragmentTogoNewsApi.LeBaluchonFragmentTogoNewsService service = LeBaluchonFragmentTogoNewsApi.getInstance().getClient().create(LeBaluchonFragmentTogoNewsApi.LeBaluchonFragmentTogoNewsService.class);
        Call<RootTogoNews> call= service.getRoots("togo","2022-06-20","2022-06-25","popularity","cf5883a8ef6c4b25b2dde26488d5f680");
        call.enqueue(new Callback<RootTogoNews>() {
            @Override
            public void onResponse(Call<RootTogoNews> call, Response<RootTogoNews> response) {
                if(response.code()==200){
                    processResponse(response);
                }else{
                    sendError();
                }
            }
            @Override
            public void onFailure(Call<RootTogoNews> call, Throwable t) {
            }
        });
    }
    private void processResponse(Response<RootTogoNews> response) {
        if(response.body().getArticles().size()>0){
            RepositoryLeBaluchon.getInstance().newsList = (ArrayList<Article>) response.body().getArticles();
            myListTogo.postValue(response.body().getArticles());
        }
    }
    public void sendError(){
        isError.postValue(true);
    }

}
