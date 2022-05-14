package com.example.lab2.models;

public class Seat {
    private int number;
    private boolean isBooked;
    private float price;

    public Seat(int number, boolean isBooked, float price) {
        this.number = number;
        this.isBooked = isBooked;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
