package com.robgas.theguardian.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robgas.theguardian.Models.FeedItem;
import com.robgas.theguardian.R;
import com.robgas.theguardian.Views.CircleNetworkImageView;

import java.util.ArrayList;
import java.util.List;


public class PinRecyclerViewAdapter extends RecyclerView.Adapter<PinRecyclerViewAdapter.PinViewHolder> {

    private List<FeedItem> mData = new ArrayList<>();
    private PinRecyclerViewAdapter.OnPinListItemClickListener mItemClickListener;


    @NonNull
    @Override
    public PinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_pin, parent, false));
    }

    @Override
    public void onBindViewHolder(PinViewHolder holder, int position) {
        holder.image.loadImage(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItemClickListener(PinRecyclerViewAdapter.OnPinListItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    private FeedItem getItem(int position) {
        return mData.get(position);
    }

    public void setData(List<FeedItem> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public interface OnPinListItemClickListener {
        void onPinListItemClick(PinViewHolder pinViewHolder, FeedItem item);
    }

    public class PinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CircleNetworkImageView image;

        public PinViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = itemView.findViewById(R.id.item_image);
            image.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            if (mItemClickListener != null) {
                mItemClickListener.onPinListItemClick(this, getItem(getAdapterPosition()));
            }

        }
    }

}