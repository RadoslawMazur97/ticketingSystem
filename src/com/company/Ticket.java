package com.company;

public class Ticket implements ITicket{

    double cost;
    String type;
    int duration_min;


    public Ticket(double cost, String type, int duration_min) {
        this.cost = cost;
        this.type = type;
        this.duration_min = duration_min;
    }

    @Override
    public double getCost() {
        return cost;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public int getDuration_min() {
        return duration_min;
    }
}
