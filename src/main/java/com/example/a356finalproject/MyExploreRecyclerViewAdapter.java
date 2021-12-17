package com.example.a356finalproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a356finalproject.dummy.DummyContent;
import com.example.a356finalproject.dummy.DummyContent.DummyBusiness;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyBusiness}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyExploreRecyclerViewAdapter extends RecyclerView.Adapter<MyExploreRecyclerViewAdapter.ViewHolder> {

    private final List<DummyBusiness> mValues;

    public MyExploreRecyclerViewAdapter(List<DummyBusiness> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mTypeView.setText(mValues.get(position).type);
        holder.mImageView.setImageResource(holder.itemView.getContext().getResources()
                .getIdentifier(mValues.get(position).picture, "drawable", holder.itemView.getContext().getPackageName()));

        if (holder.mItem.in_library == true) {
            ImageView add_icon = (ImageView) holder.mView.findViewById(R.id.add_icon);
            add_icon.setImageResource(holder.mView.getResources().getIdentifier("check_icon",
                            "drawable", holder.mView.getContext().getPackageName()));
        }

        LinearLayout layout_button = (LinearLayout) holder.mView.findViewById(R.id.itemLayout);

        layout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, BusinessInfoFragment.newInstance(holder.mItem.id))
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mTypeView;
        public final ImageView mImageView;
        public DummyBusiness mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mTypeView = (TextView) view.findViewById(R.id.type);
            mImageView = (ImageView) view.findViewById(R.id.imageView);

            ImageView add_icon = (ImageView) view.findViewById(R.id.add_icon);
            add_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItem.in_library == false) {

                        add_icon.setImageResource(view.getResources().getIdentifier("check_icon",
                                "drawable", view.getContext().getPackageName()));
                        DummyContent.addItem(DummyContent.LIBRARY_ITEMS, mItem);
                        DummyContent.findItem(DummyContent.LIBRARY_ITEMS, mItem.name).in_library = true;
                        DummyContent.findItem(DummyContent.EXPLORE_ITEMS, mItem.name).in_library = true;
                    }
                    else {
                        add_icon.setImageResource(view.getResources().getIdentifier("add_icon",
                                "drawable", view.getContext().getPackageName()));
                        DummyContent.deleteItem(DummyContent.LIBRARY_ITEMS, mItem);
                        DummyContent.findItem(DummyContent.EXPLORE_ITEMS, mItem.name).in_library = false;
                    }
                }
            });
        }
    }
}
