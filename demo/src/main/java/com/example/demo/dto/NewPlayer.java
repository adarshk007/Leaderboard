package com.example.demo.dto;

import java.sql.Date;

public class NewPlayer extends Player{
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
