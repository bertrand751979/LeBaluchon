package com.example.lebaluchon.fragments;

import static com.example.lebaluchon.activities.MainActivity.NEWS_EXTRA;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lebaluchon.OnUrlClicked;
import com.example.lebaluchon.OnlinearClickedAction;
import com.example.lebaluchon.R;
import com.example.lebaluchon.adapters.NewsAdapter;
import com.example.lebaluchon.modelsPublished.Article;
import com.example.lebaluchon.modelsPublished.RootTogoNews;
import com.example.lebaluchon.repository.RepositoryLeBaluchon;
import com.example.lebaluchon.viewModels.TogoNewsFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class TogoNewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private TogoNewsFragmentViewModel togoNewsFragmentViewModel;
    private ArrayList<Article> myList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        togoNewsFragmentViewModel = new ViewModelProvider(this).get(TogoNewsFragmentViewModel.class);
        myList= RepositoryLeBaluchon.getInstance().newsList;
        togoNewsFragmentViewModel.callService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_togo_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_news);
        setViewItem();
        setViewItemIsError();
    }

    public void setViewItem(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        OnUrlClicked onUrlClicked = new OnUrlClicked() {
            @Override
            public void goToUrl(Article article) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(article.getUrl()));
                startActivity(intent);
                Toast.makeText(TogoNewsFragment.this.getContext(), "Allez vers", Toast.LENGTH_SHORT).show();
            }
        };
        newsAdapter = new NewsAdapter(onUrlClicked);
        recyclerView.setAdapter(newsAdapter);
        togoNewsFragmentViewModel.newsDataList.observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                newsAdapter.setListAdapterTogoNews(new ArrayList<>(articles));
            }
        });
    }

    public void setViewItemIsError(){
        togoNewsFragmentViewModel.liveDataIsError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
    }
}
