package com.mobile.tneu.tneumobile.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobile.tneu.tneumobile.R;
import com.mobile.tneu.tneumobile.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by underhand on 24.09.2016.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private final int VIEW_ITEM = 1;
  private final int VIEW_PROGRESS = 0;
  private List<News> newses = null;
  private int minimumAmountOfItemsBeforeLoadMore = 2;
  private boolean loading;
  private OnLoadMoreListener onLoadMoreListener;

  public NewsRecyclerViewAdapter(RecyclerView recyclerView) {

    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
      final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
      recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);
          int totalItemCount = linearLayoutManager.getItemCount();
          int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
          if (!loading && totalItemCount <= (lastVisibleItem + minimumAmountOfItemsBeforeLoadMore)) {
            if (onLoadMoreListener != null) {
              addProgressBar();
              onLoadMoreListener.onLoadMore();
            }
            loading = true;
          }
        }
      });
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    RecyclerView.ViewHolder vh;
    if (viewType == VIEW_ITEM) {
      View v = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.news_card_view_item, parent, false);
      vh = new NewsViewHolder(v);
    } else {
      View v = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.progress_bar, parent, false);
      vh = new ProgressViewHolder(v);
    }
    return vh;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof NewsViewHolder) {
      bindNewsViewItems((NewsViewHolder) holder, position);
    } else {
      ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
    }
  }

  @Override
  public int getItemCount() {
    if (newses != null) {
      return newses.size();
    } else {
      return 0;
    }
  }

  @Override
  public int getItemViewType(int position) {
    return newses.get(position) != null ? VIEW_ITEM : VIEW_PROGRESS;
  }

  public void addAll(List<News> newList) {
    if (newses == null) {
      newses = newList;
    } else {
      removeProgressBar();
      newses.addAll(newList);
    }
    notifyDataSetChanged();
  }

  private void addProgressBar() {
    newses.add(null);
    notifyItemInserted(newses.size() - 1);
  }

  private void bingImage(NewsViewHolder holder, String imageUrl) {
    if (!imageUrl.isEmpty()) {
      Picasso.with(holder.newsImage.getContext())
          .load(imageUrl)
          .fit()
          .centerInside()
          .into(holder.newsImage);
      holder.newsImage.setVisibility(View.VISIBLE);
    } else {
      holder.newsImage.setVisibility(View.GONE);
    }
  }

  private void bindNewsViewItems(NewsViewHolder holder, int position) {
    bingImage(holder, newses.get(position).getImageLink());
    holder.title.setText(newses.get(position).getTitle());
    holder.data.setText(newses.get(position).getDate().substring(0, 16).replace("T", ", "));
    holder.topic.setText(newses.get(position).getTopic());
    holder.description.setText(newses.get(position).getDescription());
    holder.readMore.setText((Html.fromHtml("<a href=\"" + newses.get(position)
        .getReadMoreLink() + "\">Детальніше</a>")));
    holder.readMore.setMovementMethod(LinkMovementMethod.getInstance());
  }

  public void clear() {
    if (newses != null) {
      newses.clear();
    }
  }

  public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
    this.onLoadMoreListener = onLoadMoreListener;
  }

  private void removeProgressBar() {
    if (loading) {
      newses.remove(newses.size() - 1);
      loading = false;
    }
  }

  public interface OnLoadMoreListener {
    void onLoadMore();
  }

  private static class ProgressViewHolder extends RecyclerView.ViewHolder {
    private ProgressBar progressBar;

    ProgressViewHolder(View v) {
      super(v);
      progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
    }
  }

  private static class NewsViewHolder extends RecyclerView.ViewHolder {
    private ImageView newsImage;
    private TextView title;
    private TextView data;
    private TextView topic;
    private TextView description;
    private TextView readMore;

    public NewsViewHolder(View v) {
      super(v);
      title = (TextView) itemView.findViewById(R.id.title);
      data = (TextView) itemView.findViewById(R.id.data);
      topic = (TextView) itemView.findViewById(R.id.topic);
      newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
      description = (TextView) itemView.findViewById(R.id.description);
      readMore = (TextView) itemView.findViewById(R.id.readMore);
    }
  }
}

