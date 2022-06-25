package com.example.lebaluchon.modelsForecast;

public class Wind {
        public double speed;
        public int deg;

    public Wind(double speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
