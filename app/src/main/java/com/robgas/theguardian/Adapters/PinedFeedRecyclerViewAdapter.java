package com.robgas.theguardian.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robgas.theguardian.Models.FeedItem;
import com.robgas.theguardian.R;
import com.robgas.theguardian.Views.NetworkImageView;

import java.util.List;


public class PinedFeedRecyclerViewAdapter extends RecyclerView.Adapter<PinedFeedRecyclerViewAdapter.ViewHolder> {

    private List<FeedItem> feedItems;
    private OnItemClickListener mFeedListItemClickListener = null;

    public PinedFeedRecyclerViewAdapter(@NonNull List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_feed_grid, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.itemView.setOnClickListener(v -> {
            if (position == RecyclerView.NO_POSITION)
                return;

            FeedItem item = feedItems.get(position);
            if (mFeedListItemClickListener != null) {
                mFeedListItemClickListener.onFeedListItemClick(item);
            }
        });
        FeedItem feedItem = feedItems.get(position);
        if (feedItem != null) {
            viewHolder.title.setText(feedItem.getTitle());
            viewHolder.category.setText(feedItem.getCategory());
            viewHolder.image.loadImage(feedItem.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public void setFeedListItemClickListener(OnItemClickListener feedListItemClickListener) {
        mFeedListItemClickListener = feedListItemClickListener;
    }


    public interface OnItemClickListener {
        void onFeedListItemClick(FeedItem item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public TextView title;
        public NetworkImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);

        }

    }
}
