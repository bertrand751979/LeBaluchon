package com.example.lebaluchon.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lebaluchon.OnUrlClicked;
import com.example.lebaluchon.OnlinearClickedAction;
import com.example.lebaluchon.R;
import com.example.lebaluchon.modelsPublished.Article;
import com.example.lebaluchon.modelsPublished.RootTogoNews;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    private TextView vhSource;
    private TextView vhAuthor;
    private TextView vhTitle;
    private TextView vhDescription;
    private TextView vhUrl;
    private ImageView vhUrlToImage;
    private TextView vhPublishedAt;
    private TextView vhContent;


    public NewsViewHolder(@NonNull View view) {
        super(view);
        vhAuthor = view.findViewById(R.id.raw_author);
        vhTitle = view.findViewById(R.id.raw_title);
        vhDescription = view.findViewById(R.id.raw_description);
        vhUrl = view.findViewById(R.id.raw_url);
        vhUrlToImage = view.findViewById(R.id.raw_photo);
    }




    public TextView getVhSource() {
        return vhSource;
    }

    public void setVhSource(TextView vhSource) {
        this.vhSource = vhSource;
    }

    public TextView getVhAuthor() {
        return vhAuthor;
    }

    public void setVhAuthor(TextView vhAuthor) {
        this.vhAuthor = vhAuthor;
    }

    public TextView getVhTitle() {
        return vhTitle;
    }

    public void setVhTitle(TextView vhTitle) {
        this.vhTitle = vhTitle;
    }

    public TextView getVhDescription() {
        return vhDescription;
    }

    public void setVhDescription(TextView vhDescription) {
        this.vhDescription = vhDescription;
    }

    public TextView getVhUrl() {
        return vhUrl;
    }

    public void setVhUrl(TextView vhUrl) {
        this.vhUrl = vhUrl;
    }

    public ImageView getVhUrlToImage() {
        return vhUrlToImage;
    }

    public void setVhUrlToImage(ImageView vhUrlToImage) {
        this.vhUrlToImage = vhUrlToImage;
    }

    public TextView getVhPublishedAt() {
        return vhPublishedAt;
    }

    public void setVhPublishedAt(TextView vhPublishedAt) {
        this.vhPublishedAt = vhPublishedAt;
    }

    public TextView getVhContent() {
        return vhContent;
    }

    public void setVhContent(TextView vhContent) {
        this.vhContent = vhContent;
    }


    public void bind (Article article, OnUrlClicked onUrlClicked){
        vhAuthor.setText(article.getAuthor());
        vhTitle.setText(article.getTitle());
        vhDescription.setText(article.getDescription());
        vhUrl.setText(article.getUrl());
        vhUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUrlClicked.goToUrl(article);
            }
        });

        Glide.with(vhUrlToImage.getContext())
                .load(article.getUrlToImage())
                .into(vhUrlToImage);
    }
}
