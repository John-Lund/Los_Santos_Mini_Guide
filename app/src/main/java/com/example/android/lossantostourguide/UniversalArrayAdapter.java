package com.example.android.lossantostourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UniversalArrayAdapter extends ArrayAdapter<DisplayObject> {
    private View.OnClickListener mPlacesListener;

    // constructor for places layout including OnClickListener to handle map view animation
    public UniversalArrayAdapter(@NonNull Context context, @NonNull List<DisplayObject> objects, View.OnClickListener placesListener) {
        this(context, objects);
        this.mPlacesListener = placesListener;
    }

    // base constructor for all other fragments apart from places fragment
    public UniversalArrayAdapter(@NonNull Context context, @NonNull List<DisplayObject> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // creating viewHolder reference
        ViewHolder viewHolder;

        // testing if convertView exists and creating it if it doesn't
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(getItem(position).getLayout(), parent, false);
            viewHolder = new ViewHolder(convertView, getItem(position));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // finding correct object and setting default views
        DisplayObject objectToDisplay = getItem(position);
        viewHolder.image.setImageResource(objectToDisplay.getImageId());
        viewHolder.title.setText(objectToDisplay.getTitle());
        viewHolder.description.setText(objectToDisplay.getDescription());

        // setting the rest of the views depending on object type
        if (objectToDisplay.getObjectType() == ObjectType.TRANSPORT) {
            viewHolder.mPassengers.setText(objectToDisplay.getPassengers());
            viewHolder.mCost.setText(objectToDisplay.getCost());
            viewHolder.mVehicleType.setText(objectToDisplay.getVehicleType());
        }
        if (objectToDisplay.getObjectType() == ObjectType.CUSTOMISATION) {
            viewHolder.mCustomisationType.setText(objectToDisplay.getCustomisationType());
            viewHolder.mCustomisationIcon.setImageResource(objectToDisplay.getCustomisationIcon());
        }
        if (objectToDisplay.getObjectType() == ObjectType.PROPERTY) {
            viewHolder.mCost.setText(objectToDisplay.getCost());
        }
        if (objectToDisplay.getObjectType() == ObjectType.ACTIVITY) {
            viewHolder.mPlayersNeeded.setText(objectToDisplay.getPlayersNeeded());
            viewHolder.mRating.setImageResource(objectToDisplay.getRating());
        }
        if (objectToDisplay.getObjectType() == ObjectType.PLACE) {
            viewHolder.mMapIcon.setOnClickListener(mPlacesListener);
            viewHolder.mMapIcon.setTag(objectToDisplay);
        }
        return convertView;
    }

    // viewHolder class
    private class ViewHolder {
        final ImageView image;
        final TextView title;
        final TextView description;
        private TextView mPassengers;
        private TextView mCost;
        private TextView mCustomisationType;
        private ImageView mCustomisationIcon;
        private TextView mPlayersNeeded;
        private ImageView mRating;
        private TextView mVehicleType;
        private ImageView mMapIcon;

        ViewHolder(View view, DisplayObject displayObject) {
            this.image = view.findViewById(R.id.image);
            this.title = view.findViewById(R.id.title);
            this.description = view.findViewById(R.id.description);
            // creating fields depending on object type
            if (displayObject.getObjectType() == ObjectType.TRANSPORT) {
                this.mPassengers = view.findViewById(R.id.vehicle_passengers);
                this.mCost = view.findViewById(R.id.vehicle_cost);
                this.mVehicleType = view.findViewById(R.id.vehicle_type);
            }
            if (displayObject.getObjectType() == ObjectType.CUSTOMISATION) {
                this.mCustomisationType = view.findViewById(R.id.customisation_type);
                this.mCustomisationIcon = view.findViewById(R.id.customisation_icon);
            }
            if (displayObject.getObjectType() == ObjectType.PROPERTY) {
                this.mCost = view.findViewById(R.id.property_cost);
            }
            if (displayObject.getObjectType() == ObjectType.ACTIVITY) {
                this.mPlayersNeeded = view.findViewById(R.id.activity_player_count);
                this.mRating = view.findViewById(R.id.activity_rating);
            }
            if (displayObject.getObjectType() == ObjectType.PLACE) {
                this.mMapIcon = view.findViewById(R.id.map_icon);
            }
        }
    }
}





































