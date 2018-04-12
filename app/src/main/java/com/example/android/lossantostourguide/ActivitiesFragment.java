package com.example.android.lossantostourguide;
//////////////////////////////////////////////////////////////////////////
///////////// SEE PLACES FRAGMENT FOR COMMENTS
//////////////////////////////////////////////////////////////////////////
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
import android.widget.ListView;

import java.util.ArrayList;


public class ActivitiesFragment extends Fragment {
    private ConstraintLayout constraintLayout;
    private ChangeBounds transition = new ChangeBounds();
    private ConstraintSet constraintSet = new ConstraintSet();

    public ActivitiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        constraintLayout = getActivity().findViewById(R.id.main_constraint_layout_open);

        ArrayList<String> objectStrings = new ArrayList<>();
        objectStrings.add(getString(R.string.players));
        objectStrings.add(getString(R.string.player));
        objectStrings.add(getString(R.string.one_or_more_players));

        ArrayList<DisplayObject> displayObjects = new ArrayList<>();
        displayObjects.add(new DisplayObject(ObjectType.ACTIVITY, R.drawable.activity_darts, getString(R.string.activity_title_darts), getString(R.string.activity_des_darts), -2, 3, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.ACTIVITY, R.drawable.activity_golf, getString(R.string.activity_title_golf), getString(R.string.activity_des_golf), -4, 4, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.ACTIVITY, R.drawable.activity_roller_coaster, getString(R.string.activity_title_roller_coaster), getString(R.string.activity_des_roller_coaster), -16, 3, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.ACTIVITY, R.drawable.activity_shooting_range, getString(R.string.activity_title_shooting_range), getString(R.string.activity_des_shooting_range), -2, 5, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.ACTIVITY, R.drawable.activity_tennis, getString(R.string.activity_title_tennis), getString(R.string.activity_des_tennis), 2, 3, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.ACTIVITY, R.drawable.activity_ferris_wheel, getString(R.string.activity_title_ferris_wheel), getString(R.string.activity_des_ferris_wheel), 1, 0, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.ACTIVITY, R.drawable.activity_arm_wrestling, getString(R.string.activity_title_arm_wrestling), getString(R.string.activity_des_arm_wrestling), 2, 1, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.ACTIVITY, R.drawable.activity_cinema, getString(R.string.activity_title_cinema), getString(R.string.activity_des_cinema), 1, 3, objectStrings));

        View rootView = inflater.inflate(R.layout.list_view_activities, container, false);
        UniversalArrayAdapter adapter = new UniversalArrayAdapter(getActivity(), displayObjects);
        final ListView listView = rootView.findViewById(R.id.item_list_activities);

        listView.setAdapter(adapter);

        AbsListView.OnScrollListener listListener = new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                transition.setDuration(300);
                if (!MainActivity.closed) {
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

        listView.setOnScrollListener(listListener);
        return rootView;
    }
}




































