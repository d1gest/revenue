package com.dkob.revenue.model;

import javax.validation.constraints.Positive;
import java.util.Date;

public class RevenueRequest {

    private Date date;
    @Positive
    private double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
