package com.example.a356finalproject;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a356finalproject.dummy.Review;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Review}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyReviewRecyclerViewAdapter extends RecyclerView.Adapter<MyReviewRecyclerViewAdapter.ViewHolder> {

    private final List<Review.DummyReview> mValues;

    public MyReviewRecyclerViewAdapter(List<Review.DummyReview> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mReviewView.setText(mValues.get(position).review_text);
        holder.mNameView.setText(mValues.get(position).name);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mReviewView;
        public final TextView mNameView;
        public Review.DummyReview mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mReviewView = (TextView) view.findViewById(R.id.review_text);
            mNameView = (TextView) view.findViewById(R.id.name);
        }
    }
}