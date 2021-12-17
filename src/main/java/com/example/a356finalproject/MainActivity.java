package com.example.a356finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private MapsFragment maps_fragment = new MapsFragment();
    private ExploreFragment explore_fragment = new ExploreFragment();
    private LibraryFragment library_fragment = new LibraryFragment();
    private BusinessInfoFragment business_info_fragment = new BusinessInfoFragment();
    public FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentContainer, maps_fragment);
        ft.addToBackStack(null);
        ft.commit();

        ImageView explore_button = (ImageView) findViewById(R.id.explore_button);
        explore_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentContainer, explore_fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        ImageView map_button = (ImageView) findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentContainer, maps_fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        ImageView library_button = (ImageView) findViewById(R.id.library_button);
        library_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentContainer, library_fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }

    public void replace_fragment() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, business_info_fragment);
        ft.addToBackStack(null);
        ft.commit();
    };

}