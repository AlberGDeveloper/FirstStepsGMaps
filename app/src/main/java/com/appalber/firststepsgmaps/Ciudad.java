package com.appalber.firststepsgmaps;

public class Ciudad {
    private String city;
    private double lat, lng;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "city='" + city + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
