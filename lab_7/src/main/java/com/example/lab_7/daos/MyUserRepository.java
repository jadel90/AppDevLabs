package com.example.lab_7.daos;

import com.example.lab_7.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String> {

    // Query using the email field
    Optional<MyUser> findByEmail(String email);
}
