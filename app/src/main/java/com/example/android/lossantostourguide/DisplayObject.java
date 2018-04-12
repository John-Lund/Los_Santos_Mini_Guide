package com.example.android.lossantostourguide;

import java.util.ArrayList;
// enums to control object type so that ArrayAdapter can sort objects easily
enum ObjectType {
    ACTIVITY, PLACE, CUSTOMISATION, TRANSPORT, PROPERTY
}

public class DisplayObject {
    private int mImageId;
    private String mTitle;
    private String mDescription;
    private int mPlayersNeeded;
    private int mRating;
    private double mCost;
    private boolean mKIsTrue;
    private int mCustomisationType;
    private int mCustomisationIcon;
    private int mPassengers;
    private String mVehicleType;
    private ObjectType mObjectType;
    private int mLayout;
    private int mMapId;
    private ArrayList<String> objectStrings;

    // transport object constructor and getter methods
    public DisplayObject(ObjectType objectType, int imageId, String title, String description, int passengers, double cost, boolean kIsTrue, String vehicleType, ArrayList<String> objectStrings) {
        this(R.layout.transport_layout, objectType, imageId, title, description);
        this.objectStrings = objectStrings;
        this.mCost = cost;
        this.mPassengers = passengers;
        this.mKIsTrue = kIsTrue;
        this.mVehicleType = vehicleType;
    }

    public String getVehicleType() {
        return mVehicleType;
    }

    public String getPassengers() {
        return mPassengers + " " + objectStrings.get(2);
    }

    // customisation object constructor and getter methods
    public DisplayObject(ObjectType objectType, int imageId, String title, String description, int customisationType, ArrayList<String> objectStrings) {
        this(R.layout.customisation_layout, objectType, imageId, title, description);
        this.mCustomisationType = customisationType;
        this.objectStrings = objectStrings;
        if (customisationType == 0) {
            this.mCustomisationIcon = R.drawable.character_icon_tourguide;
        } else {
            this.mCustomisationIcon = R.drawable.vehicle_icon_tourguide;
        }
    }

    public int getCustomisationIcon() {
        return mCustomisationIcon;
    }

    public String getCustomisationType() {
        if (mCustomisationType == 0) {
            return objectStrings.get(0);
        } else {
            return objectStrings.get(1);
        }
    }

    // property object constructor
    public DisplayObject(ObjectType objectType, int imageId, String title, String description, double cost, boolean kIsTrue, ArrayList<String> objectStrings) {
        this(R.layout.properties_layout, objectType, imageId, title, description);
        this.objectStrings = objectStrings;
        this.mCost = cost;
        this.mKIsTrue = kIsTrue;
    }

    // activity object constructor and getter methods
    public DisplayObject(ObjectType objectType, int imageId, String title, String description, int playersNeeded, int rating, ArrayList<String> objectStrings) {
        this(R.layout.activities_layout, objectType, imageId, title, description);
        this.objectStrings = objectStrings;
        this.mPlayersNeeded = playersNeeded;
        this.mRating = rating;
    }

    public String getPlayersNeeded() {
        if (mPlayersNeeded < 0) {
            return objectStrings.get(2) + mPlayersNeeded * -1 + " " + objectStrings.get(0);
        } else if (mPlayersNeeded == 1) {
            return mPlayersNeeded + " " + objectStrings.get(1);
        } else {
            return mPlayersNeeded + " " + objectStrings.get(0);
        }
    }

    public int getRating() {
        if (mRating == 0) {
            return R.drawable.no_stars_tourguide;
        }
        if (mRating == 1) {
            return R.drawable.one_star_tourguide;
        }
        if (mRating == 2) {
            return R.drawable.two_stars_tourguide;
        }
        if (mRating == 3) {
            return R.drawable.three_stars_tourguide;
        }
        if (mRating == 4) {
            return R.drawable.four_stars_tourguide;
        }
        return R.drawable.five_stars_tourguide;
    }

    // places object constructor and getter method
    public DisplayObject(ObjectType objectType, int imageId, String title, int mapId, String description) {
        this(R.layout.places_layout, objectType, imageId, title, description);
        this.mImageId = imageId;
        this.mTitle = title;
        this.mDescription = description;
        this.mMapId = mapId;
    }

    public int getmMapId() {
        return mMapId;
    }

    // base constructor
    public DisplayObject(int layout, ObjectType objectType, int imageId, String title, String description) {
        this.mImageId = imageId;
        this.mTitle = title;
        this.mDescription = description;
        this.mLayout = layout;
        this.mObjectType = objectType;
    }

    /// common getter methods
    public int getLayout() {
        return mLayout;
    }

    public ObjectType getObjectType() {
        return mObjectType;
    }

    public int getImageId() {
        return mImageId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getCost() {
        if (mKIsTrue) {
            return (int) mCost + objectStrings.get(0);
        } else {

            return mCost + objectStrings.get(1);
        }
    }
}
