package com.example.a356finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.a356finalproject.dummy.DummyContent;

import static com.example.a356finalproject.dummy.DummyContent.EXPLORE_ITEMS;
import static com.example.a356finalproject.dummy.DummyContent.FILTER_ITEMS;

public class MapsFragment extends Fragment {

    public DummyContent.DummyBusiness current_business;
    public TextView title_display;
    public TextView type_display;
    public ImageView add_icon;
    public ImageView image;
    public TextView title;
    public ImageView filter;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {


            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));

//            FILTER_ITEMS.equals(EXPLORE_ITEMS);

            for (int i = 0; i < EXPLORE_ITEMS.size(); i++) {
                LatLng location = new LatLng(EXPLORE_ITEMS.get(i).lat, EXPLORE_ITEMS.get(i).lon );
                googleMap.addMarker(new MarkerOptions().position(location).title(EXPLORE_ITEMS.get(i).name));
                if (i == 0) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 14.0f));
                }
            }

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {

                    displayMarkerContent(marker, googleMap);
                    return true;
                }
            });

            title = (TextView) getView().findViewById(R.id.textView);
            title.setText("Map");

            DummyContent.clearBoxes();

            filter = (ImageView) getView().findViewById(R.id.filter);
            filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View popupView = getLayoutInflater().inflate(R.layout.popup_filter, null);

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
                            googleMap.clear();
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

                            for (int i = 0; i < FILTER_ITEMS.size(); i++) {
                                LatLng location = new LatLng(FILTER_ITEMS.get(i).lat, FILTER_ITEMS.get(i).lon );
                                googleMap.addMarker(new MarkerOptions().position(location).title(FILTER_ITEMS.get(i).name));
                                if (i == 0) {
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 14.0f));
                                }
                            }

                            popupWindow.dismiss();

                            return;
                        }
                    });
                }
            });

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


        add_icon = (ImageView) view.findViewById(R.id.add_icon);
        add_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_business.in_library == false) {

                    add_icon.setImageResource(view.getResources().getIdentifier("check_icon",
                            "drawable", view.getContext().getPackageName()));
                    DummyContent.addItem(DummyContent.LIBRARY_ITEMS, current_business);
                    DummyContent.findItem(DummyContent.LIBRARY_ITEMS, current_business.name).in_library = true;
                    DummyContent.findItem(DummyContent.EXPLORE_ITEMS, current_business.name).in_library = true;
                }
                else {
                    add_icon.setImageResource(view.getResources().getIdentifier("add_icon",
                            "drawable", view.getContext().getPackageName()));
                    DummyContent.deleteItem(DummyContent.LIBRARY_ITEMS, current_business);
                    DummyContent.findItem(DummyContent.EXPLORE_ITEMS, current_business.name).in_library = false;
                }
            }
        });

    }

    public void displayMarkerContent(Marker marker, GoogleMap googleMap) {
        current_business = DummyContent.findItem(EXPLORE_ITEMS, marker.getTitle());
        String info = marker.getTitle();

        title_display = (TextView) getView().findViewById(R.id.title);
        title_display.setText(current_business.name);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.weight = .2f;
        params.gravity = Gravity.LEFT;
        title_display.setLayoutParams(params);

        type_display = (TextView) getView().findViewById(R.id.type);
        type_display.setVisibility(View.VISIBLE);
        type_display.setText(current_business.type);

        add_icon = (ImageView) getView().findViewById(R.id.add_icon);
        add_icon.setVisibility(View.VISIBLE);
        if (current_business.in_library == true) {
            add_icon.setImageResource(getView().getResources().getIdentifier("check_icon",
                    "drawable", getView().getContext().getPackageName()));
        }
        else {
            add_icon.setImageResource(getView().getResources().getIdentifier("add_icon",
                    "drawable", getView().getContext().getPackageName()));
        }


        image = (ImageView) getView().findViewById(R.id.imageView);
        image.setImageResource(getResources().getIdentifier(current_business.picture,
                "drawable", getContext().getPackageName()));
        image.setVisibility(View.VISIBLE);


    }
}