package com.example.lebaluchon.modelsConverter;

public class Info {
    private double rate;
    private  int timestamp;

    public Info(double rate, int timestamp) {
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
