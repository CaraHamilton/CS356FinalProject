package com.example.a356finalproject;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a356finalproject.dummy.DummyContent;
import com.example.a356finalproject.dummy.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessInfoFragment extends Fragment {

    public DummyContent.DummyBusiness current_business;
    public TextView title;
    public TextView type;
    public ImageView top_image;
    public Button add_review;
    public int rating;
    List<ImageView> total_stars = new ArrayList<ImageView>();

    public BusinessInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index Param1
     * @return A new instance of fragment BusinessInfoFragment.
     */
    public static BusinessInfoFragment newInstance(int index) {
        BusinessInfoFragment fragment = new BusinessInfoFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        int index = args.getInt("index");

        current_business = DummyContent.findItemById(DummyContent.EXPLORE_ITEMS, index);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_info, container, false);

        title = (TextView) view.findViewById(R.id.main_title);
        title.setText(current_business.name);

        type = (TextView) view.findViewById(R.id.type_title);
        type.setText(current_business.type);

        top_image = (ImageView) view.findViewById(R.id.top_image);
        top_image.setImageResource(view.getResources().getIdentifier(current_business.picture,
                "drawable", view.getContext().getPackageName()));


        ImageView star1 = (ImageView) view.findViewById(R.id.star1);
        total_stars.add(star1);
        ImageView star2 = (ImageView) view.findViewById(R.id.star2);
        total_stars.add(star2);
        ImageView star3 = (ImageView) view.findViewById(R.id.star3);
        total_stars.add(star3);
        ImageView star4 = (ImageView) view.findViewById(R.id.star4);
        total_stars.add(star4);
        ImageView star5 = (ImageView) view.findViewById(R.id.star5);
        total_stars.add(star5);

        int loop_constraint = current_business.calculateRating();

        for (int i = 0; i < loop_constraint; i++) {
            total_stars.get(i).setImageResource(R.drawable.star);
        }

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.review_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(new MyReviewRecyclerViewAdapter(current_business.reviews));

        add_review = (Button) view.findViewById(R.id.add_review);
        add_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.popup_window);
                View popupView = inflater.inflate(R.layout.popup_review, null);

                int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                List<ImageView> stars = new ArrayList<ImageView>();

                ImageView star1 = (ImageView) popupView.findViewById(R.id.star1);
                stars.add(star1);
                ImageView star2 = (ImageView) popupView.findViewById(R.id.star2);
                stars.add(star2);
                ImageView star3 = (ImageView) popupView.findViewById(R.id.star3);
                stars.add(star3);
                ImageView star4 = (ImageView) popupView.findViewById(R.id.star4);
                stars.add(star4);
                ImageView star5 = (ImageView) popupView.findViewById(R.id.star5);
                stars.add(star5);

                star1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rating = 1;
                        for (int i = 0; i < 1; i++) {
                            stars.get(i).setImageResource(R.drawable.star);
                        }
                    }
                });

                star2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rating = 2;
                        for (int i = 0; i < 2; i++) {
                            stars.get(i).setImageResource(R.drawable.star);
                        }
                    }
                });

                star3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rating = 3;
                        for (int i = 0; i < 3; i++) {
                            stars.get(i).setImageResource(R.drawable.star);
                        }
                    }
                });

                star4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rating = 4;
                        for (int i = 0; i < 4; i++) {
                            stars.get(i).setImageResource(R.drawable.star);
                        }
                    }
                });

                star5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rating = 5;
                        for (int i = 0; i < 5; i++) {
                            stars.get(i).setImageResource(R.drawable.star);
                        }
                    }
                });


                Button submit_button = (Button) popupView.findViewById(R.id.submit);
                submit_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText review_edit_text = (EditText) popupView.findViewById(R.id.edit_text);
                        String review_text = review_edit_text.getText().toString();

                        EditText name_edit_text = (EditText) popupView.findViewById(R.id.edit_name);
                        String name_text = name_edit_text.getText().toString();;
                        if (name_text.equals("")) {
                            name_text = "Unknown";
                        }

                        Review.DummyReview new_review = new Review.DummyReview(review_text, name_text, rating);
                        current_business.add_review(new_review);

                        int loop_constraint = current_business.calculateRating();
                        for (int i = 0; i < loop_constraint; i++) {
                            total_stars.get(i).setImageResource(R.drawable.star);
                        }
                        for (int i = loop_constraint; i < 5; i++) {
                            total_stars.get(i).setImageResource(R.drawable.star_outline);
                        }


                        popupWindow.dismiss();

                        return;
                    }
                });
            }
        });
        return view;
    }
}
