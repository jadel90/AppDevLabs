package com.example.lab_7.daos;


import com.example.lab_7.entities.Household;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {


    // Fetches a household by eircode with pets eagerly loaded
    @Query("SELECT h FROM Household h LEFT JOIN FETCH h.pets WHERE h.eircode = ?1")
    Household findByEircodeWithPets(String eircode);

    // Finds households with no pets
    @Query("SELECT h FROM Household h WHERE h.pets IS EMPTY")
    List<Household> findHouseholdsWithNoPets();

    // Finds owner-occupied households
    List<Household> findByOwnerOccupiedTrue();

    @Query("SELECT h FROM Household h WHERE h.eircode = :eircode")
    // Optional<Household> findByEircode(@Param("eircode") String eircode);
    Optional<Household> findByEircode(String eircode);

    // Method to check existence by eircode
    @Query("SELECT COUNT(h) > 0 FROM Household h WHERE h.eircode = :eircode")
    boolean existsByEircode(@Param("eircode") String eircode);

    // Custom query to delete a household by eircode
    @Modifying
    @Transactional
    @Query("DELETE FROM Household h WHERE h.eircode = :eircode")
    void deleteByEircode(@Param("eircode") String eircode);

}