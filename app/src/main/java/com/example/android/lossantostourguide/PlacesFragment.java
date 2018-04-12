package com.example.android.lossantostourguide;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class PlacesFragment extends Fragment {
    // fields for header constraint animation
    private ConstraintLayout constraintLayout;
    private ChangeBounds transition = new ChangeBounds();
    private ConstraintSet constraintSet = new ConstraintSet();

    public PlacesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // referencing mainlayout for later animation
        constraintLayout = getActivity().findViewById(R.id.main_constraint_layout_open);

        // setting up listener to handle map layout animation and select the correct map image
        View.OnClickListener placesListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayObject thisObject = (DisplayObject) v.getTag();
                ImageView mapToDisplay = getActivity().findViewById(R.id.mapImage);
                mapToDisplay.setImageResource(thisObject.getmMapId());
                transition.setDuration(500);
                constraintSet.clone(getContext(), R.layout.activity_main_map_viewing_state);
                TransitionManager.beginDelayedTransition(constraintLayout, transition);
                constraintSet.applyTo(constraintLayout);
            }
        };

        // creating ArrayList for object to be displayed by places list view
        ArrayList<DisplayObject> displayObjects = new ArrayList<>();
        displayObjects.add(new DisplayObject(ObjectType.PLACE, R.drawable.places_verspucci_pier, getString(R.string.place_title_vespucci), R.drawable.map_del_perro_a, getString(R.string.place_des_vespucci)));
        displayObjects.add(new DisplayObject(ObjectType.PLACE, R.drawable.place_zancudo, getString(R.string.place_title_zancudo), R.drawable.map_zancudo_a, getString(R.string.place_des_zancudo)));
        displayObjects.add(new DisplayObject(ObjectType.PLACE, R.drawable.places_altruists, getString(R.string.place_title_altruist), R.drawable.map_altruist_a, getString(R.string.place_des_altruist)));
        displayObjects.add(new DisplayObject(ObjectType.PLACE, R.drawable.places_chilliad, getString(R.string.place_title_chilliad), R.drawable.map_chilliad_a, getString(R.string.place_des_chilliad)));
        displayObjects.add(new DisplayObject(ObjectType.PLACE, R.drawable.places_maze_bank_tower, getString(R.string.place_title_maze), R.drawable.map_maze_bank_tower_a, getString(R.string.place_des_maze)));
        displayObjects.add(new DisplayObject(ObjectType.PLACE, R.drawable.places_observatory, getString(R.string.place_title_observatory), R.drawable.map_observatory_a, getString(R.string.place_des_observatory)));
        displayObjects.add(new DisplayObject(ObjectType.PLACE, R.drawable.places_old_mine, getString(R.string.place_title_mine), R.drawable.map_old_mine_a, getString(R.string.place_des_mine)));

        // creating rootView and setting it up with the ArrayAdapter
        View rootView = inflater.inflate(R.layout.list_view_places, container, false);
        UniversalArrayAdapter adapter = new UniversalArrayAdapter(getActivity(), displayObjects, placesListener);
        final ListView listView = rootView.findViewById(R.id.item_list_places);
        listView.setAdapter(adapter);

        // setting OnScrollListener to handle header animations when user scrolls page
        AbsListView.OnScrollListener listListener = new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (!MainActivity.closed) {
                    transition.setDuration(300);
                    constraintSet.clone(getContext(), R.layout.activity_main_closed_state);
                    TransitionManager.beginDelayedTransition(constraintLayout, transition);
                    constraintSet.applyTo(constraintLayout);
                    MainActivity.closed = true;
                }
                if (MainActivity.closed && !listView.canScrollVertically(-1)) {
                    constraintSet.clone(getContext(), R.layout.activity_main_open_state);
                    TransitionManager.beginDelayedTransition(constraintLayout, transition);
                    constraintSet.applyTo(constraintLayout);
                    MainActivity.closed = false;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        };
        // adding OnScrollListener to listView and returning rootView
        listView.setOnScrollListener(listListener);
        return rootView;
    }
}







































