


package com.example.lab_7.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "household")
@AllArgsConstructor
@NoArgsConstructor
public class Household {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String eircode;  // Primary key

    private int numberOfOccupants;

    private int maxNumberOfOccupants;

    private boolean ownerOccupied;

    @Setter
    @Getter
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@ToString.Exclude
    //@JsonBackReference // Prevents infinite recursion in JSON serialization
    @JsonManagedReference
    private List<Pet> pets; // List of pets associated with the household, can be null

    // Getters and Setters

    // Add convenience methods for managing the bidirectional relationship
    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setHousehold(this);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setHousehold(null);
    }


    // Check if the household is empty
    public boolean isEmpty() {
        return numberOfOccupants == 0;
    }

    // Check if the household is full
    public boolean isFull() {
        return numberOfOccupants >= maxNumberOfOccupants;
    }

    public void setName(@NotEmpty @NotNull @NotBlank(message = "Household name must not be blank") String name) {

    }


}



