package com.zcklab.api.repository;

import com.zcklab.api.model.Ninja;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

//It works like a warehouse, respects the logic that comes from the Service and delivers the necessary
// products from the database

@Repository
public interface RepositoryNinja extends JpaRepository<Ninja, Long> {


    // Optional is to avoid null values,
    // checking if there is something inside the "box" before actually calling the object.
    Optional<Ninja> findByName(String name);

    Optional<Ninja> findByEmail(String email);


    //Queries are only necessary in specific functions that don't come natively in JpaRepository.
    //e.g: FindAllNinjasByNameAndCityAndAdultAndHiperSuperDuperCool();
    //This is impractical.
    @Query("SELECT n.email FROM Ninja n")
    List<String> findAllEmails();

}
