package com.zcklab.api.repository;

import com.zcklab.api.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClanRepository extends JpaRepository<Clan, Long> {
    @Query("SELECT c FROM Clan c JOIN FETCH c.ninja_clans")
    List<Clan> findAllWithNinjas();
}
