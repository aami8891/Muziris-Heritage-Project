package com.example.aapplication.weather;

public enum Weather {

    PERIODIC_CLOUDS("")/*("Periodic Clouds")*/,
    CLOUDY("")/*("Cloudy")*/,
    MOSTLY_CLOUDY("")/*("Mostly Cloudy")*/,
    PARTLY_CLOUDY("")/*("Partly Cloudy")*/,
    CLEAR("")/*("Clear")*/,
    CHERAMAN("");
    private String displayName;

    Weather(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
