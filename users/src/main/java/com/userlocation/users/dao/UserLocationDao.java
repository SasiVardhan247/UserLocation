package com.userlocation.users.dao;

public class UserLocationDao {
 
    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    public UserLocationDao() {
    }

    public UserLocationDao(Long id,String name, double latitude, double longitude) {
    	this.id=id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}