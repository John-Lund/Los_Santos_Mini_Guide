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

public class PropertyFragment extends Fragment {
    private ConstraintLayout constraintLayout;
    private ChangeBounds transition = new ChangeBounds();
    private ConstraintSet constraintSet = new ConstraintSet();

    public PropertyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        constraintLayout = getActivity().findViewById(R.id.main_constraint_layout_open);

        ArrayList<String> objectStrings = new ArrayList<>();
        objectStrings.add(getString(R.string.thousand_dollars));
        objectStrings.add(getString(R.string.million_dollars));

        ArrayList<DisplayObject> displayObjects = new ArrayList<>();
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_import_export_garage, getString(R.string.prop_title_import_export_garages), getString(R.string.prop_des_import_export_garages), 1, false, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_office, getString(R.string.prop_title_offices), getString(R.string.prop_des_offices), 400, true, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_apartment, getString(R.string.prop_title_apartments), getString(R.string.prop_des_apartments), 60, true, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_bunker, getString(R.string.prop_title_bunkers), getString(R.string.prop_des_bunkers), 2.4, false, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_clubhouse, getString(R.string.prop_title_clubhouses), getString(R.string.prop_des_clubhouses), 350, true, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_crate_warehouse, getString(R.string.prop_title_crate_warehouse), getString(R.string.prop_des_crate_warehouse), 250, true, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_facility, getString(R.string.prop_title_facilities), getString(R.string.prop_des_facilities), 2.4, false, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_garage, getString(R.string.prop_title_garages), getString(R.string.prop_des_garages), 20, true, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_hangar, getString(R.string.prop_title_hanger), getString(R.string.prop_des_hanger), 1.5, false, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.PROPERTY, R.drawable.property_yacht, getString(R.string.prop_title_yachts), getString(R.string.prop_des_yachts), 5, false, objectStrings));

        View rootView = inflater.inflate(R.layout.list_view_properties, container, false);
        UniversalArrayAdapter adapter = new UniversalArrayAdapter(getActivity(), displayObjects);

        final ListView listView = rootView.findViewById(R.id.item_list_properties);
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
