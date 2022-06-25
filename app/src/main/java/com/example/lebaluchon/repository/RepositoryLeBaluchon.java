package com.example.lebaluchon.repository;

import androidx.fragment.app.FragmentManager;

import com.example.lebaluchon.MyAlertDialogFragment;
import com.example.lebaluchon.modelsForecast.Main;
import com.example.lebaluchon.modelsForecast.RootForecast;
import com.example.lebaluchon.modelsForecast.Weather;
import com.example.lebaluchon.modelsPublished.Article;

import java.util.ArrayList;

public class RepositoryLeBaluchon {
    public Double newResult;
    public ArrayList<Article> newsList = new ArrayList<>();

    private RepositoryLeBaluchon(){}
    public static RepositoryLeBaluchon INSTANCE = null;
    public static RepositoryLeBaluchon getInstance(){
        if(INSTANCE ==null){
            INSTANCE = new RepositoryLeBaluchon();
        }
        return INSTANCE;
    }

    public Double getNewResult() {
        return newResult;
    }

    public void setNewResult(Double newResult) {
        this.newResult = newResult;
    }

    public ArrayList<Article> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<Article> newsList) {
        this.newsList = newsList;
    }
}
