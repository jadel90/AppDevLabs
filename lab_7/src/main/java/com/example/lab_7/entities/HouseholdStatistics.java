package com.example.lab_7.entities;

public class HouseholdStatistics {

    private long emptyHouseholds;
    private long fullHouseholds;

    public HouseholdStatistics(long emptyHouseholds, long fullHouseholds) {
        this.emptyHouseholds = emptyHouseholds;
        this.fullHouseholds = fullHouseholds;
    }

    // Getters and Setters
    public long getEmptyHouseholds() {
        return emptyHouseholds;
    }

    public void setEmptyHouseholds(long emptyHouseholds) {
        this.emptyHouseholds = emptyHouseholds;
    }

    public long getFullHouseholds() {
        return fullHouseholds;
    }

    public void setFullHouseholds(long fullHouseholds) {
        this.fullHouseholds = fullHouseholds;
    }
}
