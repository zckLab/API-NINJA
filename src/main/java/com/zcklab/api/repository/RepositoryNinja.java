package com.zcklab.api.repository;

import com.zcklab.api.enums.Category;
import com.zcklab.api.enums.Rank;
import com.zcklab.api.model.Ninja;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



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

    @Query("SELECT n FROM Ninja n WHERE n.age > 18 AND n.active = true")
    Page<Ninja> findActiveAdults(Pageable pageable);

    @Query(value = "SELECT * FROM tb_ninjas WHERE rank = :rank", nativeQuery = true)
    List<Ninja> findSpecificRank(@Param("rank") Rank rank); // Returns all items that have that specific rank.

    @Query(value = "SELECT DISTINCT rank FROM tb_ninjas", nativeQuery = true)
    List<Rank> findAllRank();

    @Query(value = "SELECT * FROM tb_ninjas WHERE active = true AND usr_category = :category", nativeQuery = true)
    List<Ninja> findByStatusAndCategory(@Param("category") Category category);

}
