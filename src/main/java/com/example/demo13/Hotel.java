package com.example.demo13;

public class Hotel extends Entity {
    private String name;
    private String location;

    public Hotel(int id, String name, String location) {
        super(id);
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getDetails() {
        return "Hotel Name: " + name + ", Location: " + location;
    }
}