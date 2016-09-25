package com.mobile.tneu.tneumobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewsViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_news_viewer);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        NewsRecyclerViewAdapter mNewsRecyclerViewAdapter = new NewsRecyclerViewAdapter();
//        recyclerView.setAdapter(mNewsRecyclerViewAdapter);
    }

}