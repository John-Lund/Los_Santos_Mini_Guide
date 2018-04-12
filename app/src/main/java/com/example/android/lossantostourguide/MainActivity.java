package com.example.android.lossantostourguide;


import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // fields for main layout
    private ImageView categoryImage;
    private ImageView tourGuideTopGraphic;
    private TabLayout tabLayout;

    // constraint layout animation fields
    public ConstraintLayout main_layout;
    private ChangeBounds transition = new ChangeBounds();
    private ConstraintSet constraintSet = new ConstraintSet();
    public static boolean closed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_open_state);

        // referencing the current layout with "main_layout" for later animation
        main_layout = findViewById(R.id.main_constraint_layout_open);
        setContentView(main_layout);

        // setting up onClickListener to handle animation backwards from map view in places activity
        View.OnClickListener placesListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition.setDuration(500);
                constraintSet.clone(MainActivity.this, R.layout.activity_main_closed_state);
                TransitionManager.beginDelayedTransition(main_layout, transition);
                constraintSet.applyTo(main_layout);
            }
        };

        // applying listener to back button in map view
        ImageView backButton = findViewById(R.id.map_back_button);
        backButton.setOnClickListener(placesListener);

        // setting up references for main layout items
        categoryImage = findViewById(R.id.category_image);
        tourGuideTopGraphic = findViewById(R.id.tour_guide_top_graphic);
        final ViewPager viewPager = findViewById(R.id.viewpager);

        // setting up array to carry strings into pagerAdapter so that they can be used from values folder
        String[] tabNames = {getString(R.string.places), getString(R.string.activities), getString(R.string.properties), getString(R.string.customisation), getString(R.string.transport)};

        // setting up pagerAdapter and tabLayout
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabNames);
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        // setting up onPageChangeListener to handle page style changes depending on tab selected by user
        ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // loads places styles
                if (position == 0) {
                    categoryImage.setImageResource(R.drawable.places_maze_bank_tower);
                    tourGuideTopGraphic.setImageResource(R.drawable.tour_guide_places_svg);
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor(getString(R.string.place_colour)));
                    tabLayout.setTabTextColors(Color.parseColor(getString(R.string.tab_text_colour_unselected)), Color.parseColor(getString(R.string.place_colour)));
                    main_layout.setBackgroundColor(Color.parseColor(getString(R.string.place_colour)));
                }
                // loads activities styles
                if (position == 1) {
                    categoryImage.setImageResource(R.drawable.activity_golf);
                    tourGuideTopGraphic.setImageResource(R.drawable.tour_guide_activities_svg);
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor(getString(R.string.activities_colour)));
                    tabLayout.setTabTextColors(Color.parseColor(getString(R.string.tab_text_colour_unselected)), Color.parseColor(getString(R.string.activities_colour)));
                    main_layout.setBackgroundColor(Color.parseColor(getString(R.string.background_white)));
                }
                // loads properties styles
                if (position == 2) {
                    categoryImage.setImageResource(R.drawable.property_apartment);
                    tourGuideTopGraphic.setImageResource(R.drawable.tour_guide_property_svg);
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor(getString(R.string.properties_colour)));
                    tabLayout.setTabTextColors(Color.parseColor(getString(R.string.tab_text_colour_unselected)), Color.parseColor(getString(R.string.properties_colour)));
                    main_layout.setBackgroundColor(Color.parseColor(getString(R.string.background_grey)));
                }
                // loads customisation styles
                if (position == 3) {
                    categoryImage.setImageResource(R.drawable.customisation_barber_shop);
                    tourGuideTopGraphic.setImageResource(R.drawable.tour_guide_customisation_svg);
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor(getString(R.string.customisation_colour)));
                    tabLayout.setTabTextColors(Color.parseColor(getString(R.string.tab_text_colour_unselected)), Color.parseColor(getString(R.string.customisation_colour)));
                    main_layout.setBackgroundColor(Color.parseColor(getString(R.string.customisation_colour)));
                }
                // loads transport styles
                if (position == 4) {
                    categoryImage.setImageResource(R.drawable.transport_pyro);
                    tourGuideTopGraphic.setImageResource(R.drawable.tour_guide_transport_svg);
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor(getString(R.string.transport_colour)));
                    tabLayout.setTabTextColors(Color.parseColor(getString(R.string.tab_text_colour_unselected)), Color.parseColor(getString(R.string.transport_colour)));
                    main_layout.setBackgroundColor(Color.parseColor(getString(R.string.background_white)));
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
        // adding above listener to viewPager
        viewPager.addOnPageChangeListener(pageChangeListener);
    }
}




























