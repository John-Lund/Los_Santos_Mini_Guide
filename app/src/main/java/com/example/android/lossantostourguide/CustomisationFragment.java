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


public class CustomisationFragment extends Fragment {
    private ConstraintLayout constraintLayout;
    private ChangeBounds transition = new ChangeBounds();
    private ConstraintSet constraintSet = new ConstraintSet();

    public CustomisationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        constraintLayout = getActivity().findViewById(R.id.main_constraint_layout_open);

        ArrayList<String> objectStrings = new ArrayList<>();
        objectStrings.add("character");
        objectStrings.add("vehicle");

        ArrayList<DisplayObject> displayObjects = new ArrayList<>();
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_bennys_garage, getString(R.string.custom_title_bennys), getString(R.string.custom_des_bennys), 1, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_clothes_binco, getString(R.string.custom_title_binco), getString(R.string.custom_des_binco), 0, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_clothes_ponsonbys, getString(R.string.custom_title_posonbys), getString(R.string.custom_des_posonbys), 0, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_clothes_suburban, getString(R.string.custom_title_suburban), getString(R.string.custom_des_suburban), 0, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_ls_customs, getString(R.string.custom_title_ls_customs), getString(R.string.custom_des_ls_customs), 1, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_mask_shop, getString(R.string.custom_title_mask_shop), getString(R.string.custom_des_mask_shop), 0, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_tattoo_parlor, getString(R.string.custom_title_tattoo_parlours), getString(R.string.custom_des_tattoo_parlours), 0, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_ammunation, getString(R.string.custom_title_ammunation), getString(R.string.custom_des_ammunation), 0, objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.CUSTOMISATION, R.drawable.customisation_barber_shop, getString(R.string.custom_title_barber_shop), getString(R.string.custom_des_barber_shop), 0, objectStrings));

        View rootView = inflater.inflate(R.layout.list_view_customisation, container, false);
        UniversalArrayAdapter adapter = new UniversalArrayAdapter(getActivity(), displayObjects);

        final ListView listView = rootView.findViewById(R.id.item_list_customisation);
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
