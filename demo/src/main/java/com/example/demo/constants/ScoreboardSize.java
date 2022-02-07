package com.example.demo.constants;

public enum ScoreboardSize {
    FIVE(5);

    private int numVal;
    ScoreboardSize(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
