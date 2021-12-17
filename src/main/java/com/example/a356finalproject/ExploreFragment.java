package com.example.a356finalproject;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.a356finalproject.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class ExploreFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public TextView title;
    public ImageView filter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExploreFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ExploreFragment newInstance(int columnCount) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

//        DummyContent.FILTER_ITEMS = DummyContent.EXPLORE_ITEMS;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        DummyContent.FILTER_ITEMS = DummyContent.EXPLORE_ITEMS; // Resetting shiz here will resit it everytime I come back to the fragment
        DummyContent.clearBoxes();

        title = (TextView) view.findViewById(R.id.textView);
        title.setText("Explore");

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyExploreRecyclerViewAdapter(DummyContent.FILTER_ITEMS));


        filter = (ImageView) view.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = inflater.inflate(R.layout.popup_filter, null);

                int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupWindow.showAtLocation(view, Gravity.RIGHT | Gravity.TOP, 0, 230);

                CheckBox check_1 = (CheckBox) popupView.findViewById(R.id.food_box);
                CheckBox check_2 = (CheckBox) popupView.findViewById(R.id.retail_box);
                CheckBox check_3 = (CheckBox) popupView.findViewById(R.id.service_box);

                if (DummyContent.food_checked) {
                    check_1.setChecked(true);
                }
                else {
                    check_1.setChecked(false);
                }
                if (DummyContent.retail_checked) {
                    check_2.setChecked(true);
                }
                else {
                    check_2.setChecked(false);
                }
                if (DummyContent.service_checked) {
                    check_3.setChecked(true);
                }
                else {
                    check_3.setChecked(false);
                }

                Button apply = (Button) popupView.findViewById(R.id.apply);
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DummyContent.clearFilteredList();

                        if (check_1.isChecked()) {
                            DummyContent.createFilteredList("food", DummyContent.EXPLORE_ITEMS);
                            DummyContent.food_checked = true;
                        }
                        else {
                            DummyContent.food_checked = false;
                        }
                        if (check_2.isChecked()) {
                            DummyContent.createFilteredList("retail", DummyContent.EXPLORE_ITEMS);
                            DummyContent.retail_checked = true;
                        }
                        else {
                            DummyContent.retail_checked = false;
                        }
                        if (check_3.isChecked()) {
                            DummyContent.createFilteredList("salon", DummyContent.EXPLORE_ITEMS);
                            DummyContent.service_checked = true;
                        }
                        else {
                            DummyContent.service_checked = false;
                        }

                        recyclerView.setAdapter(new MyExploreRecyclerViewAdapter(DummyContent.FILTER_ITEMS));

                        popupWindow.dismiss();

                        return;
                    }
                });
            }
        });
        return view;
    }
}