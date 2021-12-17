package com.example.a356finalproject.dummy;

import java.util.ArrayList;
import java.util.List;

public class Review {

//    public static final List<Review.DummyReview> ITEMS = new ArrayList<DummyReview>();
//
//    private static final int COUNT = 5;

    static {

    }

    public static class DummyReview {
        public String review_text;
        public String name;
        public int rating;

        public DummyReview(String review_text, String name, int rating) {
            this.review_text = review_text;
            this.name = name;
            this.rating = rating;
        }
    }
}
