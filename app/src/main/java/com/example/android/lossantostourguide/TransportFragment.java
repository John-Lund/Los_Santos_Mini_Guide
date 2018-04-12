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

public class TransportFragment extends Fragment {
    private ConstraintLayout constraintLayout;
    private ChangeBounds transition = new ChangeBounds();
    private ConstraintSet constraintSet = new ConstraintSet();

    public TransportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        constraintLayout = getActivity().findViewById(R.id.main_constraint_layout_open);

        ArrayList<String> objectStrings = new ArrayList<>();
        objectStrings.add(getString(R.string.thousand_dollars));
        objectStrings.add(getString(R.string.million_dollars));
        objectStrings.add(getString(R.string.players));

        ArrayList<DisplayObject> displayObjects = new ArrayList<>();
        displayObjects.add(new DisplayObject(ObjectType.TRANSPORT, R.drawable.transport_akula, getString(R.string.tran_title_akula), getString(R.string.tran_des_akula), 4, 2.2d, false, getString(R.string.helicopter), objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.TRANSPORT, R.drawable.transport_buzzard, getString(R.string.tran_title_buzzard), getString(R.string.tran_des_buzzard), 4, 2.3d, false, getString(R.string.helicopter), objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.TRANSPORT, R.drawable.transport_nightshark, getString(R.string.tran_title_nightshark), getString(R.string.tran_des_nightshark), 4, 2.5d, false, getString(R.string.car), objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.TRANSPORT, R.drawable.transport_pyro, getString(R.string.tran_title_pyro), getString(R.string.tran_des_pyro), 2, 3.12d, false, getString(R.string.jet_fighter), objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.TRANSPORT, R.drawable.transport_savage, getString(R.string.tran_title_savage), getString(R.string.tran_des_savage), 4, 2.2d, false, getString(R.string.helicopter), objectStrings));
        displayObjects.add(new DisplayObject(ObjectType.TRANSPORT, R.drawable.transport_stromberg, getString(R.string.tran_title_stromberg), getString(R.string.tran_des_stromberg), 2, 2.2d, false, getString(R.string.car), objectStrings));

        View rootView = inflater.inflate(R.layout.list_view_transport, container, false);
        UniversalArrayAdapter adapter = new UniversalArrayAdapter(getActivity(), displayObjects);
        final ListView listView = rootView.findViewById(R.id.item_list_transport);
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
