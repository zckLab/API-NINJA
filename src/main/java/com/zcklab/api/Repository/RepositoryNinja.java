package com.zcklab.api.Repository;

import com.zcklab.api.Model.Ninja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

//It works like a warehouse, respects the logic that comes from the Service and delivers the necessary
// products from the database

@Repository
public interface RepositoryNinja extends JpaRepository<Ninja, Long> {


    // Optional is to avoid null values,
    // checking if there is something inside the "box" before actually calling the object.
    Optional<Ninja> findByName(String name);

    Optional<Ninja> findByEmail(String email);
}
