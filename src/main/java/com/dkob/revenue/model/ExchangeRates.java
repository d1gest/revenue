package com.dkob.revenue.model;

public class ExchangeRates {
    private double todayRate;
    private double oldRate;

    public ExchangeRates(double todayRate, double oldRate) {
        this.todayRate = todayRate;
        this.oldRate = oldRate;
    }

    public double getTodayRate() {
        return todayRate;
    }

    public void setTodayRate(double todayRate) {
        this.todayRate = todayRate;
    }

    public double getOldRate() {
        return oldRate;
    }

    public void setOldRate(double oldRate) {
        this.oldRate = oldRate;
    }
}
