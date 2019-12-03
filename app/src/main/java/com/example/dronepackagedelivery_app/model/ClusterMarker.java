package com.example.dronepackagedelivery_app.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterMarker implements ClusterItem {
    private LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private int mIconPicture;

    public ClusterMarker() {}

    public ClusterMarker(double lat, double lng, String title, String snippet, int iconPicture) {
        this.mPosition = new LatLng(lat, lng);
        this.mTitle = title;
        this.mSnippet = snippet;
        this.mIconPicture = iconPicture;

    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public LatLng getmPosition() {
        return mPosition;
    }

    public void setmPosition(LatLng mPosition) {
        this.mPosition = mPosition;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSnippet() {
        return mSnippet;
    }

    public void setmSnippet(String mSnippet) {
        this.mSnippet = mSnippet;
    }

    public int getmIconPicture() {
        return mIconPicture;
    }

    public void setmIconPicture(int mIconPicture) {
        this.mIconPicture = mIconPicture;
    }
}