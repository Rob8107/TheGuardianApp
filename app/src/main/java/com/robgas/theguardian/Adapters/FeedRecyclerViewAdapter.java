package com.robgas.theguardian.Adapters;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robgas.theguardian.Models.FeedItem;
import com.robgas.theguardian.R;
import com.robgas.theguardian.Views.NetworkImageView;


public class FeedRecyclerViewAdapter extends PagedListAdapter<FeedItem, FeedRecyclerViewAdapter.FeedViewHolder> {
    private OnItemClickListener mFeedListItemClickListener = null;
    private Style style = Style.STAGGERED;

    public FeedRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<FeedItem> diffCallback) {
        super(diffCallback);
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new FeedViewHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate((style == Style.LINEAR) ? R.layout.list_item_feed_linear : R.layout.list_item_feed_grid,
                        viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedItem feedItem = getItem(position);
        if (feedItem != null) {
            holder.title.setText(feedItem.getTitle());
            holder.category.setText(feedItem.getCategory());
            holder.image.loadImage(feedItem.getImage());
        }
    }

    public void setFeedListItemClickListener(OnItemClickListener feedListItemClickListener) {
        mFeedListItemClickListener = feedListItemClickListener;
    }

    public enum Style {
        LINEAR, STAGGERED
    }

    public interface OnItemClickListener {
        void onFeedListItemClick(FeedItem item);
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView category;
        public TextView title;
        public NetworkImageView image;

        public FeedViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION)
                return;

            FeedItem item = getItem(getAdapterPosition());
            if (mFeedListItemClickListener != null) {
                mFeedListItemClickListener.onFeedListItemClick(item);
            }
        }
    }
}