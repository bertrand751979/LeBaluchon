package com.example.lebaluchon.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lebaluchon.OnUrlClicked;
import com.example.lebaluchon.OnlinearClickedAction;
import com.example.lebaluchon.R;
import com.example.lebaluchon.modelsPublished.Article;
import com.example.lebaluchon.modelsPublished.RootTogoNews;
import com.example.lebaluchon.viewHolders.NewsViewHolder;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private ArrayList<Article>listAdapterTogoNews = new ArrayList<>();
    private OnUrlClicked onUrlClicked;

    public NewsAdapter(OnUrlClicked onUrlClicked) {
        this.onUrlClicked = onUrlClicked;
    }

    public void setListAdapterTogoNews(ArrayList<Article> listAdapterTogoNews) {
        this.listAdapterTogoNews = listAdapterTogoNews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(listAdapterTogoNews.get(position), onUrlClicked);
    }

    @Override
    public int getItemCount() {
        return listAdapterTogoNews.size();
    }
}
