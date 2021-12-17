package com.example.a356finalproject.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
//    public static List<DummyBusiness> ITEMS = new ArrayList<DummyBusiness>();

    public static List<DummyBusiness> EXPLORE_ITEMS = new ArrayList<DummyBusiness>();

    public static List<DummyBusiness> LIBRARY_ITEMS = new ArrayList<DummyBusiness>();

    public static List<DummyBusiness> FILTER_ITEMS = new ArrayList<DummyBusiness>();

    public static boolean food_checked = false;

    public static boolean retail_checked = false;

    public static boolean service_checked = false;


    static {

        DummyBusiness peace_on_earth = new DummyBusiness(0, "Peace on Earth", "Cafe", "food", 40.23410012782629, -111.66418040236938, "peaceonearth");
        Review.DummyReview peace_review = new Review.DummyReview("What a wonderful little cafe. I recommend the pumpkin chai.", "Lily S.", 5);
        peace_on_earth.add_review(peace_review);
        addItem(EXPLORE_ITEMS, peace_on_earth);

        DummyBusiness hruskas_kolaches = new DummyBusiness(1, "Hruskas Kolaches", "Bakery", "food", 40.23414331353441, -111.66615708675192, "kolaches");
        Review.DummyReview kolche_review = new Review.DummyReview("The best kolaches I've ever had. I would kill for another.", "Jake O.", 5);
        hruskas_kolaches.add_review(kolche_review);
        addItem(EXPLORE_ITEMS, hruskas_kolaches);

        DummyBusiness time_travelers_bakery = new DummyBusiness(2, "Time Travelers", "Bakery", "food", 40.23469930043237, -111.6593727739132, "timetavelers");
        Review.DummyReview time_review = new Review.DummyReview("This bakery has the cutest little seating area. Great place to come do homework.", "Kat L.", 4);
        time_travelers_bakery.add_review(time_review);
        addItem(EXPLORE_ITEMS, time_travelers_bakery);

        DummyBusiness pioneer = new DummyBusiness(3, "Pioneer Book", "Bookstore", "retail", 40.23408306043138, -111.66658798856862, "pioneer_book");
        Review.DummyReview pioneer_review = new Review.DummyReview("Cool aesthetic, didn't have Harry Potter though :/", "Caleb W.", 3);
        pioneer.add_review(pioneer_review);
        addItem(EXPLORE_ITEMS, pioneer);

        DummyBusiness sun_kissed = new DummyBusiness(4, "Sun Kissed Massage", "Tanning Salon", "salon", 40.250053168459296, -111.6631769732589, "sun_kissed");
        Review.DummyReview sun_review = new Review.DummyReview("Nice place.", "Sarah", 5);
        sun_kissed.add_review(sun_review);
        addItem(EXPLORE_ITEMS, sun_kissed);

        DummyBusiness harmony = new DummyBusiness(5, "Harmony", "Fabric Store", "retail", 40.23404613202822, -111.65418407322454, "harmony");
        Review.DummyReview harmony_review = new Review.DummyReview("Nice fabrics, nicer workers. ", "Kaleigh", 5);
        harmony.add_review(harmony_review);
        addItem(EXPLORE_ITEMS, harmony);

        DummyBusiness salon_359 = new DummyBusiness(6, "Salon 359", "Hair Salon", "salon", 40.23231718503884, -111.66498467507675, "salon_359");
        Review.DummyReview salon_review = new Review.DummyReview("I got the worst hair cut here", "Karen", 1);
        salon_359.add_review(salon_review);
        addItem(EXPLORE_ITEMS, salon_359);

        DummyBusiness vegan_sun = new DummyBusiness(7, "Vegan Sun", "Restaurant", "food", 40.233687427849354, -111.66281015973254, "vegan_sun");
        Review.DummyReview vegan_review = new Review.DummyReview("Best vegan food around", "Leah", 5);
        vegan_sun.add_review(vegan_review);
        addItem(EXPLORE_ITEMS, vegan_sun);


        FILTER_ITEMS.equals(EXPLORE_ITEMS);

    }

    public static void addItem(List<DummyBusiness> list, DummyBusiness item) {
        list.add(item);
    }

    public static void deleteItem(List<DummyBusiness> list, DummyBusiness item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == item) {
                list.remove(i);
            }
        }
    }

    public static DummyBusiness findItem(List<DummyBusiness> list, String title) {
        for (int i = 0; i < list.size(); i++) {
            if (title.equals(list.get(i).name)) {
                return list.get(i);
            }
        }
        return null;
    }

    public static DummyBusiness findItemById(List<DummyBusiness> list, int id) {
        return list.get(id);
    }

    public static void clearFilteredList() {
        FILTER_ITEMS = new ArrayList<DummyBusiness>();
    }

    public static void createFilteredList(String type, List<DummyBusiness> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).category.equals(type)) {
                FILTER_ITEMS.add(list.get(i));
            }
        }
    }

    public static void clearBoxes() {
        food_checked = false;
        retail_checked = false;
        service_checked = false;
    }
    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyBusiness {
        public int id;
        public final String name;
        public final String type;
        public final String category;
        public double lat;
        public double lon;
        public final String picture;
        public boolean in_library = false;
        public List<Review.DummyReview> reviews = new ArrayList<Review.DummyReview>();

        public DummyBusiness(int id, String name, String type, String category, double lat, double lon, String picture) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.category = category;
            this.lat = lat;
            this.lon = lon;
            this.picture = picture;
        }

        public void add_review (Review.DummyReview review) {
            this.reviews.add(review);
        }

        public int calculateRating() {
            int num_ratings = reviews.size();
            int total_score = 0;
            for (int i = 0; i < num_ratings; i++) {
                total_score += reviews.get(i).rating;
            }
            return (total_score/num_ratings);
        }
    }
}