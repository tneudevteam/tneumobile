package com.mobile.tneu.tneumobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.tneu.tneumobile.R;
import com.mobile.tneu.tneumobile.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by underhand on 24.09.2016.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private ArrayList<News> mDataSet;

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView mNewsImage;
        private TextView mTitle;
        private TextView mData;
        private TextView mTopic;
        private TextView mDescription;
        private TextView mReadMore;

        public NewsViewHolder(View v) {
            super(v);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mData = (TextView) itemView.findViewById(R.id.data);
            mTopic = (TextView) itemView.findViewById(R.id.topic);
            mNewsImage = (ImageView) itemView.findViewById(R.id.newsImage);
            mDescription = (TextView) itemView.findViewById(R.id.description);
            mReadMore = (TextView) itemView.findViewById(R.id.readMore);
        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card_view_item, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        Picasso.with(holder.mNewsImage.getContext()).load(mDataSet.get(position)
                .getImageLink()).fit().centerInside().into(holder.mNewsImage);
        holder.mTitle.setText(mDataSet.get(position).getTitle());
        holder.mData.setText(mDataSet.get(position).getDate().substring(0, 16).replace("T", ", "));
        holder.mTopic.setText(mDataSet.get(position).getTopic());
        holder.mDescription.setText(mDataSet.get(position).getDescription());
        holder.mReadMore.setText((Html.fromHtml("<a href=\"" + mDataSet.get(position)
                .getReadMoreLink() + "\">Детальніше</a>")));
        holder.mReadMore.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

