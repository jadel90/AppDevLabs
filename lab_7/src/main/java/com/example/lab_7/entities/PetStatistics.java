

package com.example.lab_7.entities;

import java.util.List;

public class PetStatistics {
    private double averageAge;
    private int oldestAge;
    private int totalCount;
    private Pet petDetails; // Oldest pet
    private List<Pet> allPets; // Include all pets in the statistics

    // Constructor
    public PetStatistics(double averageAge, int oldestAge, int totalCount, Pet petDetails, List<Pet> allPets) {
        this.averageAge = averageAge;
        this.oldestAge = oldestAge;
        this.totalCount = totalCount;
        this.petDetails = petDetails;
        this.allPets = allPets;
    }

    // Getters and setters for all fields
    public double getAverageAge() {
        return averageAge;
    }

    public int getOldestAge() {
        return oldestAge;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Pet getPetDetails() {
        return petDetails;
    }

    public List<Pet> getAllPets() {
        return allPets;
    }

    public void setAllPets(List<Pet> allPets) {
        this.allPets = allPets;
    }

}
